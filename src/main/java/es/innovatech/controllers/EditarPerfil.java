/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.innovatech.controllers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.beans.Usuario;
import es.innovatech.models.Utils;

/**
 * El controlador EditarPerfil maneja las solicitudes relacionadas con la edición del perfil del usuario.
 * 
 * Este servlet utiliza la anotación @WebServlet para mapear las URL que maneja.
 * 
 * @author pedro
 */
@WebServlet(name = "EditarPerfil", urlPatterns = { "/EditarPerfil" })
@MultipartConfig
public class EditarPerfil extends HttpServlet {

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
        Usuario usuario = null;
        IUsuariosDAO usuarioDao = null;
        String button = request.getParameter("button");
        String url = "index.jsp";
        usuario = (Usuario) request.getSession().getAttribute("usuario");
        String dirImagen = request.getServletContext().getRealPath("/IMG/avatares/");
        String error = "";
        StringBuilder nombreFichero = new StringBuilder();
        String filePath = null;
        DAOFactory daof = DAOFactory.getDAOFactory();

        switch (button) {
            case "aceptar":
                usuarioDao = daof.getIUsuarioDAO();
                Part filePart = request.getPart("avatar");
                if (filePart != null) {
                    if (filePart.getContentType().equals("image/jpg") || filePart.getContentType().equals("image/png")
                            || filePart.getContentType().equals("image/jpeg")) {
                        if (filePart.getSize() < 102400) {
                            String extension = ".jpg";
                            if (filePart.getContentType().equals("image/png")) {
                                extension = ".png";
                            } else if (filePart.getContentType().equals("image/jpeg")) {
                                extension = ".jpeg";
                            }
                            nombreFichero.append("avatar_").append(usuario.getId()).append(extension);
                            filePath = dirImagen + nombreFichero.toString();
                            filePart.write(filePath);
                            usuario.setAvatar(nombreFichero.toString());
                        } else {
                            error = "El tamaño de la imagen no puede superar los 100KB";
                            request.setAttribute("error", error);
                            url = "JSP/cuenta.jsp";
                        }
                    }
                    Enumeration<String> campos = request.getParameterNames();
                    while (campos.hasMoreElements()) {
                        String campo = campos.nextElement();
                        if (!campo.equals("password") && !campo.equals("password2")) {
                            if (request.getParameter(campo).isEmpty() || request.getParameter(campo) == "" ) {
                                error = "El campo " + campo + " no puede estar vacío";
                                request.setAttribute("error", error);
                                url = "JSP/cuenta.jsp";
                            } else {
                                switch (campo) {
                                    case "nombre":
                                        usuario.setNombre(request.getParameter(campo));
                                        break;
                                    case "apellidos":
                                        usuario.setApellidos(request.getParameter(campo));
                                        break;
                                    case "email":
                                        usuario.setEmail(request.getParameter(campo));
                                        break;
                                    case "nif":
                                        usuario.setNIF(request.getParameter(campo));
                                        break;
                                    case "telefono":
                                        usuario.setTelefono(Integer.parseInt(request.getParameter(campo)));
                                        break;
                                    case "direccion":
                                        usuario.setDireccion(request.getParameter(campo));
                                        break;
                                    case "codigoPostal":
                                        usuario.setCodigoPostal(Integer.parseInt(request.getParameter(campo)));
                                        break;
                                    case "localidad":
                                        usuario.setLocalidad(request.getParameter(campo));
                                        break;
                                    case "provincia":
                                        usuario.setProvincia(request.getParameter(campo));
                                        break;
                                    default:
                                        break;
                                }

                            }
                        }else{
                            if (request.getParameter(campo).equals("")) {
                                usuario.setPassword(usuario.getPassword());
                            } else {
                                String password2 = request.getParameter("password2");
                                if(request.getParameter(campo).equals(password2)){
                                    Utils utils = new Utils();
                                    String passwordCifrada = utils.getMd5(request.getParameter(campo));
                                    usuario.setPassword(passwordCifrada);
                                }else{
                                    error = "Las contraseñas no coinciden";
                                    request.setAttribute("error", error);
                                    url = "JSP/cuenta.jsp";
                                }
                            }
                            break;
                        }

                    }
                }
                usuarioDao.update(usuario);
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
