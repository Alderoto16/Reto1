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

