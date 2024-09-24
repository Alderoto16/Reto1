package Controller;

import Utilidades.Util;


public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Controller controller = new Controller();

        int menu;
        boolean added;
        do {
            showMenu();
            menu = Util.leerInt("¿Que desea hacer?", 0, 7);
            switch (menu) {

                case 1:
                    added = controller.crearUnidad();
                    System.out.println(added);
                    added = controller.crearConvocatoria();
                    System.out.println(added);
                    break;
                case 2:
                    added = controller.crearEnunciado();
                    System.out.println(added);
                    
                    break;
                case 3:
                    break;
                case 4:
                    controller.consultarEnunciadosPorUnidad();
                    break;
                case 5:
                    break;
                case 6:
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

    }
   

}// end of main 
