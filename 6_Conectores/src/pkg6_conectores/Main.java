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
                        ResultSet rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    C.NOMBRE_CLIENTE,\n"
                                + "    SUM(P.TOTAL) AS TOTAL_PAGADO\n"
                                + "FROM CLIENTE C\n"
                                + "    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "GROUP BY\n"
                                + "    C.NOMBRE_CLIENTE;");
                        System.out.println("NOMBRE_CLIENTE\tTOTAL_PAGADO");
                        System.out.println("-----------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                        }
                        break;
                    case 2:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    C.NOMBRE_CLIENTE\n"
                                + "FROM CLIENTE C\n"
                                + "    LEFT JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "WHERE\n"
                                + "    YEAR(P.FECHA_PEDIDO) = 2008");
                        System.out.println("NOMBRE_CLIENTE");
                        System.out.println("-----------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 3:
                        rs = s.executeQuery("SELECT\n"
                                + "    C.NOMBRE_CLIENTE,\n"
                                + "    E.NOMBRE AS NOMBRE_REP_VENTA,\n"
                                + "    E.APELLIDO1 AS APELLIDO_REP_VENTA,\n"
                                + "    O.TELEFONO AS TELEFONO_OFICINA\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "    JOIN EMPLEADO E ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "    JOIN OFICINA O ON O.CODIGO_OFICINA = E.CODIGO_OFICINA\n"
                                + "WHERE\n"
                                + "    P.CODIGO_CLIENTE IS NULL;");
                        System.out.println("NOMBRE_CLIENTE\tNOMBRE_REP_VENTA\tAPELLIDO_REP_VENTA\tTELEFONO_OFICINA");
                        System.out.println("---------------------------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT\n"
                                + "    C.NOMBRE_CLIENTE,\n"
                                + "    E.NOMBRE AS NOMBRE_REP_VENTA,\n"
                                + "    E.APELLIDO1 AS APELLIDO_REP_VENTA,\n"
                                + "    O.CIUDAD AS CIUDAD_OFICINA\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    JOIN EMPLEADO E ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "    JOIN OFICINA O ON O.CODIGO_OFICINA = E.CODIGO_OFICINA;");
                        System.out.println("NOMBRE_CLIENTE\tNOMBRE_REP_VENTA\tAPELLIDO_REP_VENTA\tCIUDAD_OFICINA");
                        System.out.println("---------------------------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
                        }
                        break;
                    case 5:
                        rs = s.executeQuery("SELECT E.NOMBRE, E.APELLIDO1, E.PUESTO, O.TELEFONO AS TELEFONO_OFICINA\n"
                                + "FROM\n"
                                + "    EMPLEADO E\n"
                                + "    LEFT JOIN CLIENTE C ON C.CODIGO_EMPLEADO_REP_VENTAS = E.CODIGO_EMPLEADO\n"
                                + "    JOIN OFICINA O ON O.CODIGO_OFICINA = E.CODIGO_OFICINA\n"
                                + "WHERE\n"
                                + "    C.CODIGO_EMPLEADO_REP_VENTAS IS NULL;");
                        System.out.println("NOMBRE\tAPELLIDO1\tPUESTO\tTELEFONO_OFICINA");
                        System.out.println("-------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
                        }
                        break;
                    case 6:
                        rs = s.executeQuery("SELECT O.CIUDAD, COUNT(E.CODIGO_EMPLEADO) AS NUMERO_EMPLEADOS\n"
                                + "FROM OFICINA O\n"
                                + "    JOIN EMPLEADO E ON E.CODIGO_OFICINA = O.CODIGO_OFICINA\n"
                                + "GROUP BY\n"
                                + "    O.CIUDAD;");
                        System.out.println("CIUDAD\tNUMERO_EMPLEADOS");
                        System.out.println("--------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
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
        System.out.println("1. Devuelve un listado con los nombres de los clientes y el total pagado por cada uno de ellos. Tenga en cuenta que pueden existir clientes que no han realizado ningún pago.");
        System.out.println("2. Devuelve el nombre de los clientes que hayan hecho pedidos en 2008 ordenados alfabéticamente de menor a mayor.");
        System.out.println("3. Devuelve el nombre del cliente, el nombre y primer apellido de su representante de ventas y el número de teléfono de la oficina del representante de ventas, de aquellos clientes que no hayan realizado ningún pago.");
        System.out.println("4. Devuelve el listado de clientes donde aparezca el nombre del cliente, el nombre y primer apellido de su representante de ventas y la ciudad donde está su oficina.");
        System.out.println("5. Devuelve el nombre, apellidos, puesto y teléfono de la oficina de aquellos empleados que no sean representante de ventas de ningún cliente.");
        System.out.println("6. Devuelve un listado indicando todas las ciudades donde hay oficinas y el número de empleados que tiene.");
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
