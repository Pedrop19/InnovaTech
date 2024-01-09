<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="icono" value="${contexto}/IMG/Logo.png" scope="application"/>
<c:set var="css" value="${contexto}/CSS/style.css" scope="application"/>
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
    <link rel="icon" href="${icono}">
    <link rel="stylesheet" href="${css}">
    <script src="${contexto}/JS/SumarRestar.js" defer></script>
    <script src="${contexto}/JS/Busqueda.js" defer></script>
    <title>InnovaTech</title>
</head>

<body class="seccion-central">
    <jsp:directive.include file="/INC/navbar.jsp"/>
    <section>
        <div class="text-center container">
            <h4 class="mt-4 mb-5 text-white"><strong>M&aacute;s Vendidos</strong></h4>
            <div class="row">
                <c:forEach var="producto" items="${articulos}" varStatus="loop">
                    <form action="${contexto}/CarritoController" method="POST" class="col-lg-4 col-md-6 mb-4">
                        <input type="hidden" name="id" id="idProducto" value="${producto.id}"/>
                            <div class="card">
                                <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                    <img src="IMG/productos/${producto.imagen}.jpg"
                                        class="fixed-image-size" />
                                    <a id="a-link" href="#!">
                                        <div class="mask">
                                            <div class="d-flex justify-content-start align-items-end h-100">
                                                <h5><span class="badge bg-danger ms-2">M&aacute;s Vendido</span></h5>
                                            </div>
                                        </div>
                                        <div class="hover-overlay">
                                            <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                        </div>
                                    </a>
                                </div>
                                <div class="card-body">
                                    <a href="" class="text-reset">
                                        <h5 class="card-title mb-3">${producto.nombre}</h5>
                                    </a>
                                    <a href="" class="text-reset">
                                        <p>${producto.categoria.nombre}</p>
                                    </a>
                                    <h6 class="mb-3">
                                        <strong class="text-success">${producto.precio}€</strong>
                                    </h6>
                                </div>
                            </div>
                    </form>
                </c:forEach>
            </div>
        </div>
    </section>
    <jsp:directive.include file="/INC/footer.jsp"/>
    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
</body>

</html>