package ec.com.espe.batchescolastico.servicio;

import ec.com.espe.batchescolastico.model.Estudiante;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
    public static void crearArchivo(String ruta) {
        FileWriter flwriter = null;
        try {
            // crea el flujo para escribir en el archivo
            flwriter = new FileWriter(ruta);
            // crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
      /*for (Estudiante estudiante : lista) {
      	//escribe los datos en el archivo
      	bfwriter.write(estudiante.getCedula() + "," + estudiante.getNombres() + "," + estudiante.getApellidos()
      			+ "," + estudiante.getTelefono() + "," + estudiante.getDireccion() + "\n");
      }*/
            // cierra el buffer intermedio
            bfwriter.close();
            System.out.println("Archivo creado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try { // cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Estudiante> leerArchivo(String ruta) {
        File file = new File(ruta);
        ArrayList<Estudiante> listaDatos = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");

                Estudiante dato =
                        Estudiante.builder()
                                .apellido(delimitar.next())
                                .nombres(delimitar.next())
                                .nivel(delimitar.nextInt())
                                .internalId(delimitar.next())
                                .build();
                listaDatos.add(dato);
                System.out.println("Data de archivo:" + dato.toString() + "\n");
                delimitar.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listaDatos;
    }

    public static String nombreArchivo(String ruta) {
        String[] archivos = ruta.split("/");
        String nombre = archivos[archivos.length - 1];
        return nombre;
    }


    public static void agregarArchivo(String ruta, List<Estudiante> lista) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ruta, true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            for (Estudiante estudiante : lista) {

                bfwriter.write( estudiante.getApellido()+ estudiante.getNombres() + "," + estudiante.getCedula() + "\n");
            }
            bfwriter.close();
            System.out.println("Archivo modificado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
