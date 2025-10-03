# Use a lightweight JDK image
FROM eclipse-temurin:17-jdk-jammy as builder

# Set working directory
WORKDIR /app

# Copy Maven/Gradle wrapper files and build files first for caching
COPY mvnw pom.xml ./
COPY src src

# Build the app (skip tests for faster build in container)
RUN ./mvnw package -DskipTests

# ---- Runtime Image ----
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render expects this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
