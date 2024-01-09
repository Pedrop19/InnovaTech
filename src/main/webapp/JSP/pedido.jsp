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
    <script src="${contexto}/JS/Pedido.js" defer></script>
    <script src="${contexto}/JS/Busqueda.js" defer></script>
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
        <table class="table align-middle mb-0 my-5" style="background-color: #d0daffa0;">
           
            <thead class="">
                <tr>
                    <th>Nombre</th>
                    <th>Marca</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody id="tbody">
                
                <tr>
                    <td>
                        <p  class="fw-bold mb-1">Total (Con Iva)</p>
                    </td>
                    <td>
                        <p class="fw-normal mb-1"></p>
                    </td>
                    <td>
                        <p id="totalIva" class="fw-normal mb-1">120.00</p>
                    </td>
                    <td>
                        <p id="cantidadTotal" class="fw-normal mb-1 ">3</p>
                    </td>
                    <td>
                    <form action="/FinalizarCompraController" method="post">
                        <button type="submit" class="btn btn-dark btn-rounded btn-sm fw-bold"
                            data-mdb-ripple-color="dark">
                            Finalizar Compra
                        </button>
                       
                        <button type="button" class="btn btn-outline-danger btn-rounded btn-sm fw-bold"
                            data-mdb-ripple-color="dark">
                            Cancelar
                        </button>
                    </td>
                    </form>

                </tr>
            </tbody>
            
        </table>
    </section>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
    <jsp:directive.include file="/INC/footer.jsp"/>

</body>

</html>