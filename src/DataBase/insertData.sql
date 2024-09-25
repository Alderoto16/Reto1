-- Insert data into UnidadDidactica table
INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion)
VALUES 
('MATH101', 'Mathematics I', 'Final Exam', 'Basic algebra and geometry concepts'),
('PHY102', 'Physics I', 'Midterm Exam', 'Introduction to mechanics and motion'),
('CS103', 'Computer Science I', 'Project', 'Introduction to programming with Java');

-- Insert data into Enunciado table
INSERT INTO Enunciado (descripcion, nivel, disponible, ruta)
VALUES 
('Solve for x in the equation 2x + 3 = 7', 'MEDIO', TRUE, 'src/enunciados/enunciado1.docx'),
('Describe the laws of motion by Newton', 'ALTO', TRUE, 'src/enunciados/enunciado2.docx'),
('Write a simple program to calculate the sum of two numbers', 'BAJO', TRUE, 'src/enunciados/enunciado3.docx');

-- Insert data into ConvocatoriaExamen table
INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso, enunciadoID)
VALUES 
('2024-JAN-MATH101', 'January Exam for Math 101', '2024-01-15', 'Mathematics I', 1),
('2024-MAY-PHY102', 'May Exam for Physics I', '2024-05-20', 'Physics I', 2),
('2024-JUN-CS103', 'June Project for Computer Science I', '2024-06-25', 'Computer Science I', 3);

-- Insert data into UnidadDidactica_Enunciado table (many-to-many relationship)
INSERT INTO UnidadDidactica_Enunciado (unidad_id, enunciado_id)
VALUES 
(1, 1),  -- Math 101 linked to its enunciado
(2, 2),  -- Physics 102 linked to its enunciado
(3, 3);  -- Computer Science 103 linked to its enunciado
