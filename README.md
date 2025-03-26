# Client API

API RESTful desarrollada con **Spring Boot 3.3.1**, **Java 17** y **MySQL**, para la gestión de clientes (CRUD). Incluye documentación generada automáticamente con Swagger (OpenAPI 3).

## 🧰 Tecnologías

- Java 17
- Spring Boot 3.3.1
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Validation
- MySQL
- Swagger (springdoc-openapi 2.6.0)
- JUnit & Mockito para pruebas unitarias

## 🚀 Requisitos previos

- JDK 17
- Maven 3.x
- MySQL
- IDE (IntelliJ, Eclipse, VS Code)

## ⚙️ Configuración

1. **Crea la base de datos en MySQL:**

```sql
CREATE DATABASE IF NOT EXISTS clientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE clientes;

-- Crear la tabla `clientes`
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL
);

INSERT INTO clientes (nombre, email, telefono) VALUES
('Juan Pérez', 'juan@example.com', '5551234567'),
('Ana Gómez', 'ana@example.com', '5559876543'),
('Carlos López', 'carlos@example.com', '5554567890');
```

## ⚙️ Pruebas unitarias

1. **Ejecuta el comando:**
```
.\mvnw test
```

## ⚙️ Contruye el proyecto
1. **Ejecuta el comando:**
```
.\mvnw clean install
```
2. **Finalmente corre el proyecto springboot**
```
./mvnw spring-boot:run
```

## Documentacion
Para visitar los endpoints de la API RESTFUL una vez ejecutado el proyecto accede a
```
http://localhost:8080/swagger-ui/index.html/
```

Y para visualizar el json de la documentacion visita
```
http://localhost:8080/v3/api-docs
```
