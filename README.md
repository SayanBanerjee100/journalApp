# 📘 JournalApp — Secure Spring Boot Backend

A production-style backend application built using **Spring Boot** that provides secure REST APIs for managing journals and tasks with **JWT authentication** and **MongoDB persistence**.

This project demonstrates backend fundamentals including layered architecture, authentication, API design, and containerized deployment.

---

## 🚀 Features

* ✅ User Registration & Login
* 🔐 JWT-based Authentication (Spring Security)
* 📓 Journal CRUD APIs
* ✅ Task Management APIs
* 🗄 MongoDB Document Persistence
* 🧱 Layered Architecture (Controller → Service → Repository)
* 🐳 Dockerized Application
* 📄 OpenAPI / Swagger Documentation
* 🔑 Password Encryption using BCrypt

---

## 🧠 Architecture

The application follows a clean layered backend architecture:

```
Client
  ↓
JWT Security Filter
  ↓
Controller Layer (REST APIs)
  ↓
Service Layer (Business Logic)
  ↓
Repository Layer (MongoDB Access)
  ↓
MongoDB Database
```

### Layers

| Layer      | Responsibility                  |
| ---------- | ------------------------------- |
| Controller | Handles HTTP requests/responses |
| Service    | Business logic                  |
| Repository | Database interaction            |
| Security   | Authentication & Authorization  |
| Database   | Data persistence                |

---

## 🔐 Authentication Flow

1. User registers using `/users/create`
2. User logs in via `/auth/login`
3. Server generates JWT token
4. Client sends token in `Authorization` header
5. Custom JWT filter validates requests

Example header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🛠 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **JWT (JSON Web Token)**
* **MongoDB**
* **Maven**
* **Docker**
* **Swagger / OpenAPI**

---

## 📂 Project Structure

```
src/main/java/.../journal
│
├── controller     → REST endpoints
├── service        → business logic
├── repository     → MongoDB repositories
├── model          → domain models
├── security       → JWT authentication
├── config         → application configuration
└── dto            → request/response objects
```

---

## ⚙️ Running Locally

### 1️⃣ Clone Repository

```
git clone <repo-url>
cd journal-backend
```

### 2️⃣ Run MongoDB (Docker)

```
docker-compose up
```

### 3️⃣ Start Application

```
./mvnw spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

## 🐳 Docker Setup

Build image:

```
docker build -t journal-app .
```

Run container:

```
docker run -p 8080:8080 journal-app
```

Docker ensures consistent runtime across environments.

---

## 📬 API Testing (Postman Flow)

1. Create User
   `POST /users/create`

2. Login
   `POST /auth/login`

3. Copy JWT Token

4. Call Protected APIs with:

```
Authorization: Bearer <TOKEN>
```

---

## 📖 API Documentation

Swagger UI available at:

```
http://localhost:8080/swagger-ui.html
```

---

## 🎯 Learning Goals

This project was built to understand:

* Spring Boot backend design
* Stateless authentication using JWT
* Secure API development
* MongoDB integration
* Containerized deployment using Docker

---

## 👨‍💻 Author

**Sayan Banerjee**

Backend Developer | Java & Spring Boot Enthusiast
GDG Hackathon Team Lead — Top 10 out of 300 teams

---

## 📄 License

This project is for educational and learning purposes.
