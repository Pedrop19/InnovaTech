/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.innovatech.beans.Usuario;
import es.innovatech.models.Utils;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;

/**
 *
 * @author pedro
 */
@WebServlet(name = "CarritoController", urlPatterns = { "/CarritoController" })
public class CarritoController extends HttpServlet {

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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Articulo articulo;
        Carrito carrito;
        List<Carrito> carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
        Utils utils = new Utils();
        Cookie[] cookies = request.getCookies();
        DAOFactory daof = DAOFactory.getDAOFactory();
        String error = "";

        switch (button) {
            case "btnAnadir":
                String cantidad = request.getParameter("unidades");
                String idProducto = request.getParameter("id");
                if (usuario == null) {
                    if (carritoList == null) {
                        carritoList = new ArrayList<Carrito>();
                        articulo = new Articulo();
                        IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                        articulo = articulosDAO.getArticulo(Integer.parseInt(idProducto));
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
                    } else {
                        carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                        for (Carrito carrito1 : carritoList) {
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
                                break;
                            } else {
                                articulo = new Articulo();
                                IArticulosDAO articulosDAO = daof.getIArticulosDAO();
                                articulo = articulosDAO.getArticulo(Integer.parseInt(idProducto));
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
                        }
                    }

                    url = "index.jsp";
                } else {
                }
                break;
            case "eliminar":
                String id = request.getParameter("idArticuloCarrito");
                carritoList = (List<Carrito>) request.getSession().getAttribute("carrito");
                if(carritoList != null && !carritoList.isEmpty()){
                    for (Carrito carrito1 : carritoList) {
                        if (carrito1.getArticulo().getId() == Integer.parseInt(id)) {
                            carritoList.remove(carrito1);
                            if(carritoList.isEmpty()){
                                request.getSession().removeAttribute("carrito");
                            }else{
                                request.getSession().setAttribute("carrito", carritoList);
                            }
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("carrito")) {
                                    if (carritoList.isEmpty()) {
                                        cookie.setMaxAge(0);
                                        response.addCookie(cookie);
                                    } else {
                                        cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
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
                break;
            case "vaciar":
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("carrito")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                request.getSession().removeAttribute("carrito");
                url = "index.jsp";
                break;
            case "finalizar":
                usuario = (Usuario) request.getSession().getAttribute("usuario");
                if(usuario == null){
                    error = "Debes iniciar sesión o registrarte para finalizar la compra";
                    request.setAttribute("error", error);
                    url = "index.jsp";
                }else{
                    url = "JSP/pedido.jsp";
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
