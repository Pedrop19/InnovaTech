/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ICategoriasDAO;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.Categoria;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;
import es.innovatech.models.Utils;

/**
 * El controlador Ajax maneja las solicitudes Ajax del cliente.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "AjaxController", urlPatterns = { "/AjaxController" })
public class AjaxController extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET. Redirige a la página de inicio.
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
     * Maneja las solicitudes HTTP POST.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera el cuerpo de la solicitud JSON
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
        String id;
        String accion = jsonObject.getString("accion");
        List<Carrito> carritoList;
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        Utils utils = new Utils();
        int cantidad = 0;
        PrintWriter out = response.getWriter();
        DAOFactory daof = DAOFactory.getDAOFactory();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        switch (accion) {
            case "sumar":
                if (usuario == null) {
                    id = jsonObject.getString("id");
                    int idInt = Integer.parseInt(id);
                    carritoList = (List<Carrito>) session.getAttribute("carrito");
                    for (Carrito carrito : carritoList) {
                        if (carrito.getArticulo().getId() == idInt) {
                            carrito.setCantidad(carrito.getCantidad() + 1);
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("carrito")) {
                                    cookie.setValue(
                                            utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                    cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                    response.addCookie(cookie);
                                }
                            }
                            cantidad = carrito.getCantidad();
                            session.setAttribute("carrito", carritoList);
                            break;
                        }
                    }
                    response.setContentType("application/json");
                    out = response.getWriter();
                    JSONObject jsonDatos = new JSONObject();
                    jsonDatos.put("cantidad", cantidad);
                    jsonDatos.put("size", carritoList.size());
                    String jsonDatosString = jsonDatos.toString();
                    out.print(jsonDatosString);
                    out.flush();
                } else {
                    id = jsonObject.getString("id");
                    int idInt = Integer.parseInt(id);
                    carritoList = (List<Carrito>) session.getAttribute("carrito");
                    IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                    ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                    Pedido pedido = new Pedido();
                    pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                    List<LineaPedido> lineasPedido = null;
                    for (Carrito carrito : carritoList) {
                        if (carrito.getArticulo().getId() == idInt) {
                            carrito.setCantidad(carrito.getCantidad() + 1);
                            cantidad = carrito.getCantidad();
                            pedido.setImporte(pedido.getImporte() + carrito.getArticulo().getPrecio());
                            pedidosDAO.actualizarPedido(pedido);
                            lineasPedido = lineasPedidosDAO.getLineasPedido(pedido.getIdPedido());
                            if (lineasPedido != null) {
                                for (LineaPedido lineaPedido : lineasPedido) {
                                    if (lineaPedido.getArticulo().getId() == carrito.getArticulo().getId()) {
                                        lineaPedido.setCantidad(lineaPedido.getCantidad() + 1);
                                        lineasPedidosDAO.actualizarLineaPedido(lineaPedido);
                                        lineasPedido.add(lineaPedido);
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    response.setContentType("application/json");
                    out = response.getWriter();
                    JSONObject jsonDatos = new JSONObject();
                    jsonDatos.put("cantidad", cantidad);
                    jsonDatos.put("size", carritoList.size());
                    String jsonDatosString = jsonDatos.toString();
                    out.print(jsonDatosString);
                    out.flush();
                }

                break;
            case "restar":
                if (usuario == null) {
                    id = jsonObject.getString("id");
                    int idInt = Integer.parseInt(id);
                    carritoList = (List<Carrito>) session.getAttribute("carrito");

                    for (Carrito carrito : carritoList) {
                        if (carrito.getArticulo().getId() == idInt) {
                            carrito.setCantidad(carrito.getCantidad() - 1);
                            if (carrito.getCantidad() == 0) {
                                carritoList.remove(carrito);
                                if (carritoList.isEmpty()) {
                                    for (Cookie cookie : cookies) {
                                        if (cookie.getName().equals("carrito")) {
                                            cookie.setMaxAge(0);
                                            response.addCookie(cookie);
                                        }
                                    }
                                    cantidad = 0;
                                    session.removeAttribute("carrito");
                                    break;
                                } else {
                                    for (Cookie cookie : cookies) {
                                        if (cookie.getName().equals("carrito")) {
                                            cookie.setValue(
                                                    utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                                            cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                                            response.addCookie(cookie);
                                        }
                                    }
                                    cantidad = carrito.getCantidad();
                                    session.setAttribute("carrito", carritoList);
                                    break;
                                }
                            }
                            cantidad = carrito.getCantidad();
                            break;
                        }
                    }
                    response.setContentType("application/json");
                    out = response.getWriter();
                    JSONObject jsonDatos = new JSONObject();
                    jsonDatos.put("cantidad", cantidad);
                    jsonDatos.put("size", carritoList.size());
                    String jsonDatosString = jsonDatos.toString();
                    out.print(jsonDatosString);
                    out.flush();
                } else {
                    id = jsonObject.getString("id");
                    int idInt = Integer.parseInt(id);
                    carritoList = (List<Carrito>) session.getAttribute("carrito");
                    IPedidosDAO pedidosDAO = daof.getIPedidosDAO();
                    List<LineaPedido> lineasPedido = null;
                    ILineasPedidosDAO lineasPedidosDAO = daof.getILineasPedidoDAO();
                    Pedido pedido = pedidosDAO.getPedidoPorEstado(usuario.getId(), Estado.C);
                    lineasPedido = lineasPedidosDAO.getLineasPedido(pedido.getIdPedido());
                    for (Carrito carrito : carritoList) {
                        if (carrito.getArticulo().getId() == idInt) {
                            carrito.setCantidad(carrito.getCantidad() - 1);
                            if (carrito.getCantidad() == 0) {
                                carritoList.remove(carrito);
                                if (carritoList.isEmpty()) {
                                    if (lineasPedido != null) {
                                        for (LineaPedido lineaPedido : lineasPedido) {
                                            lineasPedidosDAO.deleteLineasPedido(lineaPedido.getPedido().getIdPedido());
                                            lineasPedido.remove(lineaPedido);
                                        }
                                    }
                                    pedido.setLineasPedido(lineasPedido);
                                    pedidosDAO.deletePedido(pedido.getIdPedido());
                                    session.removeAttribute("carrito");
                                    break;
                                } else {
                                    if (lineasPedido != null) {
                                        for (LineaPedido lineaPedido : lineasPedido) {
                                            if (lineaPedido.getArticulo().getId() == carrito.getArticulo().getId()) {
                                                lineasPedidosDAO.deleteLineaPedido(lineaPedido);
                                                lineasPedido.remove(lineaPedido);
                                                break;
                                            }
                                        }
                                    }
                                    pedido.setLineasPedido(lineasPedido);
                                    pedido.setImporte(pedido.getImporte() - carrito.getArticulo().getPrecio());
                                    pedidosDAO.actualizarPedido(pedido);
                                    break;
                                }
                            } else {
                                cantidad = carrito.getCantidad();
                                pedido.setImporte(pedido.getImporte() - carrito.getArticulo().getPrecio());
                                if (lineasPedido != null) {
                                    for (LineaPedido lineaPedido : lineasPedido) {
                                        if (lineaPedido.getArticulo().getId() == carrito.getArticulo().getId()) {
                                            lineaPedido.setCantidad(lineaPedido.getCantidad() - 1);
                                            lineasPedidosDAO.actualizarLineaPedido(lineaPedido);
                                            break;
                                        }
                                    }
                                }
                                pedido.setLineasPedido(lineasPedido);
                                pedidosDAO.actualizarPedido(pedido);
                                break;
                            }
                        }
                    }
                    response.setContentType("application/json");
                    out = response.getWriter();
                    JSONObject jsonDatos = new JSONObject();
                    jsonDatos.put("cantidad", cantidad);
                    jsonDatos.put("size", carritoList.size());
                    String jsonDatosString = jsonDatos.toString();
                    out.print(jsonDatosString);
                    out.flush();
                }

                break;
            case "validateEmail":
                String email = jsonObject.getString("email");
                boolean emailExists = verificarExistenciaEmail(email);
                response.setContentType("application/json");
                out = response.getWriter();
                out.print(emailExists);
                out.flush();
                break;
            case "filtrarProductosCategoria":
                String context = request.getContextPath();
                String categoria = jsonObject.getString("categoria");
                byte idCategoria = (byte) Integer.parseInt(categoria);
                ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
                IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                Categoria categoriaObj = categoriaDAO.getCategoria(idCategoria);
                request.getSession().setAttribute("categoria", categoriaObj);
                List<Articulo> productos = articulosDAO.getArticulosPorCategoria(idCategoria);
                request.getSession().setAttribute("productos", productos);

                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("redirectUrl", context + "/JSP/productos.jsp");

                response.setContentType("application/json");
                out = response.getWriter();
                out.print(jsonResponse.toString());
                out.flush();
                break;
            case "filtrarProductosSearch":
            case "filtrarProductosID":
                context = request.getContextPath();
                String idArticulo = jsonObject.getString("idProducto");
                byte idArticuloInt = (byte) Integer.parseInt(idArticulo);
                articulosDAO = daof.getIArticulosDAO();
                Articulo articulo = articulosDAO.getArticulo(idArticuloInt);
                request.getSession().setAttribute("articulo", articulo);

                jsonResponse = new JSONObject();
                jsonResponse.put("redirectUrl", context + "/JSP/articulo.jsp");

                response.setContentType("application/json");
                out = response.getWriter();
                out.print(jsonResponse.toString());
                out.flush();
                break;
            case "filtrarProductosMarca":
                context = request.getContextPath();
                String marca = jsonObject.getString("marca");
                articulosDAO = daof.getIArticulosDAO();
                List<Articulo> productosMarca = articulosDAO.getArticulosPorMarca(marca);
                request.getSession().setAttribute("productos", productosMarca);

                jsonResponse = new JSONObject();
                jsonResponse.put("redirectUrl", context + "/JSP/productos.jsp");

                response.setContentType("application/json");
                out = response.getWriter();
                out.print(jsonResponse.toString());
                out.flush();
                break;
            case "filtrarProductosPrecio":
                context = request.getContextPath();
                String precio = jsonObject.getString("precio");
                int precioInt = Integer.parseInt(precio);
                articulosDAO = daof.getIArticulosDAO();
                List<Articulo> productosPrecio = articulosDAO.getArticulosPrecioMenorA(precioInt);
                request.getSession().setAttribute("productos", productosPrecio);

                jsonResponse = new JSONObject();
                jsonResponse.put("redirectUrl", context + "/JSP/productos.jsp");

                response.setContentType("application/json");
                out = response.getWriter();
                out.print(jsonResponse.toString());
                out.flush();
                break;
            default:
                break;
        }
    }

    /**
     * Verifica la existencia de un correo electrónico en la base de datos.
     *
     * @param email correo electrónico a verificar
     * @return true si el correo electrónico existe, false de lo contrario
     */
    private boolean verificarExistenciaEmail(String email) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO usuarioDao = daof.getIUsuarioDAO();
        Usuario usuario = usuarioDao.getUsuariobyEmail(email);
        if (usuario != null) {
            return true;
        }
        return false;
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
