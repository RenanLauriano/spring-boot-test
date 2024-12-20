# Stage 1: Download Dependencies
FROM maven:3.9.6-eclipse-temurin-21-alpine AS dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Stage 2: Build the application
FROM dependencies AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 3: Create the final image
FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=build /app/target/SpringBootTest.jar /app/SpringBootTest.jar

# Set the default value for JAVA_OPTS
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar SpringBootTest.jar"]