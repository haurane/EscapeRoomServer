# Build Container
FROM maven:3.9.1-eclipse-temurin-17-alpine as build

RUN echo "backend docker"

COPY pom.xml /build/
COPY src /build/src

WORKDIR /build/
RUN mvn package -P docker

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/EscapeRoomServer-0.0.1-SNAPSHOT.jar /app/EscapeRoomServer-0.0.1.jar
ENTRYPOINT ["java","-jar","EscapeRoomServer-0.0.1.jar"]
EXPOSE 8080