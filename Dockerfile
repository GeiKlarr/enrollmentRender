FROM openjdk:17.0.1-jdk-slim

# Install Maven
ENV MAVEN_VERSION x.x.x
ENV MAVEN_HOME /usr/share/maven

RUN apt-get update && \
    apt-get install -y curl && \
    curl -fsSL https://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share && \
    mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven && \
    ln -s $MAVEN_HOME/bin/mvn /usr/bin/mvn && \
    apt-get remove -y curl && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project with Maven
RUN mvn clean package -DskipTests

# Copy the built JAR to the desired location
COPY --from=build /target/Enrollment-0.0.1-SNAPSHOT.jar Enrollment.jar

# Expose the desired port
EXPOSE 8080

# Set the entrypoint for the container
ENTRYPOINT ["java", "-jar", "Enrollment.jar"]