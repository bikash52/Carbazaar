# CarBazzar - Car Marketplace Application

A Spring Boot-based REST API for a car marketplace platform where users can buy, sell, and manage vehicles.

## 🚀 Features

- Car Listing Management (CRUD operations)
- User Authentication with JWT
- Car Evaluation System
- File Upload Support (Mock S3 Service)
- Interactive API Documentation (Swagger)

## 🛠️ Tech Stack

- **Java 17** + **Spring Boot 3.0**
- **MySQL** + **Spring Data JPA** 
- **JWT Authentication** + **Spring Security**
- **Swagger/OpenAPI** for API documentation

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd CarBazzar
```

### 2. Database Setup
1. Install and start MySQL
2. Create a database named `car`:
   ```sql
   CREATE DATABASE car;
   ```

### 3. Configuration
1. Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`
2. Update the configuration with your values:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/car
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   # JWT Configuration
   jwt.key=your-secret-key-here
   jwt.issuer=CarBazzar
   ```

### 4. Environment Variables (Recommended)
Set the following environment variables instead of hardcoding values:
```bash
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export JWT_SECRET_KEY=your-secret-key-here
export JWT_ISSUER=CarBazzar
```

### 5. Build and Run
```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📖 API Documentation

Once the application is running:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html` - Interactive API testing
- **API Docs**: `http://localhost:8080/api-docs` - OpenAPI specification

## 🛡️ Security

- JWT Authentication for secure API access
- Environment variables for sensitive configuration
- All endpoints documented in Swagger UI
