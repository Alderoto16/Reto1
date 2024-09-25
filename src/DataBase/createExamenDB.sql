CREATE DATABASE IF NOT EXISTS examendb;
USE examendb;

-- Table for UnidadDidactica (Educational Unit)
CREATE TABLE IF NOT EXISTS UnidadDidactica (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    acronimo VARCHAR(50),          -- Acronym for the unit
    titulo VARCHAR(100),           -- Title of the unit
    evaluacion VARCHAR(50),        -- Evaluation method
    descripcion VARCHAR(100)       -- Description of the unit
);

-- Table for Enunciado (Statement or Question)
CREATE TABLE IF NOT EXISTS Enunciado (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT NOT NULL,     -- Description of the question
    nivel ENUM('ALTO', 'MEDIO', 'BAJO'), -- Difficulty level as an ENUM
    disponible BOOLEAN DEFAULT TRUE, -- Is it available?
    ruta VARCHAR(255)              -- Path to the file or resource
);

-- Table for ConvocatoriaExamen (Exam Announcement)
CREATE TABLE IF NOT EXISTS ConvocatoriaExamen (
    convocatoria VARCHAR(50) PRIMARY KEY, -- Unique identifier for the exam announcement
    descripcion VARCHAR(100),             -- Description of the announcement
    fecha DATE NOT NULL,                  -- Exam date
    curso VARCHAR(100),                   -- Course associated with the exam
    enunciadoID INTEGER,                  -- Reference to the Enunciado table
    FOREIGN KEY (enunciadoID) REFERENCES Enunciado(id)
);

-- Association table for UnidadDidactica and Enunciado (Many-to-many)
CREATE TABLE IF NOT EXISTS UnidadDidactica_Enunciado (
    unidad_id INTEGER ,        -- Reference to UnidadDidactica
    enunciado_id INTEGER,                 -- Reference to Enunciado
    PRIMARY KEY (unidad_id,enunciado_id),
    FOREIGN KEY (unidad_id) REFERENCES UnidadDidactica(id),
    FOREIGN KEY (enunciado_id) REFERENCES Enunciado(id)
);
