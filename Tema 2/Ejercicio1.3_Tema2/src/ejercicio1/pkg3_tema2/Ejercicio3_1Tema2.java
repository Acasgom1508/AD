/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg3_tema2;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author DAM_M
 */
public class Ejercicio3_1Tema2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        File nuevo_directorio = new File("NUEVODIR");
        if (nuevo_directorio.mkdir()) {
            System.out.println("Se ha creado el nuevo directorio con nombre " + nuevo_directorio.getName());
        }else System.out.println("El directorio ya estaba creado, por lo que solo se añadiran los ficheros");
        File fichero1 = new File(nuevo_directorio, "Fichero1.txt");
        File fichero2 = new File(nuevo_directorio, "Fichero2.txt");
        if (fichero1.createNewFile()) {
            System.out.println("Se ha creado el fichero 1");
        } else {
            System.out.println("No se ha podido crear el fichero 1");
        }

        if (fichero2.createNewFile()) {
            System.out.println("Se ha creado el fichero 2");
        } else {
            System.out.println("No se ha podido crear el fichero 2");
        }

        File fichero1_renombrado = new File(nuevo_directorio, "Fichero1Renamed.txt");

        if (fichero1.renameTo(fichero1_renombrado)) {
            System.out.println("Se ha cambiado el nombre de fichero 1 a Fichero1Renamed");
        } else {
            System.out.println("No se ha podido cambiar el nombre del fichero 1");
        }

        if (fichero2.delete()) {
            System.out.println("El fichero 2 se ha borrado con exito");
        } else {
            System.out.println(" no se ha podido borrar el fichero 2");
        }
    }

}
