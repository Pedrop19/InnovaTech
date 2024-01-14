<div class="nac w-100 d-flex  justify-content-center bg-transparent">
    <nav class="navbar navbar-expand-lg mt-4 d-flex flex-column  navbar-light"
        style="background: #d0daffa0; border-radius: 10px 10px 0px 0px;">

        <!-- Primera linea -->
        <div class="container-fluid ">
        <i class="fas fa-bars fs-4 mx-2" data-mdb-modal-init data-mdb-target="#filtroModal"></i>
                <div class="modal right fade" id="filtroModal" tabindex="-1" aria-labelledby="filtroModalLabel"
                    aria-hidden="true" data-mdb-backdrop="true" data-mdb-keyboard="true">
                    <div class="modal-dialog modal-filtros w-100">
                        <div class="modal-content bg-info">
                            <div class="modal-header">
                                <h5 class="modal-title" id="filtroModalLabel">B&uacute;squeda Avanzada</h5>
                                <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <label for="categoria">Categor&iacute;as</label>
                                <select class="form-select" name="categoria" id="categoria">
                                    <option value="0" selected>Todas las categorias</option>
                                    <c:forEach var="categoria" items="${categorias}">
                                        <option value="${categoria.id}">${categoria.nombre}</option>
                                    </c:forEach>
                                </select>
                                <br>
                                <label for="marca">Marcas</label>
                                <select class="form-select" name="marca" id="marca">
                                    <option value="0" selected>Todas las marcas</option>
                                    <c:forEach var="marca" items="${marcas}">
                                        <option value="${marca}">${marca}</option>
                                    </c:forEach>
                                </select>
                                <br>
                                <label class="form-label" for="precioFiltro">Precio (1 - 1880)</label>
                                <div class="range" data-mdb-range-init>
                                <input type="range" class="form-range" min="1" max="${sessionScope.precioMasAlto}" id="precioFiltro" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <!-- Toggle button -->
                <div class="input-group">
                    
                    <input type="search" id="searchInput" class="form-control" style="background: #d0daffa0; color:black"
                        placeholder="Search" aria-label="Search" list="datalistOptions" aria-describedby="search-addon" />
                    <span class="input-group-text bg-white" id="search-addon">
                        <datalist id="datalistOptions">
                        <c:forEach var="articulo" items="${articulos2}">
                            <option data-id="${articulo.id}" value="${articulo.nombre}"></option>
                        </c:forEach>
                        </datalist>
                        <i class="fas fa-search"></i>
                    </span>
                </div>
                <a class="navbar-brand mt-2 p-2 mt-lg-0" href="${contexto}/index.jsp">
                    <img src="${contexto}/IMG/Logo.png" height="35" alt="Logo" loading="lazy" />
                </a>
                <div class="blank col-4 ">
                </div>

                <div class="dropdown">
                    <a data-mdb-dropdown-init class="text-reset me-3 dropdown-toggle hidden-arrow" href="#"
                        id="navbarDropdownMenuLink" role="button" aria-expanded="false">

                    </a>
                </div>
                <form action="${contexto}/FrontController" method="post" autocomplete="off">
                    <div class="buttons d-flex col-3 m-0 p-0">
                        <c:choose>
                            <c:when test="${sessionScope.usuario != null}">
                                <div class="dropdown">
                                    <a data-mdb-dropdown-init
                                    class="dropdown-toggle d-flex align-items-center hidden-arrow" href="#"
                                    id="navbarDropdownMenuAvatar" role="button" aria-expanded="false">
                                    <img src="${contexto}/IMG/avatares/${usuario.getAvatar()}"
                                    class="rounded-circle" height="45" alt="Black and White Portrait of a Man"
                                    loading="lazy" />
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end p-3"
                                        aria-labelledby="navbarDropdownMenuAvatar">
                                        <li>
                                            <a class="dropdown-item text-info fw-bold" data-mdb-modal-init
                                                data-mdb-target="#cuentaModal">Mi cuenta</a>
                                        </li>
                                        <li>
                                            <button type="submit" name="button" value="cerrarSesion"
                                                class="mx-3 btn btn-danger">Salir</button>
                                        </li>
                                    </ul>
                                </div>
                                <div class="modal top fade" id="cuentaModal" tabindex="-1"
                                    aria-labelledby="cuentaModalLabel" aria-hidden="true" data-mdb-backdrop="true"
                                    data-mdb-keyboard="true">
                                    <div class="modal-dialog   modal-dialog-centered">
                                        <div class="modal-content" style="background: #484849e9; color:black;">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="cuentaModalLabel">Hola ${usuario.nombre}
                                                </h5>
                                                <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body  text-center">
                                                <h5 class="modal-title" id="cuentaModalLabel">Mi cuenta</h5>
                                                <div class="mb-3">                                              
                                                    <button type="submit" class="btn btn-primary" name="button"
                                                        value="vercuenta">Editar mi perfil</button><br><br>
                                                    <button type="submit" class="btn btn-primary" name="button"
                                                        value="verpedidos">Ver mis pedidos</button>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-mdb-dismiss="modal">
                                                    Cerrar
                                                </button>
                                                <button type="submit" class="btn btn-dark" name="button"
                                                    value="cerrarSesion">Cerrar Sesi&oacute;n</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-dark mx-2" data-mdb-modal-init
                                        data-mdb-target="#iniciarModal">
                                        Iniciar sesi&oacute;n
                                    </button>
                                    <div class="modal top fade" id="iniciarModal" tabindex="-1" aria-labelledby="iniciarModalLabel"
                                        aria-hidden="true" data-mdb-backdrop="true" data-mdb-keyboard="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content bg-light">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="iniciarModalLabel">Iniciar Sesi&oacute;n</h5>
                                                    <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                                        aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body rounded-2">
                                                    <div class="mb-3">
                                                        <div data-mdb-input-init class="form-outline mb-4">
                                                            <input type="text" class="input-box form-control"
                                                                name="email" id="email-modal" autocomplete="off" />
                                                            <label class="form-label text-white" for="email-modal">Email</label>
                                                        </div>
                                                        <div data-mdb-input-init class="form-outline mb-4">
                                                            <input type="password" class="input-box form-control"
                                                                name="password" id="password-modal" autocomplete="off"/>
                                                            <label class="form-label text-white"
                                                                for="password-modal">Contrase&ntilde;a</label>
                                                        </div>
                                                    </div>
                                                    <p class="text-center text-white">&iquest;No tienes cuenta? <button type="button"
                                                            class="bg-transparent border-0 text-info" name="button"
                                                            value="registrar">Registrate</button></p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-dark" name="button"
                                                        value="aceptar">Aceptar</button>
                                                    <button type="button" class="btn btn-danger" data-mdb-dismiss="modal"
                                                        name="button" value="cancelar">
                                                        Cancelar
                                                    </button>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="button" name="button" value="registrar" class="btn btn-light"><a class="text-black"
                                            href="${contexto}/JSP/registrar.jsp">Registrarse</a></button>
                                </c:otherwise>
                        </c:choose>
                    </div>
                </form>
                <i class="fas fa-shopping-cart fs-4 mx-2" data-mdb-modal-init data-mdb-target="#carritoModal"><span
                        class="badge rounded-pill badge-notification bg-danger" id="carritoSize">${sessionScope.carrito.size()}</span></i>
                <div class="modal right fade" id="carritoModal" tabindex="-1" aria-labelledby="carritoModalLabel"
                    aria-hidden="true" data-mdb-backdrop="true" data-mdb-keyboard="true">
                    <div class="modal-dialog modal-carrito w-100">
                        <div class="modal-content ">
                            <div class="modal-header">
                                <h5 class="modal-title" id="carritoModalLabel" style="color: #b27878 !important;">Carrito</h5>
                                <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-12">
                                            <table class="table table-element table-hover bg-info shadow">
                                                <thead>
                                                    <tr>
                                                        <th class="text-black" scope="col">Imagen</th>
                                                        <th class="text-black" scope="col">Producto</th>
                                                        <th class="text-black" scope="col">Cantidad</th>
                                                        <th class="text-black" scope="col">Total</th>
                                                        <th class="text-black" scope="col">Acciones</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="bodyTable">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.carrito != null}">
                                                            <c:forEach var="carrito" items="${sessionScope.carrito}">
                                                                <form action="${contexto}/CarritoController" method="post">
                                                                <input type="hidden" class="idArticulo" name="idArticuloCarrito" value="${carrito.articulo.id}">
                                                                <input type="hidden" class="precioArticulo" value="${carrito.articulo.precio}">
                                                                    <tr class="item-carrito">
                                                                        <td><img src="${contexto}/IMG/productos/${carrito.articulo.imagen}.jpg"
                                                                                height="50" alt="Logo" loading="lazy" /></td>
                                                                        <td class="text-black">${carrito.articulo.nombre}</td>
                                                                        <td><button type="button"  class="fas fa-minus-circle mx-2 rounded-circle p-0 bg-info btn-menos"></button><span class="text-black cantidad">${carrito.cantidad}</span><button type="button"
                                                                                class="fas fa-plus-circle mx-2 rounded-circle p-0 bg-info btn-mas"></button></td>
                                                                        <td class="text-black precioTotal">${carrito.articulo.precio * carrito.cantidad}</td>
                                                                        <td><button type="submit" value="eliminar" class="btn btn-danger"
                                                                                name="button" value="eliminarCarrito"
                                                                                >Eliminar</button>
                                                                        </td>
                                                                    </tr>
                                                                </form>
                                                            </div>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr>
                                                                <td colspan="5" class="text-center text-danger">No hay productos en el
                                                                    carrito</td>
                                                            </tr>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                                                Cerrar
                                            </button>
                                            <form action="${contexto}/CarritoController" method="post">
                                                <button type="submit" name="button" value="vaciar" class="btn btn-dark">Vaciar Carrito</button>
                                                <button type="submit" id="finalizar" name="button" value="finalizar"
                                                    class="btn btn-primary">Finalizar
                                                    Compra</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.usuario != null}">
            <c:choose>
                <c:when test="${sessionScope.usuario.isVerificado() == false}">
                    <div class="container-fluid text-center" style="background-color: #F5C42F; width: 90% !important; border-radius: 0px 0px 10px 10px !important;">
                           <p><i class="fa-solid fa-warning"></i><strong>Verifica tu cuenta!</strong> Te hemos enviado un correo para verificar tu cuenta.</p>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:if>
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
    </nav>
</div>
</div>