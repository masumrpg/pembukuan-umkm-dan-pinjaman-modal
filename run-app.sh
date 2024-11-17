#!/bin/bash
echo "Cleaning and building the project..."
./mvnw clean package -DskipTests

echo "Starting Docker Compose..."
docker-compose up --build
