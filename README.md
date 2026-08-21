
---

# 2. Backend README

Now put this in your **`shortly-backend`** repository as `README.md`:

```markdown
# Shortly — URL Shortener Backend

A RESTful URL Shortener backend built using Java and Spring Boot.

The backend provides APIs for creating shortened URLs, generating custom short links, retrieving recent URLs and redirecting users to the original URL.

## 🚀 Live API

https://shortly-backend-kmqp.onrender.com

## 🔗 Frontend

https://shortly-frontend-gold.vercel.app

## ✨ Features

- 🔗 Create shortened URLs
- ✏️ Support custom short words
- 🔄 Redirect short URLs to original URLs
- 🕘 Retrieve recently created URLs
- 🗄️ MySQL database integration
- 🌐 REST API architecture
- 🔐 Environment-based configuration
- 🐳 Dockerized application
- ☁️ Cloud deployment

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Docker
- Render
- Aiven

## 📁 Project Structure

```text
shortly-backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── urlshortner/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── entity/
│   │   │           ├── dto/
│   │   │           └── config/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
├── Dockerfile
├── pom.xml
├── .gitignore
└── README.md
