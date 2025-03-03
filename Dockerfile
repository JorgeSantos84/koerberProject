# First stage: Build and run tests
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Run tests and build the application
RUN mvn clean test package -DskipTests=false

# Second stage: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy only the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

