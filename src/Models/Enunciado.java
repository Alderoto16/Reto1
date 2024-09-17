package Models;

public class Enunciado {
    private int id;
    private String descripcion;
    private Nivel nivel;
    private boolean disponible;
    private String ruta;
    private String convocatoriaExamen;

    // Enumeración para el campo nivel
    public enum Nivel {
        ALTA, MEDIA, BAJA
    }

    // Constructor
    public Enunciado(int id, String descripcion, Nivel nivel, boolean disponible, String ruta, String convocatoriaExamen) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.disponible = disponible;
        this.ruta = ruta;
        this.convocatoriaExamen = convocatoriaExamen;
    }
    
    public Enunciado(){
    };

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

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
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

    public String getConvocatoriaExamen() {
        return convocatoriaExamen;
    }

    public void setConvocatoriaExamen(String convocatoriaExamen) {
        this.convocatoriaExamen = convocatoriaExamen;
    }

}
