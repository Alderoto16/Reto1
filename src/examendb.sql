CREATE TABLE IF NOT EXISTS examendb;
USE examendb;

CREATE TABLE IF NOT EXISTS UnidadDidactica (
    id INTEGER PRIMARY KEY,
    acronimo VARCHAR(50),
    titulo VARCHAR(100),
    evaluacion VARCHAR(50),
    descripcion VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS ConvocatoriaExamen (
    convocatoria VARCHAR(50) PRIMARY KEY,
    descripcion VARCHAR(100),
    fecha DATE NOT NULL,
    curso VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Enunciado (
    id INTEGER PRIMARY KEY,
    descripcion TEXT NOT NULL,
     nivel ENUM('ALTA', 'MEDIA', 'BAJA'),
    disponible BOOLEAN DEFAULT TRUE,
    ruta VARCHAR(255),
    convocatoria_examen VARCHAR(50),
    FOREIGN KEY (convocatoria_examen) REFERENCES ConvocatoriaExamen(convocatoria)
);

CREATE TABLE IF NOT EXISTS UnidadDidactica_Enunciado (
    unidad_id INTEGER,
    enunciado_id INTEGER,
    PRIMARY KEY (unidad_id, enunciado_id),
    FOREIGN KEY (unidad_id) REFERENCES UnidadDidactica(id),
    FOREIGN KEY (enunciado_id) REFERENCES Enunciado(id)
);
