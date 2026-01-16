# Metrics Lab

This repository is a hands-on learning project focused on **observability with Spring Boot**, using
**Actuator, Micrometer, Prometheus, and Grafana**.

The main goal is to understand — step by step — how metrics are:
- created inside a Spring Boot application
- exposed through Actuator
- later collected and visualized by external systems

---

## 🛠️ Tech Stack

- Java 25 (Amazon Corretto)
- Spring Boot 4.0.1
- Gradle (Kotlin DSL)
- Spring Boot Actuator
- Micrometer (metrics facade)
- Docker (next steps)
- Prometheus & Grafana (next steps)

---

## 📌 What has been done so far

### Step 1 – Project setup

- Created a Spring Boot project using **Gradle with Kotlin DSL**
- Configured Java 25 using Amazon Corretto
- Added base dependencies:
    - Spring Web
    - Spring Boot Actuator
- Created and pushed a clean baseline repository to GitHub

This establishes a stable starting point for all future steps.

---

### Step 2 – Actuator exposure and metrics exploration

Before introducing Prometheus or any external monitoring tool, the focus was on
understanding what Spring Boot already provides **out of the box**.

At first, Actuator endpoints are **restricted by default**, exposing only minimal information.

---

## 🔍 Actuator behavior before and after configuration

By default, Spring Boot Actuator exposes only a minimal set of endpoints.
This behavior is intentional and aligned with production security best practices.

---

### Actuator root — default behavior

GET /actuator

![Actuator before configuration](images/step-03-actuator/actuator-before-application-yaml-config.png)

At this stage, only a limited set of endpoints is visible.

---

## ⚙️ Actuator configuration

To safely explore metrics, a restricted set of endpoints was explicitly exposed
using `application.yml`:

```yaml
spring:
  application:
    name: metrics-lab

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

Only the endpoints required for learning and validation were enabled.

---

## Actuator root — after configuration

After configuring `application.yml`, additional endpoints become visible:

```
GET /actuator
```

![Actuator after configuration](images/step-03-actuator/actuator-after-application-yaml-config.png)

This confirms that Actuator exposure is **explicit and controlled**.

---

### Health endpoint

The health endpoint verifies that the application is running correctly:

```
GET /actuator/health
```

![Health endpoint](images/step-03-actuator/health-endpoint.png)

---

### Metrics endpoint

The **metrics endpoint** lists all metric names registered by **Micrometer**:
```
GET /actuator/metrics
```

![Metrics endpoint](images/step-03-actuator/metrics-endpoint.png)

At this stage, metrics already include:
- JVM memory and threads
- CPU usage
- HTTP server requests
- 
❗ No custom metrics were created yet.

---

### ☕ JVM Metric Example

Metrics can be queried individually. Example:

```
GET /actuator/metrics/jvm.memory.used
```

![JVM Memory Used Metric](images/step-03-actuator/jvm-metric.png)

This endpoint shows:
- Current memory usage
- Available tags such as:
    - heap / non-heap
    - memory pools

---

### 🧠 Key Learnings So Far

- Metrics already exist **before** Prometheus or Grafana
- Spring Boot **autoconfigures Micrometer internally**
- Actuator is responsible for **exposing**, not **exporting**, metrics
- Metrics are composed of:
    - a **name**
    - one or more **measurements**
    - a set of **tags**
- HTTP and JVM metrics are available **without writing any metric-related code**

---

## 🚧 Next Steps

Planned improvements for this project include:

- Adding the Micrometer Prometheus registry
- Exposing the `/actuator/prometheus` endpoint
- Running Prometheus and Grafana using Docker
- Creating custom application-level metrics

Each step will be documented with configuration, validation, and learnings.