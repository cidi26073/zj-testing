package com.example.firestore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.reactivestreams.Publisher;
import org.springframework.util.ReflectionUtils;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.spring.data.firestore.FirestoreTemplate;
import com.google.cloud.spring.data.firestore.SimpleFirestoreReactiveRepository;
import com.google.cloud.spring.data.firestore.mapping.FirestorePersistentEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UpdateFirestoreReactiveRepository<T> extends SimpleFirestoreReactiveRepository<T> {

    private Firestore firestore;

    private FirestorePersistentEntity<?> persistent;

    public UpdateFirestoreReactiveRepository(Firestore firestore, FirestoreTemplate template, Class<T> type) {
        super(template, type);
        this.firestore = firestore;
        this.persistent = template.getMappingContext().getPersistentEntity(type);
    }

    @Override
    public <S extends T> Mono<S> save(S entity) {
        return saveAll(Mono.just(entity)).next();
    }

    @Override
    public <S extends T> Flux<S> saveAll(Iterable<S> entities) {
        return saveAll(Flux.fromIterable(entities));
    }

    /**
     * The default behavior will overwrite the entire document,
     * so we specifically update based on fields rather than replacing the entire document, 
     * which could result in the loss of other data
     */
    @Override
    public <S extends T> Flux<S> saveAll(Publisher<S> entityStream) {
        return Flux.from(entityStream)
                .flatMap(it -> {
                    var id = (String) persistent.getIdentifierAccessor(it).getIdentifier();
                    var collection = firestore.collection(persistent.collectionName());
                    var future = id != null ? 
                            collection.document(id).set(it, SetOptions.merge()) : 
                            collection.add(it);
                    var publisher = toCompletableFuture(future);
                    return Mono.fromFuture(publisher)
                            .filter(DocumentReference.class::isInstance)
                            .map(DocumentReference.class::cast)
                            .map(DocumentReference::getId)
                            .switchIfEmpty(Mono.just(id))
                            .doOnNext(newId -> ReflectionUtils.setField(persistent.getIdProperty().getField(), it, newId))
                            .then(Mono.just(it));
                });
    }

    private <F> CompletableFuture<F> toCompletableFuture(Future<F> future) {
        if (future.isDone())
            return this.<F>transformDoneFuture(future);
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (!future.isDone())
                    awaitFutureIsDoneInForkJoinPool(future);
                return future.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                // Normally, this should never happen inside ForkJoinPool
                Thread.currentThread()
                        .interrupt();
                // Add the following statement if the future doesn't have side effects
                // future.cancel(true);
                throw new RuntimeException(e);
            }
        });
    }

    private <F> CompletableFuture<F> transformDoneFuture(Future<F> future) {
        var cf = new CompletableFuture<F>();
        F result;
        try {
            result = future.get();
        } catch (Throwable ex) {
            cf.completeExceptionally(ex);
            return cf;
        }
        cf.complete(result);
        return cf;
    }

    private void awaitFutureIsDoneInForkJoinPool(Future<?> future) throws InterruptedException {
        ForkJoinPool.managedBlock(new ForkJoinPool.ManagedBlocker() {

            @Override
            public boolean block() throws InterruptedException {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }

            @Override
            public boolean isReleasable() {
                return future.isDone();
            }

        });
    }

}