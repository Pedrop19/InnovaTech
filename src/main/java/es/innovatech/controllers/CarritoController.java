/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java para editar esta plantilla
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.models.Utils;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;

/**
 * El controlador CarritoController maneja las solicitudes relacionadas con el carrito de compras.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "CarritoController", urlPatterns = { "/CarritoController" })
public class CarritoController extends HttpServlet {

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
     * Maneja las solicitudes HTTP POST. Realiza diversas acciones según el botón presionado.
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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Articulo articulo;
        Carrito carrito;
        Utils utils = new Utils();
        Cookie[] cookies = request.getCookies();
        DAOFactory daof = DAOFactory.getDAOFactory();
        String error = "";

        switch (button) {
            case "btnAnadir":
                List<Carrito> carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                String cantidad = request.getParameter("unidades");
                String idProducto = request.getParameter("id");
                if (usuario == null) {
                    if (carritoList == null) {
                        carritoList = new ArrayList<Carrito>();
                        articulo = new Articulo();
                        IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                        articulo = articulosDAO.getArticulo((short) Integer.parseInt(idProducto));
                        carrito = new Carrito();
                        carrito.setArticulo(articulo);
                        carrito.setCantidad(Integer.parseInt(cantidad));
                        carritoList.add(carrito);
                        request.getSession().setAttribute("carrito", carritoList);
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("carrito")) {
                                cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                response.addCookie(cookie);
                            } else {
                                Cookie carritoCookie = new Cookie("carrito",
                                        utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                carritoCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                response.addCookie(carritoCookie);
                            }
                        }
                        url = "index.jsp";
                    } else {
                        Iterator<Carrito> iterator = carritoList.iterator();
                        boolean encontrado = false;

                        while (iterator.hasNext()) {
                            Carrito carrito1 = iterator.next();
                            if (carrito1.getArticulo().getId() == Integer.parseInt(idProducto)) {
                                carrito1.setCantidad(carrito1.getCantidad() + Integer.parseInt(cantidad));
                                request.getSession().setAttribute("carrito", carritoList);
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("carrito")) {
                                        cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                        response.addCookie(cookie);
                                    } else {
                                        Cookie carritoCookie = new Cookie("carrito",
                                                utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                        carritoCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                        response.addCookie(carritoCookie);
                                    }
                                }
                                url = "index.jsp";
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            articulo = new Articulo();
                            IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                            articulo = articulosDAO.getArticulo((short) Integer.parseInt(idProducto));
                            carrito = new Carrito();
                            carrito.setArticulo(articulo);
                            carrito.setCantidad(Integer.parseInt(cantidad));
                            carritoList.add(carrito);
                            request.getSession().setAttribute("carrito", carritoList);
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("carrito")) {
                                    cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                    cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                    response.addCookie(cookie);
                                } else {
                                    Cookie carritoCookie = new Cookie("carrito",
                                            utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                    carritoCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                    response.addCookie(carritoCookie);
                                }
                            }
                            url = "index.jsp";
                            break;
                        }
                        url = "index.jsp";
                    }
                } else {
                    if (carritoList == null) {
                        carritoList = new ArrayList<>();
                        articulo = new Articulo();
                        IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                        ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                        articulo = articulosDAO.getArticulo((short) Integer.parseInt(idProducto));
                        carrito = new Carrito();
                        carrito.setArticulo(articulo);
                        carrito.setCantidad(Integer.parseInt(cantidad));
                        carritoList.add(carrito);
                        request.getSession().setAttribute("carrito", carritoList);
                        IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                        LineaPedido lineaPedido = new LineaPedido();
                        Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                        if (pedido == null) {
                            pedido = new Pedido();
                            pedido.setEstado(Estado.C);
                            pedido.setUsuario(usuario);
                            pedido.setFecha(new Date());
                            pedido.setImporte(articulo.getPrecio() * Integer.parseInt(cantidad));
                            pedido.setIva(pedido.getImporte() * 0.18);
                            pedidosDAO.registrarPedido(pedido);
                            pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                            lineaPedido.setPedido(pedido);
                            lineaPedido.setArticulo(articulo);
                            lineaPedido.setCantidad(Integer.parseInt(cantidad));
                            lineasPedidosDAO.registrarLineaPedido(lineaPedido);
                        }
                        url = "index.jsp";
                    } else {
                        Iterator<Carrito> iterator = carritoList.iterator();
                        boolean encontrado = false;

                        while (iterator.hasNext()) {
                            Carrito carrito3 = iterator.next();
                            if (carrito3.getArticulo().getId() == Integer.parseInt(idProducto)) {
                                carrito3.setCantidad(carrito3.getCantidad() + Integer.parseInt(cantidad));
                                request.getSession().setAttribute("carrito", carritoList);
                                IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                                Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                                if (pedido != null) {
                                    pedido.setImporte(pedido.getImporte()
                                            + carrito3.getArticulo().getPrecio() * Integer.parseInt(cantidad));
                                    pedidosDAO.actualizarPedido(pedido);
                                }
                                ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                                LineaPedido lineaPedido = new LineaPedido();
                                lineaPedido.setPedido(pedido);
                                lineaPedido.setArticulo(carrito3.getArticulo());
                                lineaPedido.setCantidad(carrito3.getCantidad());
                                lineasPedidosDAO.registrarLineaPedido(lineaPedido);
                                url = "index.jsp";
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            articulo = new Articulo();
                            IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                            ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                            articulo = articulosDAO.getArticulo((short) Integer.parseInt(idProducto));
                            carrito = new Carrito();
                            carrito.setArticulo(articulo);
                            carrito.setCantidad(Integer.parseInt(cantidad));
                            carritoList.add(carrito);
                            request.getSession().setAttribute("carrito", carritoList);
                            IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                            Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                            if (pedido != null) {
                                pedido.setImporte(pedido.getImporte()
                                        + carrito.getArticulo().getPrecio() * Integer.parseInt(cantidad));
                                pedidosDAO.actualizarPedido(pedido);
                            }
                            LineaPedido lineaPedido = new LineaPedido();
                            if (pedido != null) {
                                lineaPedido.setPedido(pedido);
                                lineaPedido.setArticulo(articulo);
                                lineaPedido.setCantidad(Integer.parseInt(cantidad));
                                lineasPedidosDAO.registrarLineaPedido(lineaPedido);
                            }
                            url = "index.jsp";
                        }
                    }
                }
                break;

            case "eliminar":
                if (usuario == null) {
                    String id = request.getParameter("idArticuloCarrito");
                    carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                    if (carritoList != null && !carritoList.isEmpty()) {
                        Iterator<Carrito> iteratorEliminar = carritoList.iterator();

                        while (iteratorEliminar.hasNext()) {
                            Carrito carrito1 = iteratorEliminar.next();
                            if (carrito1.getArticulo().getId() == Integer.parseInt(id)) {
                                iteratorEliminar.remove();
                                if (carritoList.isEmpty()) {
                                    request.getSession().removeAttribute("carrito");
                                } else {
                                    request.getSession().setAttribute("carrito", carritoList);
                                }
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("carrito")) {
                                        if (carritoList.isEmpty()) {
                                            cookie.setMaxAge(0);
                                            response.addCookie(cookie);
                                        } else {
                                            cookie.setValue(
                                                    utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                            cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                            response.addCookie(cookie);
                                        }
                                    }
                                }
                                url = "index.jsp";
                                break;
                            }
                        }
                    }
                } else {
                    String id = request.getParameter("idArticuloCarrito");
                    carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                    List<LineaPedido> lineaPedidoList = new ArrayList<>();
                    IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                    ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                    Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                    if (carritoList != null && !carritoList.isEmpty()) {
                        Iterator<Carrito> iteratorEliminar = carritoList.iterator();

                        while (iteratorEliminar.hasNext()) {
                            Carrito carrito1 = iteratorEliminar.next();
                            if (carrito1.getArticulo().getId() == Integer.parseInt(id)) {
                                iteratorEliminar.remove();
                                if (carritoList.isEmpty()) {
                                    request.getSession().removeAttribute("carrito");
                                    lineasPedidosDAO.deleteLineasPedido(pedido.getIdPedido());
                                    pedidosDAO.deletePedido(pedido.getIdPedido());
                                } else {
                                    carritoList.remove(carrito1);
                                    pedido.setImporte(pedido.getImporte()
                                            - carrito1.getArticulo().getPrecio() * carrito1.getCantidad());
                                    pedidosDAO.actualizarPedido(pedido);
                                    lineaPedidoList = lineasPedidosDAO.getLineasPedido(pedido.getIdPedido());
                                    for (LineaPedido lineaPedido : lineaPedidoList) {
                                        if (lineaPedido.getArticulo().getId() == Integer.parseInt(id)) {
                                            lineasPedidosDAO.deleteLineaPedido(lineaPedido);
                                        }
                                    }
                                    request.getSession().setAttribute("carrito", carritoList);
                                }
                                url = "index.jsp";
                                break;
                            }
                        }
                    }
                }
                break;

            case "vaciar":
                if (usuario == null) {
                    carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                    if (carritoList != null && !carritoList.isEmpty()) {
                        Iterator<Carrito> iteratorEliminar = carritoList.iterator();

                        while (iteratorEliminar.hasNext()) {
                            iteratorEliminar.next();
                            iteratorEliminar.remove();
                        }

                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("carrito")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }

                        request.getSession().removeAttribute("carrito");
                        url = "index.jsp";
                    }
                } else {
                    carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                    if (carritoList != null && !carritoList.isEmpty()) {
                        Iterator<Carrito> iteratorEliminar = carritoList.iterator();

                        while (iteratorEliminar.hasNext()) {
                            iteratorEliminar.next();
                            iteratorEliminar.remove();
                        }

                        IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                        Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                        if (pedido != null) {
                            ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                            lineasPedidosDAO.deleteLineasPedido(pedido.getIdPedido());
                            pedidosDAO.deletePedido(pedido.getIdPedido());
                            request.getSession().removeAttribute("carrito");
                        }
                        url = "index.jsp";
                    }
                }
                break;

            case "finalizar":
                usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario == null) {
                    error = "Debes iniciar sesión o registrarte para finalizar la compra";
                    request.setAttribute("error", error);
                    url = "index.jsp";
                } else {
                    url = "JSP/pedido.jsp";
                }
                break;

            default:
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
