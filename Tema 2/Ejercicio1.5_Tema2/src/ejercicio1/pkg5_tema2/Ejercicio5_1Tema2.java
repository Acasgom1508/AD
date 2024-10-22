/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg5_tema2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author DAM_M
 */
public class Ejercicio5_1Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("nombres.txt",true));
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Edad: ");
            int edad = sc.nextInt();
            String linea = nombre + ", " + edad + " años";
            System.out.println("Añadida nueva línea: " + linea);
            bw.write(linea + "\n");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            }catch (IOException io) {
                System.out.println("No se ha cerrado");
            }
        }

    }

}
