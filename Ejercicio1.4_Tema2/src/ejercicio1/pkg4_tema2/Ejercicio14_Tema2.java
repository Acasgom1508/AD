/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg4_tema2;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author DAM_M
 */
public class Ejercicio14_Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ruta del directorio: ");
        File directorio = new File(sc.nextLine());
        listarContenido(directorio, 0);
    }

    public static void listarContenido(File direct, int nivel) {
        File[] archivos = direct.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                
                for (int i = 0; i < nivel; i++) {
                    System.out.print("   ");
                }
                
                if (archivo.isDirectory()) {
                    System.out.println("[Directorio] "+archivo.getName());
                    listarContenido(archivo, nivel + 1);
                }else System.out.println("[Fichero] "+archivo.getName());
            }
        }

    }

}
