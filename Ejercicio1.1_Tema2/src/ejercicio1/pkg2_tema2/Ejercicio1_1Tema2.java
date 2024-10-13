/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg2_tema2;

import java.io.File;

/**
 *
 * @author DAM_M
 */
public class Ejercicio1_1Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File(args[0]);
        if (fichero.isDirectory()) {
            File[] archivos = fichero.listFiles();
            for (File archivo : archivos) {
                System.out.println("Nombre: " + archivo.getName());
            }
            
        }else System.out.println("No es un directorio");
    }
    
}
