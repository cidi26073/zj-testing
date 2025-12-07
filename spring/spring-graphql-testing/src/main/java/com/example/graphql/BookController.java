package com.example.graphql;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument("id") String id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return Book.getAll();
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @MutationMapping
    public Book addBook(@Argument("name") String name, @Argument("pageCount") int pageCount, @Argument("authorId") String authorId) {
        return Book.add(name, pageCount, authorId);
    }

    @MutationMapping
    public Book editBook(@Argument("id") String id, @Argument("name") String name, @Argument("pageCount") int pageCount, @Argument("authorId") String authorId) {
        var book = new Book(id, name, pageCount, authorId);
        return Book.edit(book);
    }

    @MutationMapping
    public boolean removeBook(@Argument("id") String id) {
        return Book.remove(id);
    }

    private static FluxSink<Book> sink;
    private static final Flux<Book> publisher = Flux.create(emitter -> sink = emitter, FluxSink.OverflowStrategy.BUFFER);

    @MutationMapping
    public Book publishBook(@Argument("id") String id) {
        var book = Book.getById(id);
        if (book != null && sink != null) {
            sink.next(book);
        }
        return book;
    }

    @SubscriptionMapping
    public Publisher<Book> onBookPublished() {
        return publisher;
    }

}