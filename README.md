## **Warehouse Microservices Project**

A **Spring Boot microservices-based warehouse system** for managing **FX deals**, built with **PostgreSQL, Docker Compose**.

### **Features**
✅ RESTful API for FX deal management  
✅ PostgreSQL integration with data initialization  
✅ Docker-based deployment  
✅ JaCoCo test coverage
---

## **1️⃣ Prerequisites**
Ensure you have the following installed:

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker & Docker Compose](https://docs.docker.com/compose/)

---

## **2️⃣ Project Structure**
```
warehouse/
│── src/
│   ├── main/java/com/progresssoft/warehouse
│   │   ├── controller/    # REST controllers (handle HTTP requests)
│   │   ├── dto/           # Data Transfer Objects (DTOs)
│   │   ├── entity/        # JPA Entities (database models)
│   │   ├── exception/     # Custom exception handling
│   │   ├── mapper/        # MapStruct mappers (Entity <-> DTO)
│   │   ├── repository/    # Data access layer (Spring Data JPA)
│   │   ├── service/       # Business logic layer
│   ├── resources/
│   │   ├── application.properties  # Spring configuration
│── target/                  # Compiled JAR files
│── Dockerfile               # Docker containerization
│── docker-compose.yml       # Docker Compose service orchestration
│── Makefile                 # Build automation commands
│── README.md                # Project documentation
│── pom.xml                  # Maven dependencies & build configuration

```

---

## **3️⃣ Setup & Installation**
### **A. Clone the repository**
```sh
git clone https://github.com/Douaesb/warehouse.git
cd warehouse
```

### **B. Build the project**
```sh
mvn clean package -DskipTests
```

### **C. Run with Docker**
```sh
docker-compose up --build -d
```

- API will be available at: `http://localhost:8080`

---

## **4️⃣ API Endpoints**
### **Create FX Deal**
```http
POST /api/deals
```
#### **Request Body (JSON)**
```json
{
  "dealUniqueId": "550e8400-e29b-41d4-a716-446655440000",
  "fromCurrency": "EUR",
  "toCurrency": "USD",
  "dealTimestamp": "2025-02-20T15:30:00",
  "dealAmount": 1200.50
}

```

#### **Response (JSON)**
```json
{
  "success": true,
  "message": "FX Deal saved successfully.",
  "fxDeal": {
    "dealUniqueId": "550e8400-e29b-41d4-a716-446655440100",
    "fromCurrency": "EUR",
    "toCurrency": "USD",
    "dealTimestamp": "2025-02-20T15:30:00",
    "dealAmount": 1200.50
  }
}

```
---

## **5️⃣ Database Initialization**
The **PostgreSQL container** automatically runs `init.sql`

---

## **6️⃣ Running Tests**
```sh
mvn test
```
---
## **7️⃣ Deployment**
### **Docker-Based Deployment**
```sh
docker-compose up --build -d
```

### **Manual JAR Execution**
```sh
java -jar target/warehouse-0.0.1-SNAPSHOT.jar
```

---

## Contact

For any questions or suggestions, please contact:

- **Name:** Douae Sebti
- **Email:** [douaesebti33@gmail.com](mailto:douaesebti33@gmail.com)
- **GitHub:** [Douaesb](https://github.com/Douaesb)
