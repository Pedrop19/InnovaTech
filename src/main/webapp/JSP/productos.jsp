<%-- 
    Document   : productos
    Created on : 3 ene. 2024, 19:55:17
    Author     : pedro
--%>
<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<c:set var="categoria" value="${sessionScope.categoria}"/>
<head>
    <jsp:directive.include file="/INC/metas.inc"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${css}">
    <link rel="icon" href="${icono}">
    <script src="${contexto}/JS/Busqueda.js" defer></script>
    <title>InnovaTech</title>
</head>
 
<body class="seccion-central">
    <jsp:directive.include file="/INC/navbar.jsp"/>
    
    <section class="min-vh-100  w-100">
        
        <div class="container-fluid d-flex align-items-center flex-column">
        <c:choose>
            <c:when test="${empty productos}">
                <div class="alert alert-danger w-50 my-5 text-center" role="alert">
                    <h4 class="alert-heading"><i class="fas fa-exclamation-triangle"></i> Sin Stock</h4>
                    <p>Lo sentimos, no disponemos de stock de productos de esta categoria en estos momentos.</p>
                </div>
            </c:when>
            <c:otherwise>
                 <div class="text-center container">
                    <h4 class="mt-4 mb-5 text-white"><strong>${categoria.nombre}</strong></h4>
                    <div class="row">
                        <c:forEach var="producto" items="${productos}" varStatus="loop">
                            <form action="${contexto}/CarritoController" method="POST" class="col-lg-4 col-md-6 mb-4">
                                <input type="hidden" name="id" id="idProducto" value="${producto.id}"/>
                                    <div class="card">
                                        <div class="bg-image hover-zoom ripple" data-mdb-ripple-color="light">
                                            <img src="${contexto}/IMG/productos/${producto.imagen}.jpg"
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
            </c:otherwise>
        </c:choose>
            <div class="container-fluid d-flex justify-content-center align-items-center">
                <div class="row">
                    <div class="col-12">
                        <a href="${contexto}/index.jsp" class="btn btn-success">Volver</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>

            <footer class="bg-body-tertiary text-center text-lg-start  w-100">
            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.05);">
              © 2023 Copyright:
              <a class="text-body" href="https://github.com/Pedrop19">Pedro Rafael L&aacute;zaro Nevado</a>
            </div>
          </footer>

</body>

</html>