package Controller;

import Models.Dificultad;
import Models.UnidadDidactica;
import java.sql.Date;

public interface IController {
    
     public boolean crearUnidad();
     public boolean crearConvocatoria();
     public boolean crearEnunciado();
     public void consultarEnunciadosPorUnidad ();
     public void consultarConvocatoriasConEnunciado();
     public void visualizarTextoEnunciado();


    public void  asignarEnunciadoConvocatoria(int enunciadoId, String convocatoriaId);
         
    
    
}
