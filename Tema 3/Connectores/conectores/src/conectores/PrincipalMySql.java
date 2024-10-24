import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrincipalMySql {

	// jardineria is database
	// user is user of database
	// pass is password of database
	private String nameDatabase = "jardineria";
	private String user = "root";
	private String pass = "root";

	public PrincipalMySql() {
	}

	public void consultaPorNombreColumna() {
		Connection connection = null;
		try {

			// jardineria is database
			// user is user of database
			// pass is password of database

			// Class.forName("com.mysql.cj.jdbc.Driver"); //De versiones antiguas

			// Notese ip, puerto, base de datos, usuario, password
			// connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria",
			// "userA", "passwordA");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto FROM cliente;");
			int code;
			String apellidoContacto;
			while (resultSet.next()) {
				code = resultSet.getInt("codigo_cliente");
				apellidoContacto = resultSet.getString("apellido_contacto").trim();
				System.out.println("Codigo Cliente : " + code + " Apellido contacto : " + apellidoContacto);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void consultaPorIndexColumna() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto FROM cliente;");

			int code;
			String apellidoContacto;
			while (resultSet.next()) {
				code = resultSet.getInt(1); // S�, empieza en 1 la primera columna
				apellidoContacto = resultSet.getString(4).trim();
				System.out.println("Codigo Cliente : " + code + " Apellido contacto : " + apellidoContacto);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void consultaParamPorIdCliente(int idCliente) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto FROM cliente  WHERE codigo_cliente = "
							+ idCliente + ";");

			int code;
			String apellidoContacto;
			while (resultSet.next()) {
				code = resultSet.getInt("codigo_cliente");
				apellidoContacto = resultSet.getString("apellido_contacto").trim();
				System.out.println("Codigo Cliente : " + code + " Apellido contacto : " + apellidoContacto);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void consultaParamPorNombreCliente(String nombreCliente) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto FROM cliente  WHERE nombre_cliente = '"
							+ nombreCliente + "';");

			int code;
			String apellidoContacto;
			while (resultSet.next()) {
				code = resultSet.getInt("codigo_cliente");
				apellidoContacto = resultSet.getString("apellido_contacto").trim();
				System.out.println("Codigo Cliente : " + code + " Apellido contacto : " + apellidoContacto);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void consultaParamPorNombreClientePreparedStatment(String nombreCliente) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto FROM cliente  WHERE nombre_cliente = ?");
			
			pstmt.setString(1, nombreCliente); // S�, empieza en 1 el primer parametro, no 0
			ResultSet resultSet = pstmt.executeQuery();
			int code;
			String apellidoContacto;
			while (resultSet.next()) {
				code = resultSet.getInt("codigo_cliente");
				apellidoContacto = resultSet.getString("apellido_contacto").trim();
				System.out.println("Codigo Cliente : " + code + " Apellido contacto : " + apellidoContacto);
			}
			resultSet.close();
			pstmt.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void actualizaLimiteCreditoPreparedStatment(String nombreCliente) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE cliente SET limite_credito = limite_credito + 1 WHERE nombre_cliente = ?");
			pstmt.setString(1, nombreCliente); // S�, empieza en 1 el primer parametro, no 0

			// ResultSet resultSet = pstmt.executeQuery(); //da error
			int nFilas = pstmt.executeUpdate();
			System.out.println("Afectadas un total de " + nFilas + " filas por el update");

			pstmt.close();
			connection.close();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void actualizaLimiteCredito2vecesPreparedStatment(String nombreCliente) {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE cliente SET limite_credito = limite_credito + 1 WHERE nombre_cliente = ?");
			pstmt.setString(1, nombreCliente); // S�, empieza en 1 el primer parametro, no 0
			int nFilas = pstmt.executeUpdate();
			if (1 == 1) {
				throw new Exception("aa");
			}
			nFilas = pstmt.executeUpdate();
			System.out.println("Afectadas un total de " + nFilas + " filas por el update");

			pstmt.close();
			connection.close();
		} catch (Exception exception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(exception);
		}
	}

	public void actualizaLimiteCredito2vecesPreparedStatmentTransaction(String nombreCliente) {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nameDatabase, user, pass);
			connection.setAutoCommit(false);
			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE cliente SET limite_credito = limite_credito + 1 WHERE nombre_cliente = ?");
			pstmt.setString(1, nombreCliente); // S�, empieza en 1 el primer parametro, no 0
			int nFilas = pstmt.executeUpdate();
			if (1 == 1) {
				throw new Exception("aa");
			}
			nFilas = pstmt.executeUpdate();
			System.out.println("Afectadas un total de " + nFilas + " filas por el update");
			connection.commit();
			pstmt.close();
			connection.setAutoCommit(true);
			connection.close();
		} catch (Exception exception) {
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(exception);
		}
	}
}




-- Active: 1729582633344@@127.0.0.1@3306@jardineria
/* 11. Devuelve un listado de todos los pedidos que fueron rechazados en 2009. */
SELECT *
FROM pedido
WHERE
    estado = 'Rechazado'
    AND YEAR(fecha_pedido) = 2009;

/* 12. Devuelve un listado de todos los pedidos que han sido entregados 
en el mes de enero de cualquier año.*/

SELECT *
FROM pedido
WHERE
    estado = 'Entregado'
    AND MONTH(fecha_entrega) = 1;

/* 13. Devuelve un listado con todos los pagos que se realizaron en el año 2008 
mediante Paypal. Ordene el resultado de mayor a menor. */

SELECT *
FROM pago
WHERE
    forma_pago = 'PayPal'
    AND YEAR(fecha_pago) = 2008
ORDER BY fecha_pago DESC;

/* 14. Devuelve un listado con todas las formas de pago que aparecen en la tabla pago. 
Tenga en cuenta que no deben aparecer formas de pago repetidas. */

SELECT DISTINCT forma_pago FROM pago;

/* 15. Devuelve un listado con todos los productos que pertenecen a la gama Ornamentales 
y que tienen más de 100 unidades en stock. El listado deberá estar ordenado por su precio de venta, 
mostrando en primer lugar los de mayor precio.*/

SELECT *
FROM producto
WHERE
    gama = 'Ornamentales'
    AND cantidad_en_stock > 100
ORDER BY precio_venta DESC;

/* 16. Devuelve un listado con todos los clientes que sean de la ciudad de Madrid y cuyo representante 
de ventas tenga el código de empleado 11 o 30. */

SELECT *
FROM cliente
WHERE
    ciudad = 'Madrid'
    AND codigo_empleado_rep_ventas IN (11, 30);
