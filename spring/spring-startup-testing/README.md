# Spring CDS/Native Startup Testing

For detailed content and results, please refer to this [link](https://jia-huang.gitlab.io/posts/blog/testing/spring-startup-testing/).

---
## Usage

To compile the images, follow these steps:

Navigate to the `cds` directory:

```bash
cd cds
mvn spring-boot:build-image
cd ..
```

Next, move to the `native` directory:
```bash
cd native
mvn -Pnative spring-boot:build-image
cd ..
```

Finally, proceed to the `none` directory:
```bash
cd none
mvn spring-boot:build-image
cd ..
```

---
## Execute Tests

To run the tests, use the following command:

```bash
docker compose --compatibility up -d
```