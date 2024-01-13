<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
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
        <link rel="stylesheet"  href="${css}">
        <link rel="icon" href="${icono}">
        <title>Error 500</title>
    </head>

    <body>
        <div class="d-flex align-items-center justify-content-center vh-100">
            <div class="text-center">
                <div>
                    <img src="${contexto}/IMG/500.gif" alt=""
                        class="img-fluid">
                </div>
                <div>
                    <p class="fs-3"> <span class="text-danger">Opps!</span> Algo ha salido mal!</p>
                    <p class="lead">
                        No te preocupes, no es tu culpa. Vamos a solucionarlo.
                    </p>
                    <a href="${contexto}/index.jsp" class="btn btn-primary">Volver</a>
                </div>

            </div>
        </div>
    </body>

</html>