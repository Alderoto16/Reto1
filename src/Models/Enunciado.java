package Models;

import Models.Dificultad;


public class Enunciado {
    private int id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;
    private String convocatoriaExamen;

    public Enunciado(int id, String descripcion, Dificultad dificultad, boolean disponible, String ruta, String convocatoriaExamen) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = dificultad;
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

    public String getConvocatoriaExamen() {
        return convocatoriaExamen;
    }

    public void setConvocatoriaExamen(String convocatoriaExamen) {
        this.convocatoriaExamen = convocatoriaExamen;
    }

    @Override
    public String toString() {
        System.out.println("Enunciado: ");
        System.out.println("ID: "+id);
        System.out.println("Descripción: "+descripcion);
        System.out.println("Nivel: "+nivel);
        System.out.println("Disponible: "+disponible);
        System.out.println("Ruta: "+ruta);
        System.out.println("Convocatoria examen: "+convocatoriaExamen);
        
        
        
        
        return "";
    }
    
    

}
