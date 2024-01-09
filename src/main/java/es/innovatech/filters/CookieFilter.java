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

import es.innovatech.beans.Carrito;
import es.innovatech.models.Utils;

import java.io.IOException;
import java.util.ArrayList;
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


            Cookie[] cookies = httpRequest.getCookies();
            Cookie carritoCookie = null;

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
                HttpSession session = httpRequest.getSession();
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

