/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg4_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author antonio
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
                        ResultSet rs = s.executeQuery("SELECT DP.CODIGO_PEDIDO AS CODIGO_PEDIDO, SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS PRECIO_TOTAL\n"
                                + "FROM PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO\n"
                                + "WHERE\n"
                                + "    DP.CODIGO_PRODUCTO IS NOT NULL\n"
                                + "GROUP BY\n"
                                + "    DP.CODIGO_PEDIDO;");
                        System.out.println("CODIGO_PEDIDO\tPRECIO_TOTAL");
                        System.out.println("------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
                        }
                        break;
                    case 2:
                        rs = s.executeQuery("SELECT P.NOMBRE, SUM(DP.CANTIDAD) AS TOTAL_UNIDADES\n"
                                + "FROM PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO\n"
                                + "GROUP BY\n"
                                + "    P.NOMBRE\n"
                                + "ORDER BY TOTAL_UNIDADES DESC\n"
                                + "LIMIT 20;");
                        System.out.println("NOMBRE\tTOTAL_UNIDADES");
                        System.out.println("------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                        }
                        break;
                    case 3:
                        rs = s.executeQuery("SELECT\n"
                                + "    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO\n"
                                + "FROM PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO;");
                        System.out.println("BASE_IMPONIBLE\tIVA\tTOTAL_FACTURADO");
                        System.out.println("------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT\n"
                                + "    P.CODIGO_PRODUCTO,\n"
                                + "    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO\n"
                                + "FROM PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO\n"
                                + "GROUP BY P.CODIGO_PRODUCTO");
                        System.out.println("CODIGO_PRODUCTO\tBASE_IMPONIBLE\tIVA\tTOTAL_FACTURADO");
                        System.out.println("--------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
                        }
                        break;
                    case 5:
                        rs = s.executeQuery("SELECT\n"
                                + "    P.CODIGO_PRODUCTO,\n"
                                + "    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,\n"
                                + "    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO\n"
                                + "FROM PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO\n"
                                + "GROUP BY P.CODIGO_PRODUCTO\n"
                                + "    HAVING P.CODIGO_PRODUCTO LIKE 'OR%';");
                        System.out.println("CODIGO_PRODUCTO\tBASE_IMPONIBLE\tIVA\tTOTAL_FACTURADO");
                        System.out.println("--------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
                        }
                        break;
                    case 6:
                        rs = s.executeQuery("SELECT \n"
                                + "    P.NOMBRE AS NOMBRE_PRODUCTO,\n"
                                + "    SUM(DP.CANTIDAD) AS UNIDADES_VENDIDAS,\n"
                                + "    SUM(DP.CANTIDAD * P.PRECIO_VENTA) AS TOTAL_FACTURADO,\n"
                                + "    ROUND(SUM(DP.CANTIDAD * P.PRECIO_VENTA) * 1.21, 2) AS TOTAL_CON_IVA\n"
                                + "FROM \n"
                                + "    PRODUCTO P\n"
                                + "    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO\n"
                                + "GROUP BY \n"
                                + "    P.NOMBRE\n"
                                + "HAVING \n"
                                + "    SUM(DP.CANTIDAD * P.PRECIO_VENTA) > 3000;");
                        System.out.println("NOMBRE_PRODUCTO\tUNIDADES_VENDIDAS TOTAL_FACTURADO TOTAL_CON_IVA");
                        System.out.println("--------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
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
        System.out.println("1. Calcula la suma de la cantidad total de todos los productos que aparecen en cada uno de los pedidos.");
        System.out.println("2. Devuelve un listado de los 20 productos más vendidos y el número total de unidades que se han vendido de cada uno. El listado deberá estar ordenado por el número total de unidades vendidas.");
        System.out.println("3. La facturación que ha tenido la empresa en toda la historia, indicando la base imponible, el IVA y el total facturado. La base imponible se calcula sumando el coste del producto por el número de unidades vendidas de la tabla detalle_pedido. El IVA es el 21 % de la base imponible, y el total la suma de los dos campos anteriores.");
        System.out.println("4. La misma información que en la pregunta anterior, pero agrupada por código de producto.");
        System.out.println("5. La misma información que en la pregunta anterior, pero agrupada por código de producto filtrada por los códigos que empiecen por OR.");
        System.out.println("6. Lista las ventas totales de los productos que hayan facturado más de 3000 euros. Se mostrará el nombre, unidades vendidas, total facturado y total facturado con impuestos (21% IVA).");
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
