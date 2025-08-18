🚖 Trip-Ease – Cab Booking Backend

Trip-Ease is a Spring Boot based backend application designed for managing cab booking operations. It provides APIs for customer registration, driver & cab management, and booking handling with features like cab availability tracking, gender-based filtering, exception handling, and automated booking confirmations via email.

⚙️ Tech Stack

Backend: Java 17, Spring Boot, Spring Web, Spring Data JPA

Database: MySQL

ORM: Hibernate (JPA)

API Documentation: Swagger UI

Other Tools: Lombok, SMTP (Java Mail Sender), Maven

📌 Features

👤 Customer Management: Add, fetch, and filter customers by gender/age.

🚕 Driver & Cab Management: Register drivers, assign cabs, and manage cab availability.

📑 Booking Module: Book cabs for customers, automatically assign available drivers, and calculate fare based on distance.

✅ Exception Handling: Custom exceptions for cases like invalid IDs, unavailable cabs, or missing customers.

📧 Email Notifications: Send booking confirmation to customers via SMTP.

🔍 API Testing & Docs: Swagger UI integrated at /swagger-ui/index.html.

🛠️ Project Structure
src/main/java/com/example/tripease
│
├── Controller    # REST Controllers for APIs
├── Service       # Business logic
├── Repository    # JPA Repositories
├── Model         # Entities (Customer, Driver, Cab, Booking, etc.)
├── DTO           # Request/Response DTOs
├── Transformer   # Entity <-> DTO converters
└── Exception     # Custom exception handling

🚀 Getting Started
1. Clone the Repository
git clone https://github.com/AbhijeetJ2000/Trip-Ease.git
cd Trip-Ease

2. Configure Database

Update application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/tripease
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Run the Application
mvn spring-boot:run

4. Access APIs

Swagger UI: http://localhost:8080/swagger-ui/index.html

Example Endpoints:

POST /customer/add → Register a new customer

POST /driver/add → Register a driver

POST /cab/register/driver/{driverId} → Assign a cab to a driver

POST /booking/book/customer/{customerId} → Book a cab

📧 Email Notifications

This project uses SMTP to send booking confirmations. Configure your Gmail/Yahoo SMTP in application.properties:

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

📝 Future Enhancements

JWT-based authentication & authorization

Payment gateway integration

Cab tracking with Google Maps API

Microservice architecture with service-to-service communication

✨ Author: Abhijeet Kumar Jha
