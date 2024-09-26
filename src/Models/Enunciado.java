package Models;

import Models.Dificultad;

public class Enunciado {

    private int id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;
    // private String convocatoriaExamen;

    public Enunciado() {
        this.id = 0;
        this.descripcion = "";
        this.nivel = Dificultad.BAJO;  // Example, assuming "FACIL" is an enum value
        this.disponible = false;
        this.ruta = "";
        // this.convocatoriaExamen = convocatoriaExamen;
    }

    public Enunciado(int id, String descripcion, Dificultad dificultad, boolean disponible, String ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = dificultad;
        this.disponible = disponible;
        this.ruta = ruta;
        // this.convocatoriaExamen = convocatoriaExamen;
    }

    public Enunciado(String descripcion, Dificultad dificultad, boolean disponible, String ruta) {
        this.descripcion = descripcion;
        this.nivel = dificultad;
        this.disponible = disponible;
        this.ruta = ruta;
        //this.convocatoriaExamen = convocatoriaExamen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Dificultad getNivel() {
        return nivel;
    }

    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    /*
    public String getConvocatoriaExamen() {
        return convocatoriaExamen;
    }

    public void setConvocatoriaExamen(String convocatoriaExamen) {
        this.convocatoriaExamen = convocatoriaExamen;
    }
     */

    @Override
    public String toString() {
        return "Enunciado{" + "id=" + id + ", descripcion=" + descripcion + ", nivel=" + nivel + ", disponible=" + disponible + ", ruta=" + ruta + '}';
    }
}
