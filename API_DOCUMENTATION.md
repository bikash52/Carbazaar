# CarBazzar API Documentation

A comprehensive REST API for a car marketplace platform where users can buy, sell, and manage vehicles.

## 🔗 Base URL
```
http://localhost:8080
```

## 🔐 Authentication
This API uses JWT (JSON Web Token) authentication. Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## 📖 Interactive Documentation
Once the application is running, you can access the interactive Swagger UI at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs JSON**: http://localhost:8080/api-docs

---

## 🔑 Authentication Endpoints

### 1. User Registration
**POST** `/api/v1/auth/signup`

Register a new user account in the CarBazzar platform.

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "mobileNumber": "9876543210",
  "fullName": "John Doe"
}
```

**Response:**
- **201 Created**: User registered successfully
- **400 Bad Request**: Invalid user data
- **409 Conflict**: User already exists

### 2. User Login
**POST** `/api/v1/auth/signin`

Authenticate user and receive JWT token for API access.

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Success Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "JWT"
}
```

**Response Codes:**
- **201 Created**: Login successful, JWT token returned
- **401 Unauthorized**: Invalid credentials

### 3. Generate OTP
**POST** `/api/v1/auth/generate-otp`

Generate OTP for mobile number verification.

**Parameters:**
- `mobileNumber` (required): User's mobile number

**Example:**
```
POST /api/v1/auth/generate-otp?mobileNumber=9876543210
```

**Response:**
- **200 OK**: OTP sent successfully
- **401 Unauthorized**: User not found

### 4. Verify OTP
**POST** `/api/v1/auth/verify-otp`

Verify OTP and get JWT token.

**Parameters:**
- `mobileNumber` (required): User's mobile number
- `otp` (required): OTP received

**Example:**
```
POST /api/v1/auth/verify-otp?mobileNumber=9876543210&otp=123456
```

### 5. Content Manager Registration
**POST** `/api/v1/auth/Content-Manager-signup`

Register a content manager account (admin privileges).

---

## 🚗 Car Management Endpoints

### 1. Add New Car
**POST** `/api/v1/car/add` 🔒

Add a new car listing to the marketplace. **Requires Authentication**.

**Request Body:**
```json
{
  "brand": "Honda",
  "model": "Civic",
  "year": 2020,
  "fuelType": "Petrol",
  "transmission": "Manual",
  "mileage": 15000,
  "price": 850000,
  "description": "Well-maintained Honda Civic in excellent condition",
  "color": "Silver",
  "engine": "1.8L",
  "location": "Mumbai"
}
```

**Response Codes:**
- **201 Created**: Car added successfully
- **400 Bad Request**: Invalid car data
- **401 Unauthorized**: JWT token required

### 2. Get Car Details
**GET** `/api/v1/car/getDetails/{id}`

Retrieve detailed information about a specific car by ID.

**Path Parameters:**
- `id` (required): Car ID

**Example:**
```
GET /api/v1/car/getDetails/1
```

**Success Response:**
```json
{
  "id": 1,
  "brand": "Honda",
  "model": "Civic",
  "year": 2020,
  "fuelType": "Petrol",
  "transmission": "Manual",
  "mileage": 15000,
  "price": 850000,
  "description": "Well-maintained Honda Civic in excellent condition",
  "color": "Silver",
  "engine": "1.8L",
  "location": "Mumbai",
  "createdAt": "2025-09-02T10:30:00Z"
}
```

**Response Codes:**
- **200 OK**: Car details retrieved successfully
- **404 Not Found**: Car not found

### 3. Search Cars
**GET** `/api/v1/car/searchCar`

Search for cars by brand name, model, or manufacturing year.

**Query Parameters:**
- `param` (optional): Search parameter (brand/model name)
- `year` (optional): Manufacturing year

**Examples:**
```
GET /api/v1/car/searchCar?param=honda
GET /api/v1/car/searchCar?year=2020
GET /api/v1/car/searchCar?param=honda&year=2020
```

**Response:**
Array of matching cars with the same structure as "Get Car Details".

### 4. Get All Cars (Paginated)
**GET** `/api/v1/car`

Retrieve all cars with pagination and sorting options.

**Query Parameters:**
- `pageNo` (optional, default: 0): Page number (0-based)
- `pageSize` (optional, default: 2): Number of items per page
- `sortBy` (optional, default: "id"): Field to sort by
- `sortDir` (optional, default: "asc"): Sort direction (asc/desc)

**Example:**
```
GET /api/v1/car?pageNo=0&pageSize=10&sortBy=price&sortDir=desc
```

**Response:**
Array of cars sorted and paginated according to parameters.

---

## 🏷️ Brand Management Endpoints

### 1. Add Brand
**POST** `/api/v1/brandcontroller/add`

Add a new car brand to the system.

**Request Body:**
```json
{
  "name": "Toyota"
}
```

### 2. Get All Brands
**GET** `/api/v1/brandcontroller/get`

Retrieve all available car brands.

**Response:**
```json
[
  "Honda",
  "Toyota",
  "Maruti Suzuki",
  "Hyundai",
  "BMW"
]
```

---

## 📍 Location Management

### Location Controller
**Base Path**: `/api/v1/locations`

Manage geographical locations for car listings.

---

## 🔧 Additional Controllers

The API also includes controllers for:
- **Fuel Type Management**: `/api/v1/fueltype`
- **Transmission Management**: `/api/v1/transmission`
- **Model Management**: `/api/v1/model`
- **Year Management**: `/api/v1/year`
- **CRM Operations**: Customer relationship management
- **Car Evaluation**: Professional car evaluation services
- **File Upload**: Image and document management

---

## 🚨 Error Responses

All endpoints may return these error responses:

### 400 Bad Request
```json
{
  "error": "Bad Request",
  "message": "Invalid input data",
  "timestamp": "2025-09-02T10:30:00Z"
}
```

### 401 Unauthorized
```json
{
  "error": "Unauthorized",
  "message": "JWT token is required",
  "timestamp": "2025-09-02T10:30:00Z"
}
```

### 404 Not Found
```json
{
  "error": "Not Found",
  "message": "Resource not found",
  "timestamp": "2025-09-02T10:30:00Z"
}
```

### 500 Internal Server Error
```json
{
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "timestamp": "2025-09-02T10:30:00Z"
}
```

---

## 🔍 API Usage Examples

### Complete User Registration and Car Addition Flow

1. **Register a new user:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123",
    "mobileNumber": "9876543210",
    "fullName": "John Doe"
  }'
```

2. **Login to get JWT token:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securePassword123"
  }'
```

3. **Add a new car (use JWT token from login):**
```bash
curl -X POST http://localhost:8080/api/v1/car/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brand": "Honda",
    "model": "Civic",
    "year": 2020,
    "fuelType": "Petrol",
    "transmission": "Manual",
    "mileage": 15000,
    "price": 850000,
    "description": "Well-maintained Honda Civic in excellent condition",
    "color": "Silver",
    "engine": "1.8L",
    "location": "Mumbai"
  }'
```

4. **Search for cars:**
```bash
curl -X GET "http://localhost:8080/api/v1/car/searchCar?param=honda&year=2020"
```

---

## 📱 Testing with Postman

1. Import the API endpoints into Postman
2. Set up environment variables:
   - `baseUrl`: http://localhost:8080
   - `jwtToken`: (obtain from login endpoint)
3. Use `{{baseUrl}}` and `{{jwtToken}}` in your requests

---

## 🛡️ Security Notes

- All sensitive endpoints require JWT authentication
- JWT tokens expire after 24 hours (86400000 ms)
- Passwords are encrypted using BCrypt
- Mobile OTP verification is available for enhanced security

---

## 📊 Database Requirements

Ensure MySQL is running with:
- **Host**: localhost:3306
- **Database**: car
- **Username**: root
- **Password**: Qwe@83288

---

## 🔄 API Versioning

Current API version: **v1**

All endpoints are prefixed with `/api/v1/` to support future versioning.

