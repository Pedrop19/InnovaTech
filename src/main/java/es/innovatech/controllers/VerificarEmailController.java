/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.innovatech.models.Utils;

/**
 *
 * @author pedro
 */
@WebServlet(name = "VerificarEmailController", urlPatterns = { "/VerificarEmailController" })
public class VerificarEmailController extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET. Verifica el email del usuario.
     *
     * @param request  solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmailFromLink = request.getParameter("email");
        Utils utils = new Utils();
        String exito = "La verificación ha sido exitosa.";
        String error = "La verificación ha fallado.";
        String url = "JSP/verificacionEmail.jsp";


        if (utils.verifyEmailLink(userEmailFromLink)) {
            request.setAttribute("exito", exito);
        } else {
            request.setAttribute("error", error);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
