FROM eclipse-temurin:17-jdk-alpine AS build

# Install Maven
RUN apk add --no-cache curl && \
    curl -fsSL -o /tmp/apache-maven.tar.gz https://apache.osuosl.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz && \
    tar -xf /tmp/apache-maven.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.4 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin && \
    rm -f /tmp/apache-maven.tar.gz

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /Softwares/SpringProject/Enrollment/target/Enrollment.jar /app/Enrollment.jar
# Add any additional configuration or setup steps for the runtime stage

CMD ["java", "-jar", "/app/Enrollment.jar"]