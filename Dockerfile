FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Enrollment-0.0.1-SNAPSHOT.jar.original Enrollment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "Enrollment.jar"]

