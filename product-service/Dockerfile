# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/product-service-1.0-SNAPSHOT.jar product-service-1.0-SNAPSHOT.jar

ENTRYPOINT ["/wait-for-postgres.sh"]

# Run the application
ENTRYPOINT ["java", "-jar", "product-service-1.0-SNAPSHOT.jar"]