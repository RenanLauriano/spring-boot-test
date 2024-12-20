# Spring Boot Test

## Overview

This Spring Boot microservices application is designed to:

- Periodically fetch user details from [RandomUser API](https://randomuser.me/api/).
- Store user details in a H2 database.
- Send email notifications when user data changes.
- Provide a secure REST API for querying stored user data.

The application is containerized and can be run with Docker Compose.

---

## Features

### 1. Periodic User Fetching
- Fetches user details from RandomUser API at regular intervals of 10 seconds.
- Interval can be configured in [UserService - @Scheduled(fixedRate = 10000](src/main/java/com/javatest/service/UserService.java).

### 2. H2 Database
- Uses H2 for data storage.
- Ensures no duplicate users by identifying records based on the `phone` and `cell` fields.

### 3. Email Notifications
- Sends email notifications when user details are updated.
- SMTP configuration can be set via application properties, it uses a mailhog locally.

### 4. Secure REST API
- Authenticates with API KEY sent in a header named X-API-KEY.
- API Key Secret can be configured in properties.
- Exposes a GET endpoint (/users) with basic authentication for querying stored records.
- Accepts `from` and `to` database record indexes as path variables.
- Returns a JSON list of user records within the specified range.

### 5. Logging
- All operations are logged with a rolling text logger for better traceability.

### 6. Unit Test Coverage
- Services are covered with Unit tests. It was a decision to cover only services becacuse they have all the logic
- Controllers and models can be covered with Integration Tests further.

### 7. Scalability
- This application runs by default as a container, meaning it can be deployed to AWS Fargate or similar cloud solutions.

---

## Getting Started

### Prerequisites

Ensure you have the following installed:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 17+](https://jdk.java.net/)
- [Maven](https://maven.apache.org/)

---

### Installation

1. **Clone the repository:**
   ```bash
   git clone git@github.com:RenanLauriano/spring-boot-test.git
   cd spring-boot-test
   ```

2. **Start the application:**
   ```bash
   docker-compose up
   ```

4. The application will be available at `http://localhost:8080`.