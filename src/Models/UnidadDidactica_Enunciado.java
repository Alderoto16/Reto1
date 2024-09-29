/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Alder
 */
public class UnidadDidactica_Enunciado {
    private int unidad_id;
    private int enunciado_id;

    public UnidadDidactica_Enunciado() {
        this.unidad_id = 0;
        this.enunciado_id = 0;
    }
    
    public UnidadDidactica_Enunciado(int unidad_id, int enunciado_id) {
        this.unidad_id = unidad_id;
        this.enunciado_id = enunciado_id;
    }

    public int getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(int unidad_id) {
        this.unidad_id = unidad_id;
    }

    public int getEnunciado_id() {
        return enunciado_id;
    }

    public void setEnunciado_id(int enunciado_id) {
        this.enunciado_id = enunciado_id;
    }
}
