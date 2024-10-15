/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1.pkg7_tema2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAM_M
 */
public class Ejercicio7_1Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crea un programa Java capaz de leer objetos de ficheros serializables. 
        ArrayList<String> objetos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique la línea que quiere añadir: ");
        String linea = sc.nextLine();
        objetos.add(linea);
        escribirFichero(objetos);
        leerFichero();
    }

    private static void leerFichero() {
        ObjectInputStream entradaObjeto = null;
        ArrayList<String> array2 = null;
        try {
            entradaObjeto = new ObjectInputStream(new FileInputStream("objetos.dat"));
            array2 = (ArrayList<String>) entradaObjeto.readObject();
            for (String string : array2) {
                System.out.println(string);
            }
        } catch (FileNotFoundException | ClassNotFoundException cn) {
            System.out.println("");
        } catch (IOException io) {

        } finally {
            if (entradaObjeto != null) {
                try {
                    entradaObjeto.close();
                } catch (IOException ex) {
                    System.out.println("Error de entrada/salida");
                }
            }
        }
    }

    private static void escribirFichero(ArrayList<String> objetos) {
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream(new FileOutputStream("objetos.dat"));
            o.writeObject(objetos);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido crear el fichero");
        } catch (IOException ioe) {
            System.out.println("Error de entrada/salida");
        } finally {
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el proyecto");
                }
            }
        }
    }

}
