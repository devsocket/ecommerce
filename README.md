# 🛒 E-Commerce Microservices Platform
A scalable, modular e-commerce backend built with Spring Boot, Kafka, OAuth2, JWT, and PostgreSQL. Each service is independently deployable via Docker, orchestrated with Docker Compose.

---
## 🚀 Tech Stack
| Layer | Technology | Version |
|-------|------------|---------|
| Backend | Spring Boot | 3.5.2 |
| Auth | OAuth2, JWT | 0.11.5 |
| Messaging | Apache Kafka | 3.3.7 |
| Database | PostgreSQL | 42.7.3 |
| Containerization | Docker, Docker Compose | 
----
## 🧩 Microservices Overview
### ✅ Completed Services
- User Service
  Handles authentication and user management using JWT and OAuth2.
- Product Service
  Manages product catalog, categories, and product-related events.
- Order Service
  Processes orders, tracks status, and integrates with inventory and payment.

### 🛠️ Upcoming Services
- Inventory Service
  Tracks stock levels and synchronizes with product and order services.
- Cart Service
  Manages user shopping carts and session-based cart persistence.
- Payment Service
  Integrates with external payment gateways and handles transactions.
- Shipping Service
  Coordinates delivery logistics and shipment tracking.
- Review Service
  Enables users to leave product reviews and ratings.
----
## 🐳 Running Locally with Docker Compose
```bash
# Build and start all services
docker-compose up --build
```
Each service is defined in its own Dockerfile and registered in docker-compose.yml.

---
## 🔐 Authentication
- OAuth2 for third-party login support
- JWT for secure, stateless authentication across services

-----

## 📡 Kafka Topics
| Topic Name | Description |
|------------|-------------|
| product-events | Publishes product lifecycle events |
| order-events | Tracks order creation and updates |
| inventory-events | Syncs inventory changes |

-----

## 🗂️ Folder Structure
    ecommerce-platform/
    ├── common-event-models
    ├── common-kafka
    ├── user-service/
    ├── product-service/
    ├── order-service/
    ├── inventory-service/
    ├── cart-service/
    ├── payment-service/
    ├── shipping-service/
    ├── review-service/
    ├── docker-compose.yml

---

## 📬 API Gateway (Optional)
You can add an API Gateway (e.g., Spring Cloud Gateway or NGINX) to route requests and centralize authentication.

---
## 🧪 Testing
Each service includes unit and integration tests. Run tests with:
``` bash
mvn test
```

---
## 📖 Documentation
- Swagger UI available for each service at /swagger-ui.html
- Kafka event models are centralized in the common-event-models library, which is shared across all services to ensure consistency and type safety.

---

## 🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

---

## 📄 License
This project is licensed under the MIT License.

Let me know if you want to include example API requests, environment variable setup, or CI/CD instructions. I can tailor it further to your workflow.

----
## 👤 Author
Created by: __Venkata Sudheer Kumar Kondeti__

[![GitHub](https://img.shields.io/badge/GitHub-Profile-black?logo=github&style=for-the-badge)](https://github.com/devsocket)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?logo=linkedin&style=for-the-badge)](https://linkedin.com/in/sudheer44)


