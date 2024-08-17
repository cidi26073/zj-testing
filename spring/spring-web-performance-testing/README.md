# Spring MVC/WebFlux Performance Testing (Virtual Thread/GraalVM)

For detailed content and results, please refer to this [link](https://jia-huang.gitlab.io/posts/blog/testing/spring-performance-testing/).

## Getting Started

Compile the various versions of the services.

* mvc
    ```bash
    cd mvc
    mvn spring-boot:build-image
    ```
* mvc + virtual
    ```bash
    cd mvc_virtual
    mvn spring-boot:build-image
    ```
* mvc + native
    ```bash
    cd mvc_native
    mvn -Pnative spring-boot:build-image
    ```
* mvc + virtual + native
    ```bash
    cd mvc_virtual_native
    mvn -Pnative spring-boot:build-image
    ```
* flux
    ```bash
    cd flux
    mvn spring-boot:build-image
    ```
* flux + virtual
    ```bash
    cd flux_virtual
    mvn spring-boot:build-image
    ```
* flux + native
    ```bash
    cd flux_native
    mvn -Pnative spring-boot:build-image
    ```
* flux + virtual + native
    ```bash
    cd flux_virtual_native
    mvn -Pnative spring-boot:build-image
    ```
---
## Usage

Run the `docker-compose` script to generate results in the `output` directory.

* mvc
    ```bash
    docker compose -f mvc.yml up -d 
    ```
* mvc + virtual
    ```bash
    docker compose -f mvc_virtual.yml up -d 
    ```
* mvc + native
    ```bash
    docker compose -f mvc_native.yml up -d 
    ```
* mvc + virtual + native
    ```bash
    docker compose -f mvc_virtual_native.yml up -d 
    ```
* flux
    ```bash
    docker compose -f flux.yml up -d 
    ```
* flux + virtual
    ```bash
    docker compose -f flux_virtual.yml up -d 
    ```
* flux + native
    ```bash
    docker compose -f flux_native.yml up -d 
    ```
* flux + virtual + native
    ```bash
    docker compose -f flux_virtual_native.yml up -d 
    ```
