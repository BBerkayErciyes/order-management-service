# E-Commerce Order Management System (Backend API)

A robust, secure, and scalable **RESTful Backend API** developed using **Java 17** and **Spring Boot 3**. This project simulates a real-world e-commerce platform's backend, featuring advanced security with JWT, role-based access control, inventory management, and transactional order processing.

---

## Tech Stack & Tools

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL
* **ORM:** Hibernate / Spring Data JPA
* **Security:** Spring Security 6, JWT (JSON Web Tokens), BCrypt
* **Build Tool:** Maven
* **Utilities:** Lombok, Docker

---

## Key Features

### Security & Authentication
* **JWT Authentication:** Stateless and secure login mechanism.
* **Role-Based Access Control (RBAC):** Distinct permissions for `ADMIN` and `USER` roles.
* **Input Validation:** Robust validation (`@Valid`) for all API requests to prevent data corruption.

### Product Management
* **CRUD Operations:** Admins can Create, Update, and Delete products.
* **Search & Filtering:** Search products by name (case-insensitive).

### Order Processing
* **Transactional Integrity:** Uses `@Transactional` to ensure data consistency. If stock deduction fails, the order creates a rollback.
* **Stock Management:** Automatic inventory updates upon order placement.
* **Order Lifecycle:** Users can cancel orders; Admins can update statuses (e.g., `SHIPPED`).

### Exception Handling
* **Global Exception Handler:** Centralized error management returning clean, standardized JSON error responses (RFC 7807 compliant) instead of stack traces.

---

### Prerequisites
* Java 17 or higher
* PostgreSQL
* Maven

### 1. Clone the Repository
```bash
git clone [https://github.com/YOUR_USERNAME/order-management-service.git](https://github.com/YOUR_USERNAME/order-management-service.git)
cd order-management-service