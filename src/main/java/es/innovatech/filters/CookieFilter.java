package es.innovatech.filters;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pedro
 */
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Carrito;
import es.innovatech.models.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebFilter("/*")
public class CookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            List<Articulo> articulos = (List<Articulo>) session.getAttribute("articulos");
            Cookie[] cookies = httpRequest.getCookies();
            Cookie carritoCookie = null;

            if (articulos == null) {
                DAOFactory daoFactory = DAOFactory.getDAOFactory();
                IArticulosDAO articulosDAO = daoFactory.getIArticulosDAO();
                articulos = articulosDAO.getArticulos();

                List<Articulo> articulos2 = new ArrayList<Articulo>();

                HashSet<Integer> indicesAleatorios = new HashSet<Integer>();

                while (indicesAleatorios.size() < 6) {
                    int indiceAleatorio = (int) (Math.random() * articulos.size());
                    indicesAleatorios.add(indiceAleatorio);
                }

                for (int indice : indicesAleatorios) {
                    Articulo articulo = articulos.get(indice);
                    articulos2.add(articulo);
                }

                session.setAttribute("articulos", articulos2);
            }

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
                carrito = utils.cookieCarritoaList(cookieDecodificada);
                session.setAttribute("carrito", carrito);
            }
            chain.doFilter(request, response);

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
