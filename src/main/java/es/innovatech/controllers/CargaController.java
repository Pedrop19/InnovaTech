/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java para editar esta plantilla
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.models.Utils;

/**
 * El controlador Carga maneja las solicitudes GET para cargar datos en la sesión y redirigir a la página principal.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "CargaController", urlPatterns = { "/CargaController" })
public class CargaController extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET. Carga datos en la sesión y redirige a la página principal.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Articulo> articulos = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        Cookie carritoCookie = null;

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        IArticulosDAO articulosDAO = daoFactory.getIArticulosDAO();
        articulos = articulosDAO.getArticulosOrdenadosAlfabeticamente();
        session.setAttribute("articulos2", articulos);

        List<Articulo> articulos2 = new ArrayList<>();
        int precioMasAlto = articulosDAO.getPrecioMasAlto();
        session.setAttribute("precioMasAlto", precioMasAlto);

        HashSet<Integer> indicesAleatorios = new HashSet<>();

        do {
            int indiceAleatorio = (int) (Math.random() * articulos.size());
            indicesAleatorios.add(indiceAleatorio);
        } while (indicesAleatorios.size() < 6);

        for (int indice : indicesAleatorios) {
            Articulo articulo = articulos.get(indice);
            articulos2.add(articulo);
        }

        session.setAttribute("articulos", articulos2);

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("carrito".equals(cookie.getName())) {
                    carritoCookie = cookie;
                    break;
                }
            }
        }

        if (carritoCookie != null) {
            List<Carrito> carrito = new ArrayList<>();
            Utils utils = new Utils();
            String cookieDecodificada = utils.decodificarCookie(carritoCookie.getValue());
            carrito = utils.cookieCarritoAList(cookieDecodificada);
            session.setAttribute("carrito", carrito);
        }

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
