create database examendb;
use examendb;

CREATE TABLE UnidadDidactica (
    id SERIAL PRIMARY KEY,
    acronimo VARCHAR(50),
    titulo VARCHAR(100),
    evaluacion VARCHAR(50),
    descripcion VARCHAR(100)
);

CREATE TABLE ConvocatoriaExamen (
    convocatoria VARCHAR(50) PRIMARY KEY,
    descripcion VARCHAR(100),
    fecha DATE NOT NULL,
    curso VARCHAR(100)
);

CREATE TABLE Enunciado (
    id SERIAL PRIMARY KEY,
    descripcion TEXT NOT NULL,
     nivel ENUM('ALTA', 'MEDIA', 'BAJA'),
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
