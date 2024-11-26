/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author DAM_M
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
                        ResultSet rs = s.executeQuery("SELECT C.NOMBRE_CLIENTE, E.NOMBRE AS NOMBRE_REPRESENTANTE, O.CIUDAD\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    INNER JOIN EMPLEADO E ON C.CODIGO_EMPLEADO_REP_VENTAS = E.CODIGO_EMPLEADO\n"
                                + "    INNER JOIN OFICINA O ON E.CODIGO_OFICINA = O.CODIGO_OFICINA\n"
                                + "WHERE\n"
                                + "    C.CODIGO_CLIENTE NOT IN(\n"
                                + "        SELECT P.CODIGO_CLIENTE\n"
                                + "        FROM PAGO P\n"
                                + "    );");
                        System.out.println("NOMBRE_CLIENTE\tNOMBRE_REPRE\tCIUDAD");
                        System.out.println("---------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    case 2:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    O.LINEA_DIRECCION1\n"
                                + "FROM\n"
                                + "    OFICINA O\n"
                                + "    INNER JOIN EMPLEADO E ON O.CODIGO_OFICINA = E.CODIGO_OFICINA\n"
                                + "    INNER JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "WHERE\n"
                                + "    C.CIUDAD = 'FUENLABRADA';");
                        System.out.println("LINEA DIRECCION 1");
                        System.out.println("-----------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 3:
                        rs = s.executeQuery("SELECT\n"
                                + "    C.NOMBRE_CLIENTE,\n"
                                + "    E.NOMBRE AS NOMBRE_REPRESENTANTE,\n"
                                + "    O.CIUDAD AS CIUDAD_OFICINA\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    INNER JOIN EMPLEADO E ON C.CODIGO_EMPLEADO_REP_VENTAS = E.CODIGO_EMPLEADO\n"
                                + "    INNER JOIN OFICINA O ON E.CODIGO_OFICINA = O.CODIGO_OFICINA;");
                        System.out.println("NOMBRE_CLIENTE\tNOMBRE_REPRE\tCIUDAD_OFICINA");
                        System.out.println("--------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT\n"
                                + "    E.NOMBRE AS NOMBRE_EMPLEADO,\n"
                                + "    J.NOMBRE AS NOMBRE_JEFE\n"
                                + "FROM EMPLEADO E\n"
                                + "    INNER JOIN EMPLEADO J ON E.CODIGO_JEFE = J.CODIGO_EMPLEADO;");
                        System.out.println("NOMBRE_EMPLEADO\tNOMBRE_JEFE");
                        System.out.println("-------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
                        }
                        break;
                    case 5:
                        rs = s.executeQuery("SELECT C.NOMBRE_CLIENTE, P.FECHA_ESPERADA, P.FECHA_ENTREGA\n"
                                + "FROM CLIENTE C\n"
                                + "    INNER JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "WHERE\n"
                                + "    P.FECHA_ESPERADA < P.FECHA_ENTREGA;");
                        System.out.println("NOMBRE_CLIENTE\tFECHA_ESPERADA\tFECHA_ENTREGA");
                        System.out.println("-----------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    case 6:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    C.NOMBRE_CLIENTE,\n"
                                + "    P.GAMA\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    INNER JOIN PEDIDO PE ON C.CODIGO_CLIENTE = PE.CODIGO_CLIENTE\n"
                                + "    INNER JOIN DETALLE_PEDIDO DP ON PE.CODIGO_PEDIDO = DP.CODIGO_PEDIDO\n"
                                + "    INNER JOIN PRODUCTO P ON DP.CODIGO_PRODUCTO = P.CODIGO_PRODUCTO;");
                        System.out.println("NOMBRE_CLIENTE\tGAMA");
                        System.out.println("--------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
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
        System.out.println("1. DEVUELVE EL NOMBRE DE LOS CLIENTES QUE NO HAYAN HECHO PAGOS Y EL NOMBRE DE SUS REPRESENTANTES JUNTO CON LA CIUDAD DE LA OFICINA A LA QUE PERTENECE EL REPRESENTANTE.");
        System.out.println("2. LISTA LA DIRECCIÓN DE LAS OFICINAS QUE TENGAN CLIENTES EN FUENLABRADA.");
        System.out.println("3. DEVUELVE EL NOMBRE DE LOS CLIENTES Y EL NOMBRE DE SUS REPRESENTANTES JUNTO CON LA CIUDAD DE LA OFICINA A LA QUE PERTENECE EL REPRESENTANTE.");
        System.out.println("4. DEVUELVE UN LISTADO CON EL NOMBRE DE LOS EMPLEADOS JUNTO CON EL NOMBRE DE SUS JEFES.");
        System.out.println("5. DEVUELVE EL NOMBRE DE LOS CLIENTES A LOS QUE NO SE LES HA ENTREGADO A TIEMPO UN PEDIDO.");
        System.out.println("6. DEVUELVE UN LISTADO DE LAS DIFERENTES GAMAS DE PRODUCTO QUE HA COMPRADO CADA CLIENTE.");
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
