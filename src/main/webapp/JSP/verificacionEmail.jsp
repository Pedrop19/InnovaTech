<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<c:set var="articulo" value="${sessionScope.articulo}"/>
<head>
    <jsp:directive.include file="/INC/metas.inc"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet"  href="${css}">
    <link rel="icon" href="${icono}">
    <script src="${contexto}/JS/Busqueda.js" defer></script>
    <script src="${contexto}/JS/SumarRestar.js" defer></script>
    <title>InnovaTech</title>
</head>

<body class="seccion-central">
    <c:if test="${error != null}">
        <script>
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "${error}",
                });
        </script>
    </c:if>
    <c:if test="${exito != null}">
        <script>
            Swal.fire({
                title: "Exito!",
                text: "${exito}",
                icon: "success"
                });
        </script>
    </c:if>
    <p class="h3 text-center">Ya puedes volver a la tienda e iniciar sesi&oacute;n</p>
    <a href="${contexto}/carga.jsp" class="btn btn-success text-center">Inicio</a>
</body>