<!DOCTYPE html>
<html lang="es">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<head>
    <jsp:directive.include file="/INC/metas.inc"/>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${css}">
  <link rel="icon" href="${icono}"
  <script src="${contexto}/JS/Ajax.js" defer></script>
  <script src="${contexto}/JS/Busqueda.js" defer></script>
  <script src="${contexto}/JS/Carrito.js" defer></script>
  <title>InnovaTech</title>
</head>

<body class="seccion-central">

<jsp:directive.include file="/INC/navbar.jsp"/>

  <section >
    <div class="container my-5 ">
      <div class="row d-flex justify-content-center align-items-center h-100  ">
        <div class=" col-xl-9 ">

          <h1 class="text-white text-center mb-4">Registrarse</h1>

        <form action="${contexto}/RegistrarController" method="post" enctype="multipart/form-data">
          <div class="card form-container " style="border-radius: 15px;">
            <div class="card-body">
              <h5 class="card-title text-center">Datos Personales</h5>
              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Nombre</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="text"  name="nombre" class="form-control form-control-lg input-box" placeholder="John" />

                </div>
              </div>
              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Apellidos</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="text"  name="apellidos" class="form-control form-control-lg input-box" placeholder="Smith" />

                </div>
              </div>


              <div class="row align-items-center py-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Email </h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="email" id="email"  name="email" class="form-control form-control-lg input-box" placeholder="example@example.com" />

                </div>
              </div>

              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">NIF</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="text" id="nif" name="nif" class="form-control form-control-lg input-box" placeholder="12345678A" />

                </div>

              </div>

              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Tel&eacute;fono</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="number"  name="telefono" maxlength="9" class="form-control form-control-lg input-box" placeholder="612345678" />

                </div>
              </div>

              <hr class="mx-n3">
              <h5 class="card-title text-center">Datos de Env&iacute;o</h5>
              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Direcci&oacute;n</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="text"  name="direccion" class="form-control form-control-lg input-box" placeholder="C/ Inventada" />

                </div>
              </div>

              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">C&oacute;digo Postal</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="number" id="codigoPostal" maxlength="5" name="codigoPostal" maxlength="6" class="form-control form-control-lg input-box" placeholder="123456" />

                </div>
              </div>
              <div class="row align-items-center pt-4 pb-3">
                <div class="col-md-2 ps-5">

                  <h6 class="mb-0">Localidad</h6>

                </div>
                <div class="col-md-4 pe-5">

                  <input type="text"  name="localidad" class="form-control form-control-lg input-box" placeholder="Trujillanos" />

                </div>
                <div class="col-md-2 ps-5">

                  <h6 class="mb-0">Provincia</h6>

                </div>
                <div class="col-md-4 pe-5">

                  <input type="text"  name="provincia" class="form-control form-control-lg input-box" placeholder="Badajoz" />

                </div>
              </div>
              <hr class="mx-n3">
              <h5 class="card-title text-center">Datos de Acceso</h5>
              <div class="row align-items-center py-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Contraseña</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="password" id="password1" class="form-control input-box" rows="3"  name="password"></input>

                </div>
              </div>
              <div class="row align-items-center py-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Repite tu Contraseña</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input type="password" id="password2" class="form-control input-box" rows="3"></input>

                </div>
              </div>

              <hr class="mx-n3">
              <h5 class="card-title text-center">Imagen de Perfil</h5>
              <div class="row align-items-center py-3">
                <div class="col-md-3 ps-5">

                  <h6 class="mb-0">Cargar Avatar</h6>

                </div>
                <div class="col-md-9 pe-5">

                  <input class="form-control form-control-lg input-box" id="formFileLg" type="file" name="avatar" />
                  <div class="small text-muted mt-2">Sube una foto de ti o de lo que tu quieras.</div>

                </div>
              </div>
              <p class="text-danger text-center" id="error"></p>
              <c:if test="${error != null}">  
                <p class="text-center text-danger">${error}</p>
              </c:if>
              <hr class="mx-n3">

              <p class="text-center">Al registrarte, aceptas nuestras Condiciones de uso y Política de privacidad.</p>
              <p class="text-center">¿Ya tienes una cuenta? <a class="" data-mdb-modal-init
                  data-mdb-target="#iniciarModal">Inicia sesi&oacute;n</a></p>
              <div class="px-5 py-4 justify-content-center align-items-center d-flex ">
                <button type="submit" name="button" id="btn-registrar" class="btn btn-primary btn-lg mx-1" value="registrar">Registrarse</button>
                <button type="submit" name="button" class="btn btn-primary btn-lg" value="cancelar">Cancelar</button>
              </div>

            </div>
          </div>
        </form>
        </div>
      </div>
    </div>

    

  </section>
<jsp:directive.include file="/INC/footer.inc"/>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js"></script>
</body>

</html>