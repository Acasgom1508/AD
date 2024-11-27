/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg6_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author anton
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion = null;
        int opcion;
        String nombreBD = "jardineria";
        String usuario = "root";
        String contrasenna = "root";
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + nombreBD, usuario, contrasenna);
            Statement s = (Statement) conexion.createStatement();
            do {
                opcion = menu();
                switch (opcion) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
            } while (opcion != 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        int n = -1;
        System.out.println("-Menu-");
        System.out.println("1. .");
        System.out.println("2. .");
        System.out.println("3. .");
        System.out.println("4. .");
        System.out.println("5. .");
        System.out.println("6. .");
        System.out.println("0. Salir");

        do {
            System.out.print("Opcion:");
            try {
                n = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero");
            }
        } while (n < 0);

        return n;
    }
}
