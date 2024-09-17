package Controller;

import Models.Dificultad;
import Models.UnidadDidactica;

public interface IController {
    
      public boolean crearUnidad();
     public void crearConvocatoria();
     public void crearEnunciado();
     public void consultarEnunciadosPorUnidad ();
     public void consultarConvocatoriasConEnunciado();
     public void visualizarTextoEnunciado();
     public void asignarEnunciadoConvocatoria();
         
    
    
}
