-- Active: 1732647373055@@127.0.0.1@3306@jardineria
/* 11. Devuelve un listado de todos los pedidos que fueron rechazados en 2009. */
SELECT
    *
FROM
    PEDIDO
WHERE
    ESTADO = 'Rechazado'
    AND YEAR(FECHA_PEDIDO) = 2009;

/* 12. Devuelve un listado de todos los pedidos que han sido entregados 
en el mes de enero de cualquier año.*/

SELECT
    *
FROM
    PEDIDO
WHERE
    ESTADO = 'Entregado'
    AND MONTH(FECHA_ENTREGA) = 1;

/* 13. Devuelve un listado con todos los pagos que se realizaron en el año 2008 
mediante Paypal. Ordene el resultado de mayor a menor. */

SELECT
    *
FROM
    PAGO
WHERE
    FORMA_PAGO = 'PayPal'
    AND YEAR(FECHA_PAGO) = 2008
ORDER BY
    FECHA_PAGO DESC;

/* 14. Devuelve un listado con todas las formas de pago que aparecen en la tabla pago. 
Tenga en cuenta que no deben aparecer formas de pago repetidas. */

SELECT
    DISTINCT FORMA_PAGO
FROM
    PAGO;

/* 15. Devuelve un listado con todos los productos que pertenecen a la gama Ornamentales 
y que tienen más de 100 unidades en stock. El listado deberá estar ordenado por su precio de venta, 
mostrando en primer lugar los de mayor precio.*/

SELECT
    *
FROM
    PRODUCTO
WHERE
    GAMA = 'Ornamentales'
    AND CANTIDAD_EN_STOCK > 100
ORDER BY
    PRECIO_VENTA DESC;

/* 16. Devuelve un listado con todos los clientes que sean de la ciudad de Madrid y cuyo representante 
de ventas tenga el código de empleado 11 o 30. */

SELECT
    *
FROM
    CLIENTE
WHERE
    CIUDAD = 'Madrid'
    AND CODIGO_EMPLEADO_REP_VENTAS IN (11, 30);