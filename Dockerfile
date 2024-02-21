FROM openjdk:21-jdk

WORKDIR /app
COPY target/product-assortment-0.0.1-SNAPSHOT.jar product-assortment.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "product-assortment.jar"]
