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
    <section>
        <div class="p-5 text-center bg-image" style="
    background-image: url('${contexto}/IMG/bg.svg');
    height: 850px;
  ">
            <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
                <div class="d-flex justify-content-center align-items-center h-100">
                    <div class="text-white">
                        <div class="card">
                            <div class="card-body">
                              <h5 class="card-title">Verificaci&oacute;n de Email</h5>
                              <p class="card-text">Gracias por verificar tu email.<p>
                              <p class="card-text">Ya puedes volver a la tienda e iniciar sesi&oacute;n</p>
                              <p class="card-text text-muted">Si no puedes iniciar sesi&oacute;n o ha habido alg&uacute;n problema al verificarte,<br>por favor, ponte en contacto con nosotros</p>
                              <a href="${contexto}/carga.jsp" class="btn btn-success text-center">Inicio</a>
                            </div>
                          </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
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
    <jsp:include page="/INC/footer.jsp"/>
</body>