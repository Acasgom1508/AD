/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg6_tema2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6_1Tema2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner arch = null;

        String archivo = null;
        do {
            System.out.print("Ruta del archivo a leer: ");
            archivo = sc.nextLine();
        } while (archivo.isEmpty());

        try {
            arch = new Scanner(new FileReader(archivo));
            while (arch.hasNext()) {
                System.out.print(arch.nextLine());
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo");
        } finally {
            try {
                if (arch != null) {
                    arch.close();
                }
            } catch (Exception e) {
                System.out.println("No se ha cerrado correctamente");
            }
        }
    }
}
