-- Active: 1732647373055@@127.0.0.1@3306@jardineria
//* 13. Calcula la suma de la cantidad total de todos los productos que aparecen en cada uno de los pedidos. */

SELECT DP.CODIGO_PEDIDO AS CODIGO_PEDIDO, SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS PRECIO_TOTAL
FROM PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
WHERE
    DP.CODIGO_PRODUCTO IS NOT NULL
GROUP BY
    DP.CODIGO_PEDIDO;

/* 14. Devuelve un listado de los 20 productos más vendidos y el número total de unidades que se han vendido de cada uno. El listado deberá estar ordenado por el número total de unidades vendidas. */

SELECT P.NOMBRE, SUM(DP.CANTIDAD) AS TOTAL_UNIDADES
FROM PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
GROUP BY
    P.NOMBRE
ORDER BY TOTAL_UNIDADES DESC
LIMIT 20;

/* 15. La facturación que ha tenido la empresa en toda la historia, indicando la base imponible, el IVA y el total facturado. La base imponible se calcula sumando el coste del producto por el número de unidades vendidas de la tabla detalle_pedido. El IVA es el 21 % de la base imponible, y el total la suma de los dos campos anteriores. */

SELECT
    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO
FROM PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
/* 16. La misma información que en la pregunta anterior, pero agrupada por código de producto. */

SELECT
    P.CODIGO_PRODUCTO,
    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO
FROM PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
GROUP BY P.CODIGO_PRODUCTO

/* 17. La misma información que en la pregunta anterior, pero agrupada por código de producto filtrada por los códigos que empiecen por OR. */

SELECT
    P.CODIGO_PRODUCTO,
    SUM(P.PRECIO_VENTA * DP.CANTIDAD) AS BASE_IMPONIBLE,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 0.21, 2) AS IVA,
    ROUND(SUM(P.PRECIO_VENTA * DP.CANTIDAD) * 1.21, 2) AS TOTAL_FACTURADO
FROM PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
GROUP BY P.CODIGO_PRODUCTO
    HAVING P.CODIGO_PRODUCTO LIKE 'OR%';

/* 18. Lista las ventas totales de los productos que hayan facturado más de 3000 euros. Se mostrará el nombre, unidades vendidas, total facturado y total facturado con impuestos (21% IVA). */

SELECT 
    P.NOMBRE AS NOMBRE_PRODUCTO,
    SUM(DP.CANTIDAD) AS UNIDADES_VENDIDAS,
    SUM(DP.CANTIDAD * P.PRECIO_VENTA) AS TOTAL_FACTURADO,
    ROUND(SUM(DP.CANTIDAD * P.PRECIO_VENTA) * 1.21, 2) AS TOTAL_CON_IVA
FROM 
    PRODUCTO P
    JOIN DETALLE_PEDIDO DP ON P.CODIGO_PRODUCTO = DP.CODIGO_PRODUCTO
GROUP BY 
    P.NOMBRE
HAVING 
    SUM(DP.CANTIDAD * P.PRECIO_VENTA) > 3000;

