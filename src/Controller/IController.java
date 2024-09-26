package Controller;

import Models.ConvocatoriaExamen;
import Models.Dificultad;
import Models.Enunciado;
import Models.UnidadDidactica;
import java.sql.Date;
import java.util.ArrayList;

public interface IController {

    public boolean crearUnidad(UnidadDidactica uniDi);

    public boolean crearConvocatoria(ConvocatoriaExamen convExamen);

    public boolean crearEnunciado(Enunciado enu);

    public ArrayList<Enunciado> consultarEnunciadosPorUnidad(String str);

    public void consultarConvocatoriasConEnunciado();

    public void visualizarTextoEnunciado(int enuID);

    public void asignarEnunciadoConvocatoria();

}
