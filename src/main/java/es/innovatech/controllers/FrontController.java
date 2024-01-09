/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.innovatech.DAO.DAOFactory;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;

import java.util.ArrayList;
import java.util.List;
import es.innovatech.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "FrontController", urlPatterns = { "/FrontController" })
public class FrontController extends HttpServlet {

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
        String button = request.getParameter("button");
        String url = "";
        String exito = "";
        String error = "";
        Date fecha = null;
        Utils utils = new Utils();
        DAOFactory daof = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        switch (button) {
            case "registrar":
                url = "JSP/registrar.jsp";
                break;
            case "aceptar":
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                if (email.equals("") || password.equals("")) {
                    error = "Los campos no pueden estar vacíos";
                    request.setAttribute("error", error);
                    url = "index.jsp";
                } else {
                    IUsuariosDAO usuarioDAO = daof.getIUsuarioDAO();
                    String passwordCodificada = utils.getMd5(password);
                    Usuario usuario = usuarioDAO.getUsuario(passwordCodificada, email);
                    if (usuario != null) {
                        HttpSession session = request.getSession();
                        List<Carrito> carritoList = new ArrayList<>();
                        List<LineaPedido> lineaPedidoList = new ArrayList<>();
                        LineaPedido lineaPedido = new LineaPedido();
                        Pedido pedido = new Pedido();
                        IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                        ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                        pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                        if (pedido != null) {
                            lineaPedidoList = lineasPedidosDAO.getLineasPedido(pedido.getIdPedido());
                            for (LineaPedido linea : lineaPedidoList) {
                                Carrito carrito = new Carrito();
                                carrito.setArticulo(linea.getArticulo());
                                carrito.setCantidad(linea.getCantidad());
                                carritoList.add(carrito);
                            }
                            if (session.getAttribute("carrito") == null) {
                                session.setAttribute("carrito", carritoList);
                            } else {
                                session.removeAttribute("carrito");
                                session.setAttribute("carrito", carritoList);
                            }
                        }
                        exito = "Bienvenido " + usuario.getNombre() + " " + usuario.getUltimoAcceso();
                        session.setAttribute("usuario", usuario);
                        request.setAttribute("exito", exito);
                        url = "index.jsp";
                    } else {
                        error = "El usuario o la contraseña no son correctos";
                        request.setAttribute("error", error);
                        url = "index.jsp";
                    }
                }
                break;
            case "cerrarSesion":
                HttpSession session = request.getSession();
                session.invalidate();
                url = "index.jsp";
                break;
            case "vercuenta":
                url = "JSP/cuenta.jsp";
                break;
            case "finalizar":
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario != null) {
                    url = "JSP/pedido.jsp";
                } else {
                    error = "Debe iniciar sesión o registrarse para finalizar la compra";
                    request.setAttribute("error", error);
                    url = "index.jsp";
                }
            default:
                break;
        }

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
