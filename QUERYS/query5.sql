-- Active: 1732647373055@@127.0.0.1@3306@jardineria
/* 16. Devuelve las oficinas donde no trabajan ninguno de los empleados que hayan sido los representantes de ventas de algún cliente que haya realizado la compra de algún producto de la gama Frutales. */

SELECT DISTINCT
    O.CODIGO_OFICINA
FROM OFICINA O
    JOIN EMPLEADO E ON E.CODIGO_OFICINA = O.CODIGO_OFICINA
WHERE
    O.CODIGO_OFICINA NOT IN(
        SELECT DISTINCT
            E.CODIGO_OFICINA
        FROM
            EMPLEADO E
            JOIN CLIENTE C ON C.CODIGO_EMPLEADO_REP_VENTAS = E.CODIGO_EMPLEADO
            JOIN PEDIDO P ON P.CODIGO_CLIENTE = C.CODIGO_CLIENTE
            JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PEDIDO = P.CODIGO_PEDIDO
            JOIN PRODUCTO PR ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO
        WHERE
            PR.GAMA = 'Frutales'
    );

/* 17. Devuelve un listado con los clientes que han realizado algún pedido pero no han realizado ningún pago. */

SELECT DISTINCT
    C.NOMBRE_CLIENTE
FROM
    CLIENTE C
    JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE
    LEFT JOIN PAGO PAGO ON P.CODIGO_CLIENTE = PAGO.CODIGO_CLIENTE
WHERE
    PAGO.CODIGO_CLIENTE IS NULL;

/* 18. Devuelve un listado que muestre solamente los clientes que no han realizado ningún pago. */

SELECT C.NOMBRE_CLIENTE
FROM CLIENTE C
    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE
WHERE
    P.CODIGO_CLIENTE IS NULL;

/* 19. Devuelve un listado que muestre solamente los clientes que sí han realizado ALGUN pago. */

SELECT DISTINCT
    C.NOMBRE_CLIENTE
FROM CLIENTE C
    JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE;

/* 20. Devuelve un listado de los productos que nunca han aparecido en un pedido. */

SELECT DISTINCT
    PR.NOMBRE
FROM
    PRODUCTO PR
    LEFT JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO
WHERE
    DP.CODIGO_PRODUCTO IS NULL;

/* 21. Devuelve un listado de los productos que han aparecido en un pedido alguna vez. */

SELECT DISTINCT
    PR.NOMBRE
FROM
    PRODUCTO PR
    JOIN DETALLE_PEDIDO DP ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO;

SELECT NOMBRE FROM PRODUCTO