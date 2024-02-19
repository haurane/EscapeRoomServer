FROM eclipse-temurin:17-jdk-alpine
MAINTAINER haurane
COPY target/EscapeRoomServer-0.0.1-SNAPSHOT.jar EscapeRoomServer-0.0.1.jar
ENTRYPOINT ["java","-jar","EscapeRoomServer-0.0.1.jar"]
EXPOSE 8080