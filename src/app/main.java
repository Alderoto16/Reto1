package app;

import Controller.Controller;
import Models.ConvocatoriaExamen;
import Models.Dificultad;
import Models.Enunciado;
import Models.UnidadDidactica;
import Utilidades.Util;
import java.time.LocalDate;
import java.util.ArrayList;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller controller = new Controller();
        int menu;
        do {
            showMenu();
            menu = Util.leerInt("¿Que desea hacer?", 0, 6);
            switch (menu) {

                case 1:
                    controller.crearUnidad(solicitarDatosUnidad());
                    break;
                case 2:
                    controller.crearConvocatoria(solicitarDatosConvocatorias(controller));
                    break;
                case 3:
                    controller.crearEnunciado(solicitarDatosEnunciado(controller));
                    break;
                case 4:
                    consultarEnunciadosPorUnidad(controller);
                    break;
                case 5:
                    // consultar convocatorias
                    break;
                case 6:
                    visualizarDocEnunciado(controller);
                    break;
                case 7:
                   controller.asignarEnunciadoConvocatoria();
                    break;
                case 0:
                    break;
            }
        } while (menu != 0);
    }

    public static void showMenu() {
        System.out.println("1. Crear una unidad didáctica (Unidad) y crear una Convocatoria de examen");
        System.out.println("2 .Crear un enunciado de examen agregando las unidades didácticas que va a referir. También\n"
                        + "se asociará a este enunciado la convocatoria para la que se crea.");
        System.out.println("3. Consultar los enunciados de examen en los que se trata una unidad didáctica concreta.");
        System.out.println("4. Consultar en que convocatorias se ha utilizado un enunciado concreto.");
        System.out.println("5. Visualizar el documento de texto asociado a un enunciado");
        System.out.println("6. Asignar un enunciado a una convocatoria.");
        System.out.println("7. ...");
        System.out.println("8. ...");
    }

    public static UnidadDidactica solicitarDatosUnidad() {
        System.out.println("Introduce el acronimo de la unidad:");
        String acronimo = Util.introducirCadena();
        System.out.println("Introduce el titulo de la unidad:");
        String titulo = Util.introducirCadena();
        System.out.println("Introduce la evaluacion de la unidad:");
        String evaluacion = Util.introducirCadena();
        System.out.println("Introduce la descripcion de la unidad:");
        String descripcion = Util.introducirCadena();

        return new UnidadDidactica(acronimo, titulo, evaluacion, descripcion);

    }

    public static void consultarEnunciadosPorUnidad(Controller controller) {
        String unidAcronim;
        ArrayList<Enunciado> enunciadosLista = new ArrayList();
        unidAcronim = Utilidades.Util.introducirCadena("Introduce el acronimo de la unidad: ");
        enunciadosLista = controller.consultarEnunciadosPorUnidad(unidAcronim);

        if (!enunciadosLista.isEmpty()) {
            for (Enunciado enunciado : enunciadosLista) {
                System.out.println("");
                System.out.println(enunciado);
            }
        } else {
            System.out.println("Ese enunciado de examen no existe");
        }
    }

  

    public static Enunciado solicitarDatosEnunciado(Controller controller) {
        String descripcion = Util.introducirCadena("Introduce la descripcion del enunciado: ");
        Dificultad nivel = Util.stringToEnum();
        System.out.println("¿Está disponible el enunciado? (true/false): ");
        boolean disponible = Util.leerBooleano();
        String titulo = Util.introducirCadena("Introduce titulo del enunciado: ");
        String ruta = controller.createFile(titulo);

        return new Enunciado(descripcion, nivel, disponible, ruta);
    }

    public static ConvocatoriaExamen solicitarDatosConvocatorias(Controller controller) {
        String convocatoria;
        while (true) {
            System.out.println("Introduce el id de la convocatoria:");
            convocatoria = Util.introducirCadena();
            if (!controller.isConvocatoriaExists(convocatoria)) {
                break;
            }
            System.out.println("La convocatoria ya existe. Por favor, introduce un ID único.");
        }

        String descripcion = Util.introducirCadena("Introduce el descripcion:");
        System.out.println("Introduce la fecha:");
        LocalDate localDate = Util.leerFechaAMD();
        // Date fecha = Date.valueOf(localDate);
        String curso = Util.introducirCadena("Introduce el curso:");
        //  int enunciadoID = Util.leerInt("Introduce el id del enunciado:");
        return new ConvocatoriaExamen(convocatoria, descripcion, localDate, curso);
    }

    public static void visualizarDocEnunciado(Controller controller) {
        ArrayList<Integer> enunciadosIDList = controller.getEnunciadosIDList();
        System.out.println(" ID de enunciados en DB : " + enunciadosIDList);

        int enuID = Util.leerInt("Introduce la id del Enunciado: ");
        if (enunciadosIDList.contains(enuID)) {
            controller.visualizarTextoEnunciado(enuID);
        } else {
            System.out.println("ID NO exist");
        }
    }
    
    

}// end of main 
