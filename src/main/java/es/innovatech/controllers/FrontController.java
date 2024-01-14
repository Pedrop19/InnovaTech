/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java para editar esta plantilla
 */
package es.innovatech.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.innovatech.models.Utils;

/**
 * El controlador FrontController maneja las solicitudes generales de la aplicación.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "FrontController", urlPatterns = { "/FrontController" })
public class FrontController extends HttpServlet {

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
        String button = request.getParameter("button");
        String url = "";
        String exito = "";
        String error = "";
        Utils utils = new Utils();
        DAOFactory daof = DAOFactory.getDAOFactory();

        switch (button) {
            case "aceptar":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                HttpSession session = request.getSession();
                session.removeAttribute("carrito");

                if (email.equals("") || password.equals("")) {
                    error = "Los campos no pueden estar vacíos";
                    request.setAttribute("error", error);
                    url = "index.jsp";
                } else {
                    IUsuariosDAO usuarioDAO = daof.getIUsuarioDAO();
                    String passwordCodificada = utils.getMd5(password);
                    Usuario usuario = usuarioDAO.getUsuario(passwordCodificada, email);
                    List<LineaPedido> lineaPedidoList = new ArrayList<>();
                    ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                    if (usuario != null) {
                        List<Carrito> carritoList = new ArrayList<>();
                        Pedido pedido = new Pedido();
                        IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                        pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                        Cookie[] cookies = request.getCookies();
                        Cookie carritoCookie = null;
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if ("carrito".equals(cookie.getName())) {
                                    carritoCookie = cookie;
                                    carritoCookie.setMaxAge(0);
                                    response.addCookie(carritoCookie);
                                    break;
                                    
                                }
                            }
                        }
                        if (pedido != null) {
                            pedido.setLineasPedido(lineasPedidosDAO.getLineasPedido(pedido.getIdPedido()));
                            lineaPedidoList = pedido.getLineasPedido();
                            if(lineaPedidoList != null){
                                for (LineaPedido linea : lineaPedidoList) {
                                    Carrito carrito = new Carrito();
                                    carrito.setArticulo(linea.getArticulo());
                                    carrito.setCantidad(linea.getCantidad());
                                    carritoList.add(carrito);
                                }
                            }
                            session.setAttribute("carrito", carritoList);
                        }
                        usuario.setUltimoAcceso(new Date());
                        exito = "Bienvenido " + usuario.getNombre();
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
                session = request.getSession();
                session.removeAttribute("usuario");
                session.removeAttribute("carrito");
                url = "index.jsp";
                break;
            case "vercuenta":
                url = "JSP/cuenta.jsp";
                break;
            case "verpedidos":
                IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                List<Pedido> pedidos = new ArrayList<Pedido>();
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario != null) {
                    pedidos = pedidosDAO.listarPedidos(usuario.getId(), Estado.F);
                    if (pedidos != null) {
                        request.getSession().setAttribute("pedidos", pedidos);
                    }
                    url = "JSP/pedidosFinalizados.jsp";
                }
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);
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
