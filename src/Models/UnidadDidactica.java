package Model;

public class UnidadDidactica {
    private int id;
    private String acronimo;
    private String titulo;
    private String evaluacion;
    private String descripcion;

    public UnidadDidactica() {
        this.id = 0;
        this.acronimo = "";
        this.titulo = "";
        this.evaluacion = "";
        this.descripcion = "";
    }

    public UnidadDidactica(int id, String acronimo, String titulo, String evaluacion, String descripcion) {
        this.id = id;
        this.acronimo = acronimo;
        this.titulo = titulo;
        this.evaluacion = evaluacion;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
