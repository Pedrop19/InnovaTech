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
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.Categoria;
import es.innovatech.beans.Usuario;
import es.innovatech.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "AjaxController", urlPatterns = { "/AjaxController" })
public class AjaxController extends HttpServlet {
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
        String cantidadString = "";
        PrintWriter out = response.getWriter();
        DAOFactory daof = DAOFactory.getDAOFactory();
        

        switch (accion) {
            case "sumar":
                id = jsonObject.getString("id");
                carritoList = (List<Carrito>) session.getAttribute("carrito");
                int idInt = Integer.parseInt(id);
                for (Carrito carrito : carritoList) {
                    if (carrito.getArticulo().getId() == idInt) {
                        carrito.setCantidad(carrito.getCantidad() + 1);
                        cantidad = carrito.getCantidad();
                        break;
                    }
                }
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("carrito")) {
                        cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
                        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
                        response.addCookie(cookie);
                    }
                }
                session.setAttribute("carrito", carritoList);
                response.setContentType("application/json");
                out = response.getWriter();
                cantidadString = String.valueOf(cantidad);
                out.print(cantidadString);
                out.flush();
                break;
            case "restar":
                id = jsonObject.getString("id");
                idInt = Integer.parseInt(id);
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
                                        cookie.setValue(utils.codificarCookie(utils.listCarritoToCookie(carritoList)));
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
                cantidadString = String.valueOf(cantidad);
                out.print(cantidadString);
                out.flush();
                break;
            case "validarEmail":
                String email = jsonObject.getString("email");
                boolean emailExists = verificarExistenciaEmail(email);
                response.setContentType("application/json");
                out = response.getWriter();
                out.print(emailExists);
                out.flush();
                break;
            case "filtrarProductosCategoria":
                sb = new StringBuilder();
                try {
                    while ((line = request.getReader().readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String context = request.getContextPath();
                String categoria = jsonObject.getString("categoria");
                int idCategoria = Integer.parseInt(categoria);
                ICategoriasDAO categoriaDAO =  daof.getICategoriasDAO();
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
            default:
                break;
        }
    }

    private boolean verificarExistenciaEmail(String email) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO usuarioDao = daof.getIUsuarioDAO();
        Usuario usuario = usuarioDao.getUsuariobyEmail(email);
        if (usuario != null) {
            return true;
        }
        return false;
    }
}
