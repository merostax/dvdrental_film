# Use a base image
FROM docker.io/library/eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

# Set the working directory

# Copy the JAR file from your local machine to the container
COPY ./target/*.jar starter.jar

# Expose the port

# Define the entrypoint with system properties
ENTRYPOINT ["java", "-Djboss.http.port=8081", "-Djboss.management.http.port=9990", "-jar", "starter.jar"]
