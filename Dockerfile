# Use OpenJDK image as base
FROM openjdk:17-jdk-slim AS build

# Set working directory
WORKDIR /app

# Copy the jar file from the target directory into the container
COPY ./target/*.jar /app/umkm-app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/umkm-app.jar"]
