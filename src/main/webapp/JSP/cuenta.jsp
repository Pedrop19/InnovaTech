<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
    <c:set var="usuario" value="${sessionScope.usuario}" />

    <head>
        <jsp:directive.include file="/INC/metas.inc" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${css}">
        <link rel="icon" href="${icono}">
        <script src="${contexto}/JS/Busqueda.js" defer></script>
        <script src="${contexto}/JS/EditarPerfil.js" defer></script>
        <title>InnovaTech</title>
    </head>

    <body class="seccion-central">

        <jsp:directive.include file="/INC/navbar.jsp" />
        <section class="min-vh-100  w-100">
            <div class="container mt-3 w-75 vh-100 flex-column">
                <h5 class="text-center text-white">Editar Perfil</h5>
                <form action="${contexto}/EditarPerfil" method="post" class="w-100" enctype="multipart/form-data">
                    <div class="accordion shadow" id="accordionExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Secci&oacute;n 1: Informaci&oacute;n Personal
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="container">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fas fa-user"></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text"  class="form-control text-black"
                                                    name="nombre" value="${usuario.nombre}" />
                                                <label class="form-label" for="form12">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fas fa-user"></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text" class="form-control text-black"
                                                    name="apellidos" value="${usuario.apellidos}" />
                                                <label class="form-label" for="form12">Apellidos</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-phone"></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="number"  class="form-control" name="telefono"
                                                    value="${usuario.telefono}" />
                                                <label class="form-label" for="form12">Tel&eacute;fono</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-address-card"></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text"  class="form-control" name="nif"
                                                    value="${usuario.NIF}" disabled />
                                                <label class="form-label" for="form12">NIF</label>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="form-label" for="form12">Imagen de Perfil</label>
                                    <div class="input-group mb-3">
                                        <div class="form-outline" data-mdb-input-init>
                                            <input type="file"  class="form-control" name="avatar" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Secci&oacute;n 2: Detalles de Acceso
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="container">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-envelope"></i></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="email" name="email" class="form-control "
                                                    value="${usuario.email}" disabled />
                                                <label class="form-label" for="form12">Correo El&eacute;ctronico</label>
                                            </div>
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <button type="button" id="btn-cambiar"
                                                class="btn btn-primary btn-lg mx-1 mb-2">
                                                Cambiar Contraseña
                                            </button>
                                        </div>
                                        <div class="contraseña d-none" id="contrasena">
                                            <div class="input-group mb-3">
                                                <span class="input-group-text" id="basic-addon1"><i
                                                        class="fa-solid fa-lock"></i></span>
                                                <div class="form-outline" data-mdb-input-init>
                                                    <input type="password" id="contrActual" class="form-control"
                                                        name="passwordActual" />
                                                    <label class="form-label" for="form12">Contraseña Actual</label>
                                                </div>
                                            </div>
                                            <div class="input-group mb-3">
                                                <span class="input-group-text" id="basic-addon1"><i
                                                        class="fa-solid fa-lock"></i></span>
                                                <div class="form-outline" data-mdb-input-init>
                                                    <input type="password" id="contrNueva" class="form-control"
                                                        name="passwordNueva" />
                                                    <label class="form-label" for="form12">Contraseña Nueva</label>
                                                </div>
                                            </div>
                                            <div class="input-group mb-3">
                                                <span class="input-group-text" id="basic-addon1"><i
                                                        class="fa-solid fa-lock"></i></span>
                                                <div class="form-outline" data-mdb-input-init>
                                                    <input type="password" id="contrNueva2" class="form-control"
                                                        name="password2" />
                                                    <label class="form-label" for="form12">Repite la contraseña</label>
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-center" id="botonesContr">
                                                <button type="button" id="btn-aceptar"
                                                    class="btn btn-primary btn-lg mx-1 ">
                                                    Aceptar
                                                </button>
                                                <button type="button" id="btn-ocultar"
                                                    class="btn btn-primary btn-lg mx-1 ">
                                                    Cancelar
                                                </button>
                                            </div>
                                            <p class="text-center text-danger" id="error"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Secci&oacute;n 3: Datos de Env&iacute;o
                                </button>
                            </h2>
                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="container">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-house"></i></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text" name="direccion" class="form-control" value="${usuario.direccion}" />
                                                <label class="form-label" for="form12">Direcci&oacute;n</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-house"></i></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="number" max="99999" maxlength="9" name="codigoPostal" class="form-control" value="${usuario.codigoPostal}"/>
                                                <label class="form-label" for="form12">C&oacute;digo Postal</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-house"></i></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text" name="localidad" class="form-control" value="${usuario.localidad}" />
                                                <label class="form-label" for="form12">Localidad</label>
                                            </div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1"><i
                                                    class="fa-solid fa-house"></i></i></span>
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text" name="provincia" class="form-control" value="${usuario.provincia}"/>
                                                <label class="form-label" for="form12">Provincia</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${error != null}">
                        <p class="text-center text-danger mt-3">${error}</p>
                    </c:if>
                    <div class="px-5 justify-content-center align-items-center d-flex ">
                        <button type="submit" name="button" value="aceptar"
                            class="btn btn-primary btn-lg mx-1">Aceptar</button>
                        <button type="submit" name="button" value="cancelar"
                            class="btn btn-primary btn-lg">Cancelar</button>
                        <button type="button" class="btn btn-danger btn-lg mx-1" data-mdb-modal-init
                            data-mdb-target="#alertaBorrarModal">Borrar Cuenta</button>
                        <div class="modal top fade" id="alertaBorrarModal" tabindex="-1"
                            aria-labelledby="alertaBorrarModalLabel" aria-hidden="true" data-mdb-backdrop="true"
                            data-mdb-keyboard="true">
                            <div class="modal-dialog   modal-dialog-centered">
                                <div class="modal-content" style="background: #d0daffd8; color:black;">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="alertaBorrarModalLabel">Borrar Cuenta</h5>
                                        <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <p class="text-center">¿Estas seguro de que quieres borrar tu cuenta?</p>
                                            <p class="text-center text-danger">Esta acci&oacute;n no se puede deshacer.
                                            </p>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                                            Cancelar
                                        </button>
                                        <button type="submit" name="button" value="borrarCuenta"
                                            class="btn btn-dark">Aceptar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </form>
            </div>
        </section>
        <jsp:directive.include file="/INC/footer.jsp" />

        <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>
    </body>

</html>