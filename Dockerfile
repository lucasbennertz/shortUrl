FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /build/target/shortUrl.jar /app/shortUrl.jar

EXPOSE 8080

CMD ["java", "-jar", "shortUrl.jar"]