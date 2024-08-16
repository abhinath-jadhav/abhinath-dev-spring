# Stage 1: Build the application
FROM maven:3.8.6-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files
COPY pom.xml ./
COPY src ./src

# Package the application using Maven
RUN mvn clean package -DskipTests

# Stage 2: Serve the application
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
