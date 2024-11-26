-- Active: 1729582633344@@127.0.0.1@3306@jardineria
/* 11. Devuelve un listado de todos los pedidos que fueron rechazados en 2009. */
SELECT *
FROM pedido
WHERE
    estado = 'Rechazado'
    AND YEAR(fecha_pedido) = 2009;

/* 12. Devuelve un listado de todos los pedidos que han sido entregados 
en el mes de enero de cualquier año.*/

SELECT * FROM pedido WHERE estado = 'Entregado' AND MONTH(fecha_entrega) = 1;

/* 13. Devuelve un listado con todos los pagos que se realizaron en el año 2008 
mediante Paypal. Ordene el resultado de mayor a menor. */

SELECT * FROM pago WHERE forma_pago = 'PayPal' AND YEAR(fecha_pago) = 2008 ORDER BY fecha_pago DESC;

/* 14. Devuelve un listado con todas las formas de pago que aparecen en la tabla pago. 
Tenga en cuenta que no deben aparecer formas de pago repetidas. */

SELECT DISTINCT forma_pago FROM pago;

/* 15. Devuelve un listado con todos los productos que pertenecen a la gama Ornamentales 
y que tienen más de 100 unidades en stock. El listado deberá estar ordenado por su precio de venta, 
mostrando en primer lugar los de mayor precio.*/

SELECT * FROM producto WHERE gama = 'Ornamentales' AND cantidad_en_stock > 100 ORDER BY precio_venta DESC;

/* 16. Devuelve un listado con todos los clientes que sean de la ciudad de Madrid y cuyo representante 
de ventas tenga el código de empleado 11 o 30. */

SELECT * FROM cliente WHERE ciudad = 'Madrid' AND codigo_empleado_rep_ventas IN (11, 30);