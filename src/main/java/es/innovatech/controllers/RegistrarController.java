/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.DAO.LineasPedidoDAO;
import es.innovatech.DAO.PedidosDAO;
import es.innovatech.DAO.UsuarioDAO;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.Pedido;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "RegistrarController", urlPatterns = { "/RegistrarController" })
@MultipartConfig
public class RegistrarController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
                if (filePart != null) {
                    Enumeration<String> campos = request.getParameterNames();
                    while (campos.hasMoreElements()) {
                        String campo = campos.nextElement();
                        if (request.getParameter(campo).isEmpty() || request.getParameter(campo) == "") {
                            error = "Faltan campos por rellenar";
                            url = "JSP/registro.jsp";
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
                    nombreFichero.append(filePart.getSubmittedFileName());
                    filePath = dirImagen + nombreFichero.toString();
                    filePart.write(filePath);
                    usuario.setAvatar(nombreFichero.toString());
                }
                double importe = 0;
                List<Integer> idProductos = new ArrayList<Integer>();
                int idProducto = 0;
                List<Integer> cantidades = new ArrayList<Integer>();
                int cantidad = 0;

                List<Carrito> carrito = (List<Carrito>) request.getSession().getAttribute("carrito");
                for (Carrito item : carrito) {
                    importe += item.getArticulo().getPrecio() * item.getCantidad();
                    idProducto = item.getArticulo().getId();
                    cantidad = item.getCantidad();
                    cantidades.add(cantidad);
                    idProductos.add(idProducto);
                }

                usuarioDao.add(usuario);
                IPedidosDAO pedidosDao = daof.getIPedidosDAO();
                Pedido pedido = new Pedido();
                usuario.setId(usuarioDao.getLastIdUsuario());
                pedido.setUsuario(usuario);
                pedido.setEstado(Estado.C);
                pedido.setFecha(utils.getFechaActual());
                pedido.setImporte(importe);
                pedido.setIva(18);
                pedidosDao.registrarPedido(pedido);
                int idPedido = pedidosDao.getUltimoIdPedido();
                ILineasPedidosDAO lineasPedidoDao = daof.getILineasPedidoDAO();
                for (int i = 0; i < idProductos.size(); i++) {
                    lineasPedidoDao.registrarLineaPedido(idPedido, idProductos.get(i), cantidades.get(i));
                }
                request.getSession().removeAttribute("carrito");
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("carrito")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                exito = "Usuario registrado correctamente";
                request.setAttribute("exito", exito);
                url = "index.jsp";
                break;
            case "cancelar":
                url = "index.jsp";
                break;
            default:
                break;
        }

        request.setAttribute("error", error);
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
