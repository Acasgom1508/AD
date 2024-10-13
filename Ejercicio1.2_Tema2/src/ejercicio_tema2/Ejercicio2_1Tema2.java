/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio_tema2;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author DAM_M
 */
public class Ejercicio2_1Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ruta del directorio: ");
        File fichero = new File(sc.nextLine());
        System.out.println("-------------------------------");
        System.out.println("Es un directorio");
            String tipo = "";
            String leerEscribir = "";
            if (fichero.canRead()) {
                if (fichero.canWrite()) {
                    leerEscribir = "Si";
                } else {
                    leerEscribir = "Solo puede leer";
                }
            } else if (fichero.canWrite()) {
                leerEscribir = "Solo puede escribir";
            }
            System.out.println("Nombre:" + fichero.getName());
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("Tamaño; " + fichero.length() + " bytes");

            System.out.println("Se puede leer y escribir?: " + leerEscribir);
            if (fichero.isDirectory()) {
                tipo = "Directorio";
            } else {
                tipo = "Archivo";
            }
            
            System.out.println("Es fichero o directorio?: " + tipo);
            System.out.println("Nombre de su directorio padre: " + fichero.getParent());
            System.out.println("-------------------------------");

    }

}
