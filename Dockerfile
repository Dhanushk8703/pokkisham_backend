# Use a lightweight JDK 21 runtime
FROM eclipse-temurin:21-jre

# Set working directory inside the container
WORKDIR /app

# Copy your Spring Boot JAR (update the name if different)
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "\target\app-0.0.1-SNAPSHOT.jar"]
