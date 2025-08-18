# 🚖 Trip-Ease – Cab Booking Backend

Trip-Ease is a **Spring Boot** backend application for managing cab booking operations.  
It provides REST APIs for customer registration, driver & cab management, and booking handling with features such as cab availability tracking, gender-based filtering, custom exception handling, and automated booking confirmations via email.

---

## ⚙️ Tech Stack

- **Backend:** Java 17, Spring Boot, Spring Web, Spring Data JPA  
- **Database:** MySQL  
- **ORM:** Hibernate (JPA)  
- **API Documentation:** Swagger UI (springdoc-openapi)  
- **Other Tools:** Lombok, JavaMail (SMTP), Maven

---

## 📌 Features

- 👤 **Customer Management:** Register customers and fetch/filter by gender or age.  
- 🚕 **Driver & Cab Management:** Register drivers, register/assign cabs and manage cab availability.  
- 📑 **Booking Module:** Create bookings for customers; system assigns an available cab + driver and calculates fare from distance.  
- ✅ **Exception Handling:** Custom exceptions for invalid IDs, unavailable cabs, etc.  
- 📧 **Email Notifications:** Booking confirmation emails sent to customers (SMTP).  
- 🔍 **API Docs:** Swagger UI available at `/swagger-ui/index.html`.

---

## 🛠️ Project Structure
src/main/java/com/example/tripease
│
├── Controller # REST Controllers for APIs
├── Service # Business logic
├── Repository # JPA Repositories
├── Model # Entities: Customer, Driver, Cab, Booking
├── dto # Request/Response DTOs
├── Transformer # Entity <-> DTO converters
└── Exception # Custom exception classes & handlers

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/AbhijeetJ2000/Trip-Ease.git
cd Trip-Ease

2. Configure the application

Edit src/main/resources/application.properties (or provide environment variables). Example properties:
# MySQL datasource
spring.datasource.url=jdbc:mysql://localhost:3306/tripease
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Mail (Gmail example)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Server port (optional)
server.port=8080

3. Run the application
mvn clean install
mvn spring-boot:run

4. API documentation

Open Swagger UI:
http://localhost:8080/swagger-ui/index.html

⚙️ Error handling & Best practices

Custom exceptions (e.g., DriverNotFoundException, CabUnavailableException) should be annotated with @ResponseStatus or handled centrally via @RestControllerAdvice to map exceptions to proper HTTP status codes and JSON error bodies.

Use DTOs for API request/response (already implemented) — do not expose JPA entities directly to avoid over-posting and leaking internals.

Add validation annotations on DTOs (@NotBlank, @Email, @Min) and use @Valid in controller method signatures.

Mark multi-step service methods (like booking) with @Transactional to ensure atomicity.

Avoid sending emails inside the transaction before commit; either register an after-commit callback or publish an event consumed after commit.

🧪 Testing

Unit test the service layer with JUnit + Mockito (mock repositories).

Use @WebMvcTest or MockMvc for controller-level testing.

Use an in-memory DB (H2) or a test container (Testcontainers) for integration tests.

🔐 Security & Production Notes

Use Spring Security + JWT for authentication/authorization in production.

Replace spring.jpa.hibernate.ddl-auto=update with database migration tools (Flyway or Liquibase) for controlled schema changes.

Externalize secrets (SMTP credentials, DB passwords) to environment variables or secret managers (Vault, AWS Secrets Manager).

Configure logging, monitoring, and proper exception tracing for observability (ELK/Prometheus/Grafana).

📦 Future Enhancements

JWT-based authentication & role-based access control.

Payment gateway integration for fares.

Cab real-time tracking (Google Maps / WebSocket).

Split into microservices (Booking, Cab, Driver, Customer) with interservice communication (REST or messaging).

Rate limiting, circuit breaker patterns (Resilience4j) for robustness.

🤝 Contributing

Fork the repository

Create a feature branch: git checkout -b feature/<your-feature>

Commit changes: git commit -m 'Add some feature'

Push to the branch and open a PR

Please keep commits small and write descriptive messages. Add unit tests for new behavior.

🧾 License & Author

Author: Abhijeet Kumar Jha
Project: Trip-Ease
