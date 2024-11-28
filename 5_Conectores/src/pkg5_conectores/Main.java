/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg5_conectores;

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
                        ResultSet rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    O.CODIGO_OFICINA\n"
                                + "FROM OFICINA O\n"
                                + "    JOIN EMPLEADO E ON E.CODIGO_OFICINA = O.CODIGO_OFICINA\n"
                                + "WHERE\n"
                                + "    O.CODIGO_OFICINA NOT IN(\n"
                                + "        SELECT DISTINCT\n"
                                + "            E.CODIGO_OFICINA\n"
                                + "        FROM\n"
                                + "            EMPLEADO E\n"
                                + "            JOIN CLIENTE C ON C.CODIGO_EMPLEADO_REP_VENTAS = E.CODIGO_EMPLEADO\n"
                                + "            JOIN PEDIDO P ON P.CODIGO_CLIENTE = C.CODIGO_CLIENTE\n"
                                + "            JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PEDIDO = P.CODIGO_PEDIDO\n"
                                + "            JOIN PRODUCTO PR ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO\n"
                                + "        WHERE\n"
                                + "            PR.GAMA = 'Frutales'\n"
                                + "    );");
                        System.out.println("CODIGO_OFICINA");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 2:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    C.NOMBRE_CLIENTE\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "    LEFT JOIN PAGO PAGO ON P.CODIGO_CLIENTE = PAGO.CODIGO_CLIENTE\n"
                                + "WHERE\n"
                                + "    PAGO.CODIGO_CLIENTE IS NULL;");
                        System.out.println("NOMBRE_CLIENTE");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 3:
                        rs = s.executeQuery("SELECT C.NOMBRE_CLIENTE\n"
                                + "FROM CLIENTE C\n"
                                + "    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "WHERE\n"
                                + "    P.CODIGO_CLIENTE IS NULL;");
                        System.out.println("NOMBRE_CLIENTE");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    C.NOMBRE_CLIENTE\n"
                                + "FROM CLIENTE C\n"
                                + "    JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE;");
                        System.out.println("NOMBRE_CLIENTE");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 5:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    PR.NOMBRE\n"
                                + "FROM\n"
                                + "    PRODUCTO PR\n"
                                + "    LEFT JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO\n"
                                + "WHERE\n"
                                + "    DP.CODIGO_PRODUCTO IS NULL;");
                        System.out.println("NOMBRE");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 6:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    PR.NOMBRE\n"
                                + "FROM\n"
                                + "    PRODUCTO PR\n"
                                + "    JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO;");
                        System.out.println("NOMBRE");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
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
        System.out.println("1. Devuelve las oficinas donde no trabajan ninguno de los empleados que hayan sido los representantes de ventas de algún cliente que haya realizado la compra de algún producto de la gama Frutales.");
        System.out.println("2. Devuelve un listado con los clientes que han realizado algún pedido pero no han realizado ningún pago.");
        System.out.println("3. Devuelve un listado que muestre solamente los clientes que no han realizado ningún pago.");
        System.out.println("4. Devuelve un listado que muestre solamente los clientes que sí han realizado ALGUN pago.");
        System.out.println("5. Devuelve un listado de los productos que nunca han aparecido en un pedido.");
        System.out.println("6. Devuelve un listado de los productos que han aparecido en un pedido alguna vez.");
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
