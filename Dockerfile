# Use an official OpenJDK as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/image-uploader-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
