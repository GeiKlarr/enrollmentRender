FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY target/Enrollment-0.0.1-SNAPSHOT.jar.original app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "Enrollment.jar"]

