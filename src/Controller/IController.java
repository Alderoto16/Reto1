package Controller;

import Models.Dificultad;
import Models.UnidadDidactica;
import java.sql.Date;

public interface IController {
    
      public boolean crearUnidad(int id, String acronimo, String titulo, String evaluacion, String descripcion);
     public boolean crearConvocatoria(String convocatoria, String descripcion, Date fecha, String curso);
     public boolean crearEnunciado();
     public void consultarEnunciadosPorUnidad ();
     public void consultarConvocatoriasConEnunciado();
     public void visualizarTextoEnunciado();
     public void asignarEnunciadoConvocatoria();
         
    
    
}
