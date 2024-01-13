/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java para editar esta plantilla
 */
package es.innovatech.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Pedido;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;

/**
 * El controlador FinalizarCompraController maneja las solicitudes relacionadas con la finalización de la compra.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "FinalizarCompraController", urlPatterns = {"/FinalizarCompraController"})
public class FinalizarCompraController extends HttpServlet {

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
     * Maneja las solicitudes HTTP POST. Realiza acciones para finalizar la compra si un usuario está autenticado.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String url = "";
        String exito = "Compra realizada con éxito";
        if(usuario != null){
            Pedido pedido = new Pedido();
            DAOFactory daoFactory = DAOFactory.getDAOFactory();
            IPedidosDAO pedidosDAO = daoFactory.getIPedidosDAO();
            pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
            if(pedido != null){
                pedido.setEstado(Estado.F);
                pedido.setIva(pedido.getImporte() * 0.18);
                pedidosDAO.actualizarPedido(pedido);
                request.getSession().removeAttribute("carrito");
                request.setAttribute("exito", exito);
                url = "index.jsp";
            }
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
