# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Maven wrapper and pom first
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Ensure mvnw is executable
RUN chmod +x mvnw

# Copy source code
COPY src src

# Build the application (skip tests for speed)
RUN ./mvnw package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
