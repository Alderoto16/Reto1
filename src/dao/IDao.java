/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Models.ConvocatoriaExamen;
import Models.Enunciado;
import Models.UnidadDidactica;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public interface IDao {

    public boolean crearUnidad(UnidadDidactica uniDi);

    public boolean crearConvocatoria(ConvocatoriaExamen convExamen);

    public boolean crearEnunciado(Enunciado enu);

    public void consultarEnunciadosPorUnidad();

    public void consultarConvocatoriasConEnunciado();

    public ArrayList<Integer> getEnunciadosIDList();

    public String getFilePathFromDatabase(int id);

    public void asignarEnunciadoConvocatoria();

}
