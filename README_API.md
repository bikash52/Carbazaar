# CarBazzar API - Quick Start Guide

## 🚀 Getting Started

### 1. Start the Application
```bash
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**

### 2. Access Interactive Documentation

Once the application is running, visit:

#### 🌐 **Swagger UI (Interactive)**
```
http://localhost:8080/swagger-ui.html
```
- Test API endpoints directly in browser
- View request/response schemas
- JWT authentication support

#### 📄 **OpenAPI JSON**
```
http://localhost:8080/api-docs
```
- Raw OpenAPI 3.0 specification
- Import into API tools

### 3. Import Postman Collection
1. Open Postman
2. Import `CarBazzar_Postman_Collection.json`
3. Set environment variables:
   - `baseUrl`: http://localhost:8080
   - `jwtToken`: (will be auto-populated after login)

## 🔗 Quick API Testing

### Register a User
```bash
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com", 
    "password": "password123",
    "mobileNumber": "9876543210",
    "fullName": "Test User"
  }'
```

### Login to Get JWT Token
```bash
curl -X POST http://localhost:8080/api/v1/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

### Add a Car (Use JWT from login)
```bash
curl -X POST http://localhost:8080/api/v1/car/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brand": "Honda",
    "model": "Civic", 
    "year": 2020,
    "price": 850000
  }'
```

### Search Cars
```bash
curl -X GET "http://localhost:8080/api/v1/car/searchCar?param=honda"
```

## 📚 Documentation Files

- **`API_DOCUMENTATION.md`** - Complete API reference
- **`CarBazzar_Postman_Collection.json`** - Postman collection
- **`README_API.md`** - This quick start guide

## 🛡️ Authentication

All sensitive endpoints require JWT authentication:
1. Register or login to get JWT token
2. Include in Authorization header: `Bearer <token>`
3. Tokens expire after 24 hours

## 🌐 Available Endpoints

### Authentication
- `POST /api/v1/auth/signup` - Register user
- `POST /api/v1/auth/signin` - Login user
- `POST /api/v1/auth/generate-otp` - Generate OTP
- `POST /api/v1/auth/verify-otp` - Verify OTP

### Car Management
- `POST /api/v1/car/add` 🔒 - Add car
- `GET /api/v1/car/getDetails/{id}` - Get car details
- `GET /api/v1/car/searchCar` - Search cars
- `GET /api/v1/car` - Get all cars (paginated)

### Brand Management
- `POST /api/v1/brandcontroller/add` - Add brand
- `GET /api/v1/brandcontroller/get` - Get all brands

🔒 = Requires JWT Authentication

## 🔧 Prerequisites

- Java 17+
- MySQL running on localhost:3306
- Database named 'car'
- Maven 3.6+

## 📱 Testing Tools

- **Swagger UI**: Interactive testing in browser
- **Postman**: Import provided collection
- **cURL**: Command line testing
- **Any HTTP client**: REST API compatible

## 🆘 Troubleshooting

**Port 8080 in use?**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /F /PID <process_id>
```

**Database connection issues?**
- Ensure MySQL is running
- Check credentials in `application.properties`
- Database 'car' should exist

**JWT token errors?**
- Token expires after 24 hours
- Include 'Bearer ' prefix in Authorization header
- Login again to get fresh token

## 🎯 Next Steps

1. ✅ Start the application
2. ✅ Visit Swagger UI
3. ✅ Test authentication endpoints
4. ✅ Import Postman collection
5. ✅ Read full API documentation

Happy coding! 🚀
