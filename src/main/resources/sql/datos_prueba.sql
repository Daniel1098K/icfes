-- ========================================
-- SCRIPT DE INSERCIÓN DE DATOS
-- Sistema Saber Pro UTS
-- ========================================

-- ========================================
-- 1. INSERTAR USUARIOS
-- ========================================

-- Coordinador Principal (Password encriptada con BCrypt: "coord2024")
-- Puedes usar este coordinador o mantener el que ya tienes
INSERT INTO usuarios (username, password, nombre_completo, email, rol, activo, fecha_creacion) 
VALUES 
('yezid.garcia', '$2a$10$xn3LI/AjqicFYZFzXKXKi.4QOqS5vMBY2z0xKCzIrKpKl7uiXWBGO', 
 'Ing. Yezid Yair García', 'yezid.garcia@uts.edu.co', 'COORDINACION', true, NOW());

-- Usuarios estudiantes (Password: "est2024" para todos)
-- Los crearemos después de insertar los estudiantes

-- ========================================
-- 2. INSERTAR ESTUDIANTES
-- ========================================

-- Limpiar datos existentes (CUIDADO: solo usar en desarrollo)
-- DELETE FROM resultados_saber_pro;
-- DELETE FROM estudiantes;

INSERT INTO estudiantes 
(numero_documento, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, 
 correo_electronico, numero_telefonico, numero_registro, tipo_programa, programa, fecha_registro)
VALUES
-- Estudiante 1: BARBOSA
('1098700001', 'BARBOSA', NULL, 'BARBOSA', NULL, 
 'EK2018907722@correo.uts.edu.co', NULL, 'EK2018907722', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 2: QUINTERO
('1098700002', 'QUINTERO', NULL, 'QUINTERO', NULL, 
 'EK2013410013@correo.uts.edu.co', NULL, 'EK2013410013', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 3: GIL
('1098700003', 'GIL', NULL, 'GIL', NULL, 
 'EK2018014660@correo.uts.edu.co', NULL, 'EK2018014660', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 4: ANAYA
('1098700004', 'ANAYA', NULL, 'ANAYA', NULL, 
 'EK2018025381@correo.uts.edu.co', NULL, 'EK2018025381', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 5: FLOR
('1098700005', 'FLOR', NULL, 'FLOR', NULL, 
 'EK2019925325@correo.uts.edu.co', NULL, 'EK2019925325', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 6: GARCIA
('1098700006', 'GARCIA', NULL, 'GARCIA', NULL, 
 'EK2018312548@correo.uts.edu.co', NULL, 'EK2018312548', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 7: MALDONADO VA
('1098700007', 'MALDONADO', 'VA', 'MALDONADO', NULL, 
 'EK2019304665@correo.uts.edu.co', NULL, 'EK2019304665', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 8: DIAZ
('1098700008', 'DIAZ', NULL, 'DIAZ', NULL, 
 'EK2018907885@correo.uts.edu.co', NULL, 'EK2018907885', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 9: BELTRAN
('1098700009', 'BELTRAN', NULL, 'BELTRAN', NULL, 
 'EK2019323620@correo.uts.edu.co', NULL, 'EK2019323620', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 10: SANTAMARIA
('1098700010', 'SANTAMARIA', NULL, 'SANTAMARIA', NULL, 
 'EK2019305016@correo.uts.edu.co', NULL, 'EK2019305016', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 11: SANCHEZ
('1098700011', 'SANCHEZ', NULL, 'SANCHEZ', NULL, 
 'EK2019047073@correo.uts.edu.co', NULL, 'EK2019047073', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 12: ROMERO
('1098700012', 'ROMERO', NULL, 'ROMERO', NULL, 
 'EK2017403647@correo.uts.edu.co', NULL, 'EK2017403647', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 13: DIAZ
('1098700013', 'DIAZ', NULL, 'DIAZ', NULL, 
 'EK2018317811@correo.uts.edu.co', NULL, 'EK2018317811', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 14: TRIANA
('1098700014', 'TRIANA', NULL, 'TRIANA', NULL, 
 'EK2018317801@correo.uts.edu.co', NULL, 'EK2018317801', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 15: SUAREZ
('1098700015', 'SUAREZ', NULL, 'SUAREZ', NULL, 
 'EK2019317666@correo.uts.edu.co', NULL, 'EK2019317666', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 16: GARCIA
('1098700016', 'GARCIA', NULL, 'GARCIA', NULL, 
 'EK2015026427@correo.uts.edu.co', NULL, 'EK2015026427', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 17: PINZON
('1098700017', 'PINZON', NULL, 'PINZON', NULL, 
 'EK2018318290@correo.uts.edu.co', NULL, 'EK2018318290', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 18: VERA
('1098700018', 'VERA', NULL, 'VERA', NULL, 
 'EK2019318301@correo.uts.edu.co', NULL, 'EK2019318301', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 19: NIÑO
('1098700019', 'NIÑO', NULL, 'NIÑO', NULL, 
 'EK2018009565@correo.uts.edu.co', NULL, 'EK2018009565', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 20: PARODI
('1098700020', 'PARODI', NULL, 'PARODI', NULL, 
 'EK2018013716@correo.uts.edu.co', NULL, 'EK2018013716', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 21: HERNANDEZ
('1098700021', 'HERNANDEZ', NULL, 'HERNANDEZ', NULL, 
 'EK2018044579@correo.uts.edu.co', NULL, 'EK2018044579', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 22: LARIOS
('1098700022', 'LARIOS', NULL, 'LARIOS', NULL, 
 'EK2019041700@correo.uts.edu.co', NULL, 'EK2019041700', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 23: SANDOVAL
('1098700023', 'SANDOVAL', NULL, 'SANDOVAL', NULL, 
 'EK2019318334@correo.uts.edu.co', NULL, 'EK2019318334', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 24: VILLARREAL
('1098700024', 'VILLARREAL', NULL, 'VILLARREAL', NULL, 
 'EK2018041521@correo.uts.edu.co', NULL, 'EK2018041521', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 25: RESTREPO
('1098700025', 'RESTREPO', NULL, 'RESTREPO', NULL, 
 'EK2019027636@correo.uts.edu.co', NULL, 'EK2019027636', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 26: CACERES
('1098700026', 'CACERES', NULL, 'CACERES', NULL, 
 'EK2018031092@correo.uts.edu.co', NULL, 'EK2018031092', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 27: TABARES
('1098700027', 'TABARES', NULL, 'TABARES', NULL, 
 'EK2019304153@correo.uts.edu.co', NULL, 'EK2019304153', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 28: MEJIA
('1098700028', 'MEJIA', NULL, 'MEJIA', NULL, 
 'EK2018018792@correo.uts.edu.co', NULL, 'EK2018018792', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 29: PRADA
('1098700029', 'PRADA', NULL, 'PRADA', NULL, 
 'EK2018024754@correo.uts.edu.co', NULL, 'EK2018024754', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 30: VARGAS
('1098700030', 'VARGAS', NULL, 'VARGAS', NULL, 
 'EK2018018620@correo.uts.edu.co', NULL, 'EK2018018620', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 31: TORRES
('1098700031', 'TORRES', NULL, 'TORRES', NULL, 
 'EK2019318210@correo.uts.edu.co', NULL, 'EK2019318210', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 32: ORTIZ
('1098700032', 'ORTIZ', NULL, 'ORTIZ', NULL, 
 'EK2018303135@correo.uts.edu.co', NULL, 'EK2018303135', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 33: SALAMAZAR
('1098700033', 'SALAMAZAR', NULL, 'SALAMAZAR', NULL, 
 'EK2019217790@correo.uts.edu.co', NULL, 'EK2019217790', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 34: RESTREPO
('1098700034', 'RESTREPO', NULL, 'RESTREPO', NULL, 
 'EK2018028123@correo.uts.edu.co', NULL, 'EK2018028123', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 35: ANULADO
('1098700035', 'ANULADO', NULL, 'ANULADO', NULL, 
 'EK2018520870@correo.uts.edu.co', NULL, 'EK2018520870', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE()),

-- Estudiante 36: MATIZ
('1098700036', 'MATIZ', NULL, 'MATIZ', NULL, 
 'EK2018144329@correo.uts.edu.co', NULL, 'EK2018144329', 'PROFESIONAL', 'Ingeniería de Sistemas', CURDATE());

-- ========================================
-- 3. INSERTAR RESULTADOS SABER PRO
-- ========================================

-- Obtener IDs de estudiantes y insertar resultados
-- Nota: Ajusta los datos según corresponda

INSERT INTO resultados_saber_pro 
(estudiante_id, puntaje, 
 comunicacion_escrita, comunicacion_escrita_nivel,
 razonamiento_cuantitativo, razonamiento_cuantitativo_nivel,
 lectura_critica, lectura_critica_nivel,
 competencias_ciudadanas, competencias_ciudadanas_nivel,
 ingles, ingles_nivel,
 formulacion_proyectos, formulacion_proyectos_nivel,
 pensamiento_cientifico, pensamiento_cientifico_nivel,
 diseno_software, diseno_software_nivel,
 fecha_presentacion, fecha_registro_resultado)
SELECT 
    e.id,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 200
        WHEN 'EK2013410013' THEN 165
        WHEN 'EK2018014660' THEN 167
        WHEN 'EK2018025381' THEN 166
        WHEN 'EK2019925325' THEN 160
        WHEN 'EK2018312548' THEN 157
        WHEN 'EK2019304665' THEN 161
        WHEN 'EK2018907885' THEN 156
        WHEN 'EK2019323620' THEN 154
        WHEN 'EK2019305016' THEN 160
        WHEN 'EK2019047073' THEN 145
        WHEN 'EK2017403647' THEN 146
        WHEN 'EK2018317811' THEN 144
        WHEN 'EK2018317801' THEN 141
        WHEN 'EK2019317666' THEN 140
        WHEN 'EK2015026427' THEN 135
        WHEN 'EK2018318290' THEN 138
        WHEN 'EK2019318301' THEN 135
        WHEN 'EK2018009565' THEN 134
        WHEN 'EK2018013716' THEN 133
        WHEN 'EK2018044579' THEN 132
        WHEN 'EK2019041700' THEN 131
        WHEN 'EK2019318334' THEN 130
        WHEN 'EK2018041521' THEN 129
        WHEN 'EK2019027636' THEN 126
        WHEN 'EK2018031092' THEN 125
        WHEN 'EK2019304153' THEN 124
        WHEN 'EK2018018792' THEN 122
        WHEN 'EK2018024754' THEN 122
        WHEN 'EK2018018620' THEN 114
        WHEN 'EK2019318210' THEN 113
        WHEN 'EK2018303135' THEN 109
        WHEN 'EK2019217790' THEN 107
        WHEN 'EK2018028123' THEN 0
        ELSE 150
    END as puntaje,
    -- Comunicación Escrita
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 128
        WHEN 'EK2013410013' THEN 125
        WHEN 'EK2018014660' THEN 132
        ELSE 120
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 2
        WHEN 'EK2013410013' THEN 1
        ELSE 2
    END,
    -- Razonamiento Cuantitativo
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 182
        WHEN 'EK2013410013' THEN 151
        ELSE 140
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 2
        ELSE 2
    END,
    -- Lectura Crítica
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 202
        WHEN 'EK2013410013' THEN 178
        ELSE 150
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 3
        ELSE 2
    END,
    -- Competencias Ciudadanas
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 206
        WHEN 'EK2013410013' THEN 163
        ELSE 145
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 3
        ELSE 2
    END,
    -- Inglés
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 183
        WHEN 'EK2013410013' THEN 205
        ELSE 150
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 3
        ELSE 2
    END,
    -- Formulación de Proyectos
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 185
        WHEN 'EK2013410013' THEN 182
        ELSE 140
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 3
        ELSE 2
    END,
    -- Pensamiento Científico
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 160
        WHEN 'EK2013410013' THEN 143
        ELSE 135
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 2
        ELSE 2
    END,
    -- Diseño de Software
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 197
        WHEN 'EK2013410013' THEN 136
        ELSE 130
    END,
    CASE e.numero_registro
        WHEN 'EK2018907722' THEN 3
        WHEN 'EK2013410013' THEN 2
        ELSE 2
    END,
    '2024-05-15',
    CURDATE()
FROM estudiantes e;

-- ========================================
-- 4. VERIFICACIÓN DE DATOS
-- ========================================

-- Ver todos los estudiantes insertados
SELECT 
    COUNT(*) as total_estudiantes,
    tipo_programa
FROM estudiantes
GROUP BY tipo_programa;

-- Ver estudiantes con sus resultados
SELECT 
    e.numero_registro,
    CONCAT(e.primer_nombre, ' ', e.primer_apellido) as nombre,
    r.puntaje,
    r.nivel_beneficio,
    r.puede_graduar
FROM estudiantes e
LEFT JOIN resultados_saber_pro r ON e.id = r.estudiante_id
ORDER BY r.puntaje DESC;

-- Ver estadísticas generales
SELECT 
    COUNT(*) as total,
    AVG(puntaje) as promedio,
    MAX(puntaje) as maximo,
    MIN(puntaje) as minimo
FROM resultados_saber_pro;
