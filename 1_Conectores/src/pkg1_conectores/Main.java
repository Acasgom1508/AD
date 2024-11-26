/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        String estado = "rechazado";
                        int fechaPedido = 2009;
                        ResultSet rs = s.executeQuery("SELECT * FROM pedido WHERE estado = '" + estado + "' AND YEAR(fecha_pedido) = " + fechaPedido);
                        System.out.println("Codigo_Pedido\tFecha_Pedido\tFecha_Esperada\tFecha_Entrega\tEstado\tComentarios\tCodigo_Cliente");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
                        }
                        break;
                    case 2:
                        estado = "entregado";
                        int fechaEntrega = 1;
                        rs = s.executeQuery("SELECT * FROM pedido WHERE estado = '" + estado + "' AND MONTH(fecha_entrega) = " + fechaEntrega);
                        System.out.println("Codigo_Pedido\tFecha_Pedido\tFecha_Esperada\tFecha_Entrega\tEstado\tComentarios\tCodigo_Cliente");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
                        }
                        break;
                    case 3:
                        String formaPago = "PayPal";
                        int fechaPago = 2008;
                        rs = s.executeQuery("SELECT * FROM pago WHERE forma_pago = '" + formaPago + "' AND YEAR(fecha_pago) = " + fechaPago + " ORDER BY fecha_pago DESC");
                        System.out.println("Codigo_Cliente\tForma_Pago\tId_Transaccion\tFecha_Pago\tTotal");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(4));
                        }
                        break;
                    case 4:
                        rs = s.executeQuery("SELECT DISTINCT forma_pago FROM pago");
                        System.out.println("Forma_Pago");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                        break;
                    case 5:
                        String gama = "Ornamentales";
                        rs = s.executeQuery("SELECT * FROM producto WHERE gama = '" + gama + "' AND cantidad_en_stock > 100 ORDER BY precio_venta DESC;");
                        System.out.println("Codigo_producto\tNombre\tGama\tDimensiones\tProveedor\tDescripcion\tCantidad_enStock\tPrecio_Venta\tPrecio_Proveedor");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t");
                        }
                        break;
                    case 6:
                        String ciudad = "Madrid";
                        rs = s.executeQuery("SELECT * FROM cliente WHERE ciudad = '" + ciudad + "' AND codigo_empleado_rep_ventas IN (11, 30)");
                        System.out.println("Codigo_Cliente\tNombre_Cliente\tNombre_Contacto\tApellio_Contacto\tTelefono\tFax\tLinea_Direccion1\tLinea_Direccion2\tCiudad\tRegion\tPais\tCodigo_Postal\tCodigo_Empleado_Rep_Ventas\tLimite_Credito");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t"  + rs.getString(8) + "\t"  + rs.getString(9) + "\t" + rs.getString(10) + "\t" + rs.getString(11) + "\t" + rs.getString(12) + "\t" + rs.getString(13) + "\t" + rs.getString(14) + "\t");
                        }
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
        System.out.println("1. Listado de pedidos rechazados en 2009.");
        System.out.println("2. Listado de pedidos entregados en enero, de cualquier año.");
        System.out.println("3. Listado de pagos hechos en 2008 mediante PayPal, ordenados de mayor a menor.");
        System.out.println("4. Listado de formas de pago únicas en la tabla de pagos.");
        System.out.println("5. Listado de productos de la gama Ornamentales con más de 100 unidades en stock, ordenados de mayor a menor precio.");
        System.out.println("6. Listado de clientes de Madrid cuyos representantes de ventas tienen el código de empleado 11 o 30.");
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
