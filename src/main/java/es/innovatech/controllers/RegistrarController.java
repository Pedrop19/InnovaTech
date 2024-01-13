/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java para editar esta plantilla
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.models.Utils;

/**
 * El controlador RegistrarController maneja las solicitudes relacionadas con el registro de usuarios.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "RegistrarController", urlPatterns = { "/RegistrarController" })
@MultipartConfig
public class RegistrarController extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET. Redirige a la página principal.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes HTTP POST. Realiza acciones en función del botón presionado.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = null;
        String button = request.getParameter("button");
        String url = "index.jsp";
        String dirImagen = request.getServletContext().getRealPath("/IMG/avatares/");
        String error = "";
        String exito = "";
        StringBuilder nombreFichero = new StringBuilder();
        String filePath = null;
        Utils utils = new Utils();
        DAOFactory daof = DAOFactory.getDAOFactory();

        switch (button) {
            case "registrar":
                IUsuariosDAO usuarioDao = daof.getIUsuarioDAO();
                usuario = new Usuario();
                Part filePart = request.getPart("avatar");
                if (filePart.getSubmittedFileName() != "") {
                    Enumeration<String> campos = request.getParameterNames();
                    while (campos.hasMoreElements()) {
                        String campo = campos.nextElement();
                        if (request.getParameter(campo).isEmpty() || request.getParameter(campo) == "") {
                            error = "Faltan campos por rellenar";
                            url = "JSP/registrar.jsp";
                            break;
                        } else {
                            switch (campo) {
                                case "nombre":
                                    usuario.setNombre(request.getParameter(campo));
                                    break;
                                case "apellidos":
                                    usuario.setApellidos(request.getParameter(campo));
                                    break;
                                case "email":
                                    usuario.setEmail(request.getParameter(campo));
                                    break;
                                case "nif":
                                    usuario.setNIF(request.getParameter(campo));
                                    break;
                                case "password":
                                    String passwordCifrada = utils.getMd5(request.getParameter(campo));
                                    usuario.setPassword(passwordCifrada);
                                    break;
                                case "telefono":
                                    usuario.setTelefono(Integer.parseInt(request.getParameter(campo)));
                                    break;
                                case "direccion":
                                    usuario.setDireccion(request.getParameter(campo));
                                    break;
                                case "codigoPostal":
                                    usuario.setCodigoPostal(Integer.parseInt(request.getParameter(campo)));
                                    break;
                                case "localidad":
                                    usuario.setLocalidad(request.getParameter(campo));
                                    break;
                                case "provincia":
                                    usuario.setProvincia(request.getParameter(campo));
                                    break;
                                default:
                                    break;
                            }  
                        }
                    }
                    
                    double importe = 0;
                    List<Articulo> articulos = new ArrayList<>();
                    short idProducto = 0;
                    List<Integer> cantidades = new ArrayList<Integer>();
                    int cantidad = 0;

                    List<Carrito> carrito = (List<Carrito>) request.getSession().getAttribute("carrito");
                    IArticulosDAO articulosDao = daof.getIArticulosDAO();
                    Utils util = new Utils();
                    if (carrito != null) {
                        for (Carrito item : carrito) {
                            importe += item.getArticulo().getPrecio() * item.getCantidad();
                            idProducto = item.getArticulo().getId();
                            cantidad = item.getCantidad();
                            cantidades.add(cantidad);
                            articulos.add(articulosDao.getArticulo(idProducto));
                        }

                        usuarioDao.add(usuario);
                        IPedidosDAO pedidosDao = daof.getIPedidosDAO();
                        Pedido pedido = new Pedido();
                        usuario.setId((short) usuarioDao.getLastIdUsuario());
                        pedido.setUsuario(usuario);
                        pedido.setEstado(Estado.C);
                        pedido.setFecha(new Date());
                        pedido.setImporte(importe);
                        pedido.setIva(importe * 0.18);
                        pedidosDao.registrarPedido(pedido);
                        short idPedido = pedidosDao.getUltimoIdPedido();
                        Pedido pedido2 = new Pedido();
                        pedido2 = pedidosDao.getPedidoPorId(idPedido);
                        ILineasPedidosDAO lineasPedidoDao = daof.getILineasPedidoDAO();
                        List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
                        LineaPedido lineaPedido = new LineaPedido();
                        for (int i = 0; i < articulos.size(); i++) {
                            lineaPedido.setArticulo(articulos.get(i));
                            lineaPedido.setCantidad(cantidades.get(i));
                            lineaPedido.setPedido(pedido2);
                            lineasPedidoDao.registrarLineaPedido(lineaPedido);
                            lineasPedido.add(lineaPedido);
                        }
                        pedido.setLineasPedido(lineasPedido);
                        request.getSession().removeAttribute("carrito");
                        Cookie[] cookies = request.getCookies();
                        for (Cookie cookie : cookies) {


                            if (cookie.getName().equals("carrito")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    nombreFichero.append(filePart.getSubmittedFileName());
                    filePath = dirImagen + nombreFichero.toString();
                    filePart.write(filePath);
                    usuario.setAvatar(nombreFichero.toString());
                    exito = "Usuario registrado correctamente";
                    String link = utils.generateVerificationLink(usuario.getEmail(), request.getContextPath());
                    String cuerpo = "Bienvenido" + usuario.getNombre() + "a la tienda online de InnovaTech. Para verificar tu cuenta, haz click en el siguiente enlace: " + link;
                    utils.enviarConGMail(usuario.getEmail(), "Verifica tu cuenta", cuerpo);
                    request.setAttribute("exito", exito);
                    } else {
                        usuarioDao.add(usuario);
                        nombreFichero.append(filePart.getSubmittedFileName());
                        filePath = dirImagen + nombreFichero.toString();
                        filePart.write(filePath);
                        usuario.setAvatar(nombreFichero.toString());
                        exito = "Usuario registrado correctamente";
                        String link = utils.generateVerificationLink(usuario.getEmail(), request.getContextPath());
                        String cuerpo = "Bienvenido " + usuario.getNombre() + " a la tienda online de InnovaTech.\n Para verificar tu cuenta, haz click en el siguiente enlace: " + link;
                        utils.enviarConGMail(usuario.getEmail(), "Verifica tu cuenta", cuerpo);
                        request.setAttribute("exito", exito);
                    }
                    url = "index.jsp";
                } else {
                    error = "Faltan campos por rellenar";
                    url = "JSP/registrar.jsp";
                }
                break;
            case "cancelar":
                url = "index.jsp";
                break;
            default:
                break;
        }

    request.setAttribute("error",error);
    request.getRequestDispatcher(url).forward(request,response);

    }

   

    /**
     * Devuelve una breve descripción del servlet.
     *
     * @return una cadena que contiene la descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
