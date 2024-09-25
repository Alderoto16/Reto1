package Controller;

import Models.ConvocatoriaExamen;
import Models.Dificultad;
import Models.Enunciado;
import Models.UnidadDidactica;
import java.sql.Date;

public interface IController {

    public boolean crearUnidad(UnidadDidactica uniDi);

    public boolean crearConvocatoria(ConvocatoriaExamen convExamen);

    public boolean crearEnunciado(Enunciado enu);

    public void consultarEnunciadosPorUnidad();

    public void consultarConvocatoriasConEnunciado();

    public void visualizarTextoEnunciado(int enuID);

    public void asignarEnunciadoConvocatoria();

}
