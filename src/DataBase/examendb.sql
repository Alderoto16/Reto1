CREATE DATABASE IF NOT EXISTS examendb;
USE examendb;

CREATE TABLE IF NOT EXISTS UnidadDidactica (
    id INTEGER PRIMARY KEY,
    acronimo VARCHAR(50),
    titulo VARCHAR(100),
    evaluacion VARCHAR(50),
    descripcion VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Enunciado (
    id INTEGER PRIMARY KEY,
    descripcion TEXT NOT NULL,
	nivel ENUM('ALTO', 'MEDIO', 'BAJO'),
    disponible BOOLEAN DEFAULT TRUE,
    ruta VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ConvocatoriaExamen (
    convocatoria VARCHAR(50) PRIMARY KEY,
    descripcion VARCHAR(100),
    fecha DATE NOT NULL,
    curso VARCHAR(100),
    enunciadoID INTEGER,
     FOREIGN KEY (enunciadoID) REFERENCES Enunciado(id)
);

CREATE TABLE IF NOT EXISTS UnidadDidactica_Enunciado (
    unidad_id INTEGER primary key,
    enunciado_id INTEGER,
    FOREIGN KEY (unidad_id) REFERENCES UnidadDidactica(id),
    FOREIGN KEY (enunciado_id) REFERENCES Enunciado(id)
);


INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES
(1, 'MAT101', 'Matemáticas Básicas', 'Examen Final', 'Introducción a las matemáticas'),
(2, 'PHY102', 'Física General', 'Examen Parcial', 'Conceptos fundamentales de la física'),
(3, 'CS103', 'Introducción a la Programación', 'Examen Práctico', 'Fundamentos de programación en Python'),
(4, 'HIS104', 'Historia Universal', 'Trabajo Final', 'Análisis de eventos históricos'),
(5, 'ENG105', 'Inglés Básico', 'Examen Oral', 'Curso básico de inglés');

INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso) VALUES
('JUN2024', 'Convocatoria de Junio', '2024-06-15', 'Curso 2023/2024'),
('SEP2024', 'Convocatoria de Septiembre', '2024-09-10', 'Curso 2023/2024'),
('DIC2024', 'Convocatoria de Diciembre', '2024-12-05', 'Curso 2024/2025');

INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta, convocatoria_examen) VALUES
(1, 'Resolver el sistema de ecuaciones lineales', 'ALTA', TRUE, '/enunciados/matematicas/sistema_ecuaciones.pdf', 'JUN2024'),
(2, 'Calcular la energía cinética de un objeto', 'MEDIA', TRUE, '/enunciados/fisica/energia_cinetica.pdf', 'SEP2024'),
(3, 'Escribir un programa en Python que ordene una lista', 'BAJA', TRUE, '/enunciados/programacion/ordenar_lista.py', 'JUN2024'),
(4, 'Analizar las causas de la Revolución Francesa', 'MEDIA', FALSE, '/enunciados/historia/revolucion_francesa.pdf', 'DIC2024'),
(5, 'Realizar una conversación básica en inglés', 'BAJA', TRUE, '/enunciados/ingles/conversacion_basica.pdf', 'JUN2024');

INSERT INTO UnidadDidactica_Enunciado (unidad_id, enunciado_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

