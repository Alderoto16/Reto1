create database examendb;
use examendb;

CREATE TYPE Dificultad AS ENUM ('ALTA', 'MEDIA', 'BAJA');

CREATE TABLE UnidadDidactica (
    id SERIAL PRIMARY KEY,
    acronimo VARCHAR(50) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    evaluacion VARCHAR(50),
    descripcion TEXT
);

CREATE TABLE ConvocatoriaExamen (
    convocatoria VARCHAR(50) PRIMARY KEY,
    descripcion TEXT,
    fecha DATE NOT NULL,
    curso VARCHAR(100)
);

CREATE TABLE Enunciado (
    id SERIAL PRIMARY KEY,
    descripcion TEXT NOT NULL,
    nivel Dificultad NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    ruta VARCHAR(255),
    convocatoria_examen VARCHAR(50),
    FOREIGN KEY (convocatoria_examen) REFERENCES ConvocatoriaExamen(convocatoria)
);

CREATE TABLE UnidadDidactica_Enunciado (
    unidad_id INT,
    enunciado_id INT,
    PRIMARY KEY (unidad_id, enunciado_id),
    FOREIGN KEY (unidad_id) REFERENCES UnidadDidactica(id),
    FOREIGN KEY (enunciado_id) REFERENCES Enunciado(id)
);
