<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                    <h3 class="text-center text-white">Pedidos Finalizados</h3>
                </div>
            </div>
        </div>
        <table class="table align-middle mb-0 my-5" style="background-color: #d0daffa0;">
            <thead class="text-black">
                <tr>
                    <th>IdPedido</th>
                    <th>Fecha</th>
                    <th>Importe (Sin Iva)</th>
                    <th>Iva</th>
                    <th>Precio Total (Con IVA)</th>
                </tr>
            </thead>
            <tbody id="tbody">
            <c:choose>
                <c:when test="${empty sessionScope.pedidos}">
                    <tr>
                        <td colspan="5" class="text-center">No hay pedidos finalizados</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="pedido" items="${sessionScope.pedidos}">
                    <fmt:parseDate var="fecha" value="${pedido.fecha}" pattern="yyyy-MM-dd" />
                    
                                <tr>
                                    <td class="text-black">                      
                                        <p class="fw-bold mb-1">${pedido.idPedido}</p>
                                    </td>
                                    <td class="text-black">
                                        <p class="fw-normal mb-1"><fmt:formatDate value="${fecha}" pattern="dd/MM/yyyy" /></p>
                                    </td>
                                    <td class="text-black">
                                        <p class="fw-normal mb-1">${pedido.importe}</p>
                                    </td>
                                    <td class="text-black">
                                        <p class="fw-normal mb-1">${pedido.iva}</p>
                                    </td>
                                    <td class="text-black">
                                        <p class="fw-normal mb-1">${pedido.importe + pedido.iva}</p>
                                    </td>
                                </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
        <a href="${contexto}/index.jsp" class="btn btn-success">Volver</a>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
    <jsp:directive.include file="/INC/footer.jsp"/>

</body>

</html>