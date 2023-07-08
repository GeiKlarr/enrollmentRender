FROM maven:3.8.7-jdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Enrollment-0.0.1-SNAPSHOT.jar.original Enrollment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "Enrollment.jar"]

