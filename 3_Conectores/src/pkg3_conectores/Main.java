/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg3_conectores;

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
                        ResultSet rs = s.executeQuery("SELECT E.NOMBRE\n"
                                + "FROM EMPLEADO E\n"
                                + "    LEFT JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "WHERE\n"
                                + "    E.CODIGO_OFICINA IS NULL\n"
                                + "    OR C.CODIGO_EMPLEADO_REP_VENTAS IS NULL;");
                        System.out.println("NOMBRE");
                        System.out.println("-----------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 2:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    P.NOMBRE\n"
                                + "FROM PRODUCTO P\n"
                                + "    LEFT JOIN DETALLE_PEDIDO PD ON P.CODIGO_PRODUCTO = PD.CODIGO_PRODUCTO\n"
                                + "WHERE\n"
                                + "    PD.CODIGO_PRODUCTO IS NULL;");
                        System.out.println("NOMBRE");
                        System.out.println("-----------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 3:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    P.NOMBRE,\n"
                                + "    P.DESCRIPCION\n"
                                + "FROM PRODUCTO P\n"
                                + "    LEFT JOIN DETALLE_PEDIDO PD ON P.CODIGO_PRODUCTO = PD.CODIGO_PRODUCTO\n"
                                + "WHERE\n"
                                + "    PD.CODIGO_PRODUCTO IS NULL;");
                        System.out.println("NOMBRE\tDESCRIPCION");
                        System.out.println("-----------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT DISTINCT\n"
                                + "    O.CODIGO_OFICINA\n"
                                + "FROM OFICINA O\n"
                                + "WHERE\n"
                                + "    O.CODIGO_OFICINA NOT IN(\n"
                                + "        SELECT DISTINCT\n"
                                + "            E.CODIGO_OFICINA\n"
                                + "        FROM\n"
                                + "            EMPLEADO E\n"
                                + "            INNER JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "            INNER JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "            INNER JOIN DETALLE_PEDIDO DP ON P.CODIGO_PEDIDO = DP.CODIGO_PEDIDO\n"
                                + "            INNER JOIN PRODUCTO PR ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO\n"
                                + "        WHERE\n"
                                + "            PR.GAMA = 'Frutales'\n"
                                + "    );");
                        System.out.println("CODIGO_OFICINA");
                        System.out.println("--------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 5:
                        rs = s.executeQuery("SELECT C.NOMBRE_CLIENTE\n"
                                + "FROM\n"
                                + "    CLIENTE C\n"
                                + "    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE\n"
                                + "WHERE \n"
                                + "    P.CODIGO_CLIENTE IS NULL;");
                        System.out.println("NOMBRE");
                        System.out.println("-------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 6:
                        rs = s.executeQuery("SELECT \n"
                                + "    E.NOMBRE\n"
                                + "FROM EMPLEADO E\n"
                                + "LEFT JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS\n"
                                + "LEFT JOIN EMPLEADO J ON E.CODIGO_JEFE = J.CODIGO_EMPLEADO\n"
                                + "WHERE C.CODIGO_CLIENTE IS NULL;");
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
        System.out.println("1. Devuelve un listado que muestre los empleados que no tienen una oficina asociada y los que no tienen un cliente asociado.");
        System.out.println("2. Devuelve un listado de los productos que nunca han aparecido en un pedido.");
        System.out.println("3. Devuelve un listado de los productos que nunca han aparecido en un pedido. El resultado debe mostrar el nombre, la descripción y la imagen del producto.");
        System.out.println("4. Devuelve las oficinas donde no trabajan ninguno de los empleados que hayan sido los representantes de ventas de algún cliente que haya realizado la compra de algún producto de la gama Frutales.");
        System.out.println("5. Devuelve un listado con los clientes que han realizado algún pedido pero no han realizado ningún pago.");
        System.out.println("6. Devuelve un listado con los datos de los empleados que no tienen clientes asociados y el nombre de su jefe asociado.");
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
