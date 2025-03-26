-- Crear la base de datos
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
