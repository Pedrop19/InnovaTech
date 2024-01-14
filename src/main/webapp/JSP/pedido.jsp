<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<c:set var="carrito" value="${sessionScope.carrito}"/>
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
    <%-- <script src="${contexto}/JS/Carrito.js" defer></script> --%>
    <script src="${contexto}/JS/Busqueda.js" defer></script>
    <script src="${contexto}/JS/SumarRestar.js" defer></script>
    <title>InnovaTech</title>
</head>

<body class="seccion-central">

<jsp:directive.include file="/INC/navbar.jsp"/>
    <section class="vh-100 d-flex align-items-center seccion-factura flex-column ">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center text-white">Factura</h1>
                </div>
            </div>
        </div>
        <table class="table align-middle mb-0 my-5" style="background-color: #fff !important;">
           
            <thead class="bg-white text-bg-white">
                <tr>
                    <th>Nombre</th>
                    <th>Marca</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Estado</th>
                    <th>Precio Total (Sin IVA)</th>
                </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach var="carrito" items="${sessionScope.carrito}">
                <input type="hidden" name="id" class="idArticulo" value="${carrito.articulo.id}">
                <tr>
                    <td class="text-black">
                        <div class="d-flex align-items-center">
                            <img src="${contexto}/IMG/productos/${carrito.articulo.imagen}.jpg" alt=""
                                style="width: 45px; height: 45px" />
                            <div class="ms-3">
                                <p class="fw-bold mb-1">${carrito.articulo.nombre}</p>
                            </div>
                        </div>
                    </td>
                    <td class="text-black">
                        <p class="fw-normal mb-1">${carrito.articulo.marca}</p>
                    </td> 
                    <td class="text-black">
                        <p class="fw-normal mb-1 precioArticulo">${carrito.articulo.precio}€</p>
                    </td>
                    <td class="text-black">
                        <p class="fw-normal mb-1 cantidad">${carrito.cantidad}</p>
                    </td>
                    <td class="text-black">
                        <span class="badge badge-warning">Pendiente</span>
                    </td>
                    <td class="text-black">
                        <p class="fw-normal mb-1 precioTotal">${carrito.articulo.precio * carrito.cantidad}€</p>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td class="text-white">
                        <p  class="fw-bold mb-1">Total (Con IVA)</p>
                    </td>
                    <td>
                        <p class="fw-normal mb-1"></p>
                    </td>
                    <td class="text-white">
                    </td>
                    <td class="text-white">
                    </td>
                    <td class="text-white">
                        <p id="precioTotalPedido" class="fw-normal mb-1">120.00</p>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <form action="${contexto}/FinalizarCompraController" method="post" class="mt-2">
            <button type="submit" class="btn btn-dark btn-rounded btn-sm fw-bold"
                data-mdb-ripple-color="dark">
                Finalizar Compra
            </button>
            
            <a href="${contexto}/index.jsp" type="button" class="btn btn-outline-danger btn-rounded btn-sm fw-bold"
                data-mdb-ripple-color="dark">
                Cancelar
            </a>
        </form>
            
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
    <jsp:directive.include file="/INC/footer.jsp"/>

</body>

</html>