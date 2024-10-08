package Utilidades;

import Models.Dificultad;
import java.io.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Util {

    public static String fechaToString(LocalDate fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String wfecha;

        wfecha = fecha.format(formateador);

        return wfecha;
    }

    public static LocalDate leerFechaDMA() {
        boolean error;
        LocalDate date = null;
        String dateString;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato dd/mm/aaaa ");
                error = true;
            }
        } while (error);
        return date;
    }

    public static LocalDate leerFechaAMD() {
        boolean error;
        LocalDate date = null;
        String dateString;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato aaaa/mm/dd ");
                error = true;
            }
        } while (error);
        return date;
    }

    public static char leerChar(char opt1, char opt2) {
        char letra = ' ';
        String cadena;
        boolean error;
        do {
            error = false;
            cadena = introducirCadena();
            if (cadena.length() != 1) {
                System.out.println("Error, introduce un �nico caracter: ");
                error = true;
            } else {
                letra = cadena.charAt(0);
                letra = Character.toUpperCase(letra);
                if (letra != opt1 && letra != opt2) {
                    System.out.println("Error, la opcion introducida no es correcta, introduce " + opt1 + " o " + opt2);
                    error = true;
                }
            }
        } while (error);

        return letra;
    }

    public static char leerChar() {
        char letra = ' ';
        String cadena;
        boolean error;
        do {
            error = false;
            cadena = introducirCadena();
            if (cadena.length() != 1) {
                System.out.println("Error, introduce un �nico caracter: ");
                error = true;
            }
        } while (error);
        letra = cadena.charAt(0);
        return letra;
    }

    public static float leerFloat() {
        float num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
            }
        } while (error);
        return num;
    }

    public static float leerFloat(String message, float min, float max) {
        float num = 0;
        boolean error;
        System.out.println(message);
        do {
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
                num = min;
            }
            if (num < min || num > max) {
                System.out.println("N� fuera de rango, introduce n� entre " + min + " y " + max + ": ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static float leerFloat(float min, float max) {
        float num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
                num = min;
            }
            if (num < min || num > max) {
                System.out.println("N� fuera de rango, introduce n� entre " + min + " y " + max + ": ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(String message, int min, int max) {
        int num = 0;
        boolean error;
        System.out.println(message);
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
                num = min;
            }
            if (num < min || num > max) {
                System.out.println("N� fuera de rango, introduce n� entre " + min + " y " + max + ": ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(int min, int max) {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
                num = min;
            }
            if (num < min || num > max) {
                System.out.println("N� fuera de rango, introduce n� entre " + min + " y " + max + ": ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(String msg) {
        System.out.println(msg);
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
            }
            // Todos los int son mayor que 0
            if (num < 0) {
                System.out.println("El numero introducido debe ser mayor que 0");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt() {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Valor no num�rico. Introduce de nuevo:");
                error = true;
            }
            // Todos los int son mayor que 0
            if (num < 0) {
                System.out.println("El numero introducido debe ser mayor que 0");
                error = true;
            }
        } while (error);
        return num;
    }

    public static String introducirCadena() {
        String cadena = "";
        boolean error;
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        do {
            error = false;
            try {
                cadena = teclado.readLine();
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            }
        } while (error);
        return cadena;
    }

    public static int calculoFichero(File fich) {
        int cont = 0;
        if (fich.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(fich);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }

            } catch (EOFException e1) {
                System.out.println("Has acabado de leer, tienes " + cont + " objetos");

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos");

            }
        }
        return cont;
    }

    // ** added to facilitate working with files and LocalDateTime
    public static <T> void arrayToFile(ArrayList<T> miList, File fich) {
        ObjectOutputStream oos = null;
        try {
            if (fich.exists()) {
                fich.delete();
            }
            oos = new ObjectOutputStream(new FileOutputStream(fich));
            for (T obj : miList) {
                oos.writeObject(obj);
            }
            miList.clear();
            oos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static <T> void fileToArray(File fich, ArrayList<T> miList) {
        if (fich.exists()) {
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(new FileInputStream(fich));

                int cuantos = calculoFichero(fich);
                for (int i = 0; i < cuantos; i++) {
                    T obj = (T) ois.readObject();
                    miList.add(obj);
                }

                /*T obj = (T) ois.readObject();
				while (obj != null) {
					miList.add(obj);
					obj = (T) ois.readObject();
					miList.add(obj);
				}*/
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static LocalDateTime leerFechaAMDH() {
        boolean error;
        LocalDateTime date = null;
        String dateString;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDateTime.parse(dateString, formateador);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato (aaaa/mm/dd HH:mm)");
                error = true;
            }
        } while (error);
        return date;
    }

    public static Dificultad stringToEnum() {
        while (true) {
            System.out.print("Introduce el nivel (BAJO, MEDIO, ALTO): ");
            String input = introducirCadena().toUpperCase(); // Convierte a mayúsculas para manejar casos de entrada

            try {
                // Intentar convertir el String a un valor del enum
                return Dificultad.valueOf(input);
            } catch (IllegalArgumentException e) {
                // Si el valor no es válido, se captura la excepción y se muestra un mensaje
                System.out.println("Valor no válido. Por favor, introduce uno de los siguientes valores: BAJO, MEDIO, ALTO.");
            }
        }
    }

    public static boolean leerBooleano() {

        while (true) {
            System.out.print("Introduce 'true' o 'false': ");
            String input = introducirCadena().trim().toLowerCase(); // Leer y normalizar la entrada

            if ("true".equals(input)) {
                return true;
            } else if ("false".equals(input)) {
                return false;
            } else {
                System.out.println("Entrada no válida. Por favor, introduce 'true' o 'false'.");
            }
        }
    }

}
