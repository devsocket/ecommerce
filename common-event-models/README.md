# 📦 common-event-models

A centralized module containing versioned, immutable Kafka event DTOs used across the DevSocket eCommerce microservices ecosystem. This library ensures consistency, reusability, and clean separation of domain events from infrastructure logic.

---

## 🚀 Purpose

- ✅ Share event definitions across services
- ✅ Enable schema versioning and evolution
- ✅ Promote immutability and serialization safety
- ✅ Decouple business logic from transport concerns

---

## 🧱 Architecture

    Microservice A
        └── Depends on common-event-models
        └── Uses shared Kafka DTOs

    Microservice B
        └── Depends on common-event-models
        └── Uses same DTOs for consuming events

    Kafka Topic
    └── Transports serialized common-event-models DTOs

---

## 📚 Included Modules & DTOs

| Service           | DTO Class             | Description                                |
|------------------|-----------------------|--------------------------------------------|
| `order-service`   | `OrderEvent`          | Order lifecycle events                     |
| `product-service` | `ProductEvent`        | Product catalog and pricing updates        |
| `user-service`    | `UserEvent`           | User registration and profile changes      |
| `payment-service` | `PaymentEvent`        | Payment transactions and refunds           |
| `inventory-service`| `InventoryEvent`     | Stock adjustments and reservations         |
| `shipping-service`| `ShippingEvent`       | Shipment tracking and delivery updates     |
| `cart-service`    | `CartEvent`           | Cart interactions and checkout triggers    |
| `review-service`  | `ReviewEvent`         | Product reviews and feedback               |

---

## 🧬 Design Principles

- **Java Records**: All DTOs use `record` for immutability and clarity
- **EventType Enums**: Each DTO includes an `EventType` to distinguish lifecycle stages
- **Timestamp Field**: Every event includes a `timestamp` for ordering and audit
- **Nested Records**: Used for structured sub-objects like `Address`, `CartItem`, etc.

---

## 🛠️ Usage

Add this module as a dependency in your microservice:

```xml
<dependency>
  <groupId>com.devsocket.ecommerce</groupId>
  <artifactId>common-event-models</artifactId>
  <version>1.0.0</version>
</dependency>
```

Then import and use the DTOs:

```java
import com.devsocket.ecommerce.events.user.UserEvent;

UserEvent event = new UserEvent(
    userId,
    email,
    fullName,
    phoneNumber,
    new UserEvent.Address(...),
    UserEvent.EventType.REGISTERED, Instant.now());
```
---
## 📦 Serialization Compatibility
These DTOs are designed to work with:

* ✅ JSON (default Spring Kafka)

* ✅ Avro / Protobuf (with annotations if needed)

* ✅ Schema Registry (for versioning and compatibility)
---
## 📈 Versioning Strategy
Semantic versioning (MAJOR.MINOR.PATCH)
Breaking changes (e.g., field removal or type change) require a major version bump
Additive changes (e.g., new fields) are backward-compatible

----
## 🤝 Contributing
Feel free to submit PRs for:
New event types
Schema enhancements
Serialization improvements