-- Crear base de datos
CREATE DATABASE IF NOT EXISTS icfes
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE icfes;

-- Las tablas se crearán automáticamente con Hibernate
-- Este script es solo para crear la base de datos manually si es necesario

-- Verificar tablas creadas
SHOW TABLES;

-- Consultas útiles para verificación
-- SELECT * FROM usuarios;
-- SELECT * FROM estudiantes;
-- SELECT * FROM resultados_saber_pro;
