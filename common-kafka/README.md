# 📦 ecommerce-kafka Module

A reusable Kafka messaging library for the DevSocket eCommerce microservices ecosystem. This module abstracts Kafka producer and consumer logic, enabling services to send and receive messages without duplicating boilerplate code.

---

## 🚀 Features

- Generic Kafka producer and consumer support
- Delegated message handling via `KafkaEventHandler`
- Manual acknowledgment for reliable processing
- Plug-and-play integration with any microservice
- JSON serialization/deserialization out of the box
- Centralized configuration via Spring Boot

## 🧱 Architecture Overview

This module is designed to decouple Kafka messaging logic from business services, allowing microservices to focus solely on handling domain events.

### 🔄 Message Flow
    ┌────────────────────────────┐
    │ Microservice A │
    │ ┌────────────────────────┐ │
    │ │ KafkaEventHandler<T> │◄─── Handles domain-specific logic
    │ └────────────────────────┘ │
    │   ▲           │
    │   │ Delegates     │ 
    └────────┼────────────────────┘
    │ ▼
    ┌────────────────────────────────────────────┐
    │ ecommerce-kafka module │
    │ ┌────────────────────────────────────────┐ │
    │ │ KafkaListener │ │
    │ │ └── Receives Kafka messages │ │
    │ │ └── Delegates to KafkaEventHandler │ │
    │ │ └── Acknowledges after success │ │
    │ └────────────────────────────────────────┘ │
    │ ┌────────────────────────────────────────┐ │
    │ │ KafkaProducerService<T> │ │
    │ │ └── Publishes events to Kafka topics │ │
    │ └────────────────────────────────────────┘ │
    │ ┌────────────────────────────────────────┐ │
    │ │ KafkaConfig │ │
    │ │ └── Sets up bootstrap servers │ │
    │ │ └── Configures serializers/deserial. │ │
    │ └────────────────────────────────────────┘ │
    └────────────────────────────────────────────┘

Code
---

## ⚙️ Configuration

Add the following to your microservice's `application.yml`:

```yaml
kafka:
  bootstrap-servers: localhost:9092
```
## 📥 Consuming Messages

Implement the KafkaEventHandler<T> interface in your microservice:
```java
@Component
public class OrderEventHandler implements KafkaEventHandler<Object> {
@Override
public void handleEvent(Object event) {
// Cast and process your event
System.out.println("Received: " + event);
}
}
```
The `KafkaListener` class in this module will automatically delegate incoming messages to your handler.

## 📤 Producing Messages
Use the `KafkaProducerService<T>` to send messages:

```java
@Autowired
private KafkaProducerService<OrderEvent> producer;

public void publishOrder(OrderEvent event) {
    producer.send("order-events", event);
}
```
## 🧪 Testing
Use `spring-kafka-test` for integration testing with embedded Kafka:
```xml
<dependency>
  <groupId>org.springframework.kafka</groupId>
  <artifactId>spring-kafka-test</artifactId>
  <scope>test</scope>
</dependency>
```
## 📚 Classes in This Module

Class	Purpose
KafkaListener	Receives messages and delegates to handler
KafkaEventHandler<T>	Interface for microservices to implement
KafkaProducerService<T>	Sends messages to Kafka topics
KafkaConfig	Configures Kafka producer/consumer factories

## 🛡️ Error Handling

This module ensures reliable message processing with manual acknowledgment and robust error reporting.

- ✅ Messages are acknowledged **only after successful handling**
- 🛑 If an exception occurs:
    - The error is logged with full context (event, partition, offset, key)
    - The exception is rethrown to trigger **Kafka retries** or **dead-letter queue (DLQ)** behavior
- 📊 You can extend the listener to:
    - Forward failed events to a DLQ topic
    - Report errors to monitoring systems like Sentry, Datadog, or Prometheus

## 📦 How to Use
Add this module as a dependency in your microservice:

```xml
<dependency>
  <groupId>com.devsocket.ecommerce</groupId>
  <artifactId>common-kafka</artifactId>
  <version>1.0.0</version>
</dependency>
```

## 🤝 Contributing
Feel free to open issues or submit PRs to improve flexibility, add support for Avro/Protobuf, or extend topic routing.