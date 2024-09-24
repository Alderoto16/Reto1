package Controller;

import Models.Dificultad;
import Models.UnidadDidactica;

import java.sql.Date;


import Models.Enunciado;

public interface IController {
    
     public void crearUnidad();
     public void crearConvocatoria();
     public void crearEnunciado();
     public Enunciado consultarEnunciadosPorUnidad (int id);

     public void consultarConvocatoriasConEnunciado();
     public void visualizarTextoEnunciado();
     public void asignarEnunciadoConvocatoria();
         
    
    
}
