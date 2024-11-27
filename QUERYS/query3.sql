-- Active: 1729582633344@@127.0.0.1@3306@jardineria
/* 7. Devuelve un listado que muestre los empleados que no tienen una oficina asociada y los que no tienen un cliente asociado. */

SELECT E.NOMBRE
FROM EMPLEADO E
    LEFT JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS
WHERE
    E.CODIGO_OFICINA IS NULL
    OR C.CODIGO_EMPLEADO_REP_VENTAS IS NULL;

/* 8. Devuelve un listado de los productos que nunca han aparecido en un pedido. */

SELECT DISTINCT
    P.NOMBRE
FROM PRODUCTO P
    LEFT JOIN DETALLE_PEDIDO PD ON P.CODIGO_PRODUCTO = PD.CODIGO_PRODUCTO
WHERE
    PD.CODIGO_PRODUCTO IS NULL;
    /* 9. Devuelve un listado de los productos que nunca han aparecido en un pedido. El resultado debe mostrar el nombre, la descripción y la imagen del producto. */

SELECT DISTINCT
    P.NOMBRE,
    P.DESCRIPCION
FROM PRODUCTO P
    LEFT JOIN DETALLE_PEDIDO PD ON P.CODIGO_PRODUCTO = PD.CODIGO_PRODUCTO
WHERE
    PD.CODIGO_PRODUCTO IS NULL;
    /* 10. Devuelve las oficinas donde no trabajan ninguno de los empleados que hayan sido los representantes de ventas de algún cliente que haya realizado la compra de algún producto de la gama Frutales. */

SELECT DISTINCT
    O.CODIGO_OFICINA
FROM OFICINA O
WHERE
    O.CODIGO_OFICINA NOT IN(
        SELECT DISTINCT
            E.CODIGO_OFICINA
        FROM
            EMPLEADO E
            INNER JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS
            INNER JOIN PEDIDO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE
            INNER JOIN DETALLE_PEDIDO DP ON P.CODIGO_PEDIDO = DP.CODIGO_PEDIDO
            INNER JOIN PRODUCTO PR ON DP.CODIGO_PRODUCTO = PR.CODIGO_PRODUCTO
        WHERE
            PR.GAMA = 'Frutales'
    );

/* 11. Devuelve un listado con los clientes que han realizado algún pedido pero no han realizado ningún pago. */

SELECT DISTINCT C.NOMBRE_CLIENTE
FROM 
    CLIENTE C
    JOIN PEDIDO D ON C.CODIGO_CLIENTE = D.CODIGO_CLIENTE
    LEFT JOIN PAGO P ON C.CODIGO_CLIENTE = P.CODIGO_CLIENTE
WHERE 
    P.CODIGO_CLIENTE IS NULL;

/* 12. Devuelve un listado con los datos de los empleados que no tienen clientes asociados y el nombre de su jefe asociado. */

SELECT 
    E.NOMBRE
FROM EMPLEADO E
LEFT JOIN CLIENTE C ON E.CODIGO_EMPLEADO = C.CODIGO_EMPLEADO_REP_VENTAS
LEFT JOIN EMPLEADO J ON E.CODIGO_JEFE = J.CODIGO_EMPLEADO
WHERE C.CODIGO_CLIENTE IS NULL;
