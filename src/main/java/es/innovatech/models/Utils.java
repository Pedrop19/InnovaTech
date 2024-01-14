/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;

import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.Usuario;
import es.innovatech.beans.Articulo;

/**
 * Clase de utilidad con métodos diversos para operaciones comunes.
 * 
 * @author pedro
 */
public class Utils {

    /**
     * Calcula el hash MD5 de una contraseña.
     * 
     * @param password Contraseña a la que se le calculará el hash MD5.
     * @return Hash MD5 de la contraseña.
     */
    public String getMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte una cadena de texto (Value de una Cookie) con información sobre productos en una lista de objetos Carrito.
     * 
     * @param cookieCarrito Cadena de texto con información sobre productos.
     * @return Lista de objetos Carrito.
     */
    public List<Carrito> cookieCarritoAList(String cookieCarrito) {
        List<Carrito> carrito = new ArrayList<Carrito>();
        String[] productos = cookieCarrito.split(";");
        DAOFactory daof = DAOFactory.getDAOFactory();
        IArticulosDAO articuloDAO = daof.getIArticulosDAO();
        for (int i = 0; i < productos.length; i++) {
            String[] producto = productos[i].split(",");
            short idProducto = (short) Integer.parseInt(producto[0]);
            Articulo articulo = articuloDAO.getArticulo(idProducto);
            Carrito c = new Carrito();
            c.setArticulo(articulo);
            c.setCantidad(Integer.parseInt(producto[1]));
            carrito.add(c);
        }
        return carrito;
    }

    /**
     * Convierte una lista de objetos Carrito en una cadena de texto (Value de una Cookie) con información sobre productos.
     * 
     * @param carrito Lista de objetos Carrito.
     * @return Cadena de texto con información sobre productos.
     */
    public String listCarritoToCookie(List<Carrito> carrito) {
        StringBuilder cookieCarrito = new StringBuilder();
        for (int i = 0; i < carrito.size(); i++) {
            cookieCarrito.append(carrito.get(i).getArticulo().getId()).append(",")
                    .append(carrito.get(i).getCantidad()).append(";");
        }
        return cookieCarrito.toString();
    }

    /**
     * Codifica una cadena de texto para ser utilizada como cookie en una URL.
     * 
     * @param cookie Cadena de texto a codificar.
     * @return Cadena de texto codificada.
     */
    public String codificarCookie(String cookie) {
        try {
            return URLEncoder.encode(cookie, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decodifica una cadena de texto que ha sido utilizada como cookie en una URL.
     * 
     * @param cookie Cadena de texto a decodificar.
     * @return Cadena de texto decodificada.
     */
    public String decodificarCookie(String cookie) {
        try {
            return URLDecoder.decode(cookie, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
   
    /**
     * Genera un enlace de verificación de correo electrónico.
     * 
     * @param userEmail Dirección de correo electrónico del usuario.
     * @return Enlace de verificación de correo electrónico.
     */
    public String generateVerificationLink(String userEmail, String contexto) {
        return "http://localhost:8080/" + contexto + "/VerificarEmailController?email=" + userEmail;
    }



    /**
     * Verifica un enlace de verificación de correo electrónico.
     * 
     * @param userEmail Dirección de correo electrónico del usuario.
     * @return true si el enlace es válido, false en caso contrario.
     */
    public boolean verifyEmailLink(String userEmail) {
        // Lógica de verificación, por ejemplo, puedes validar si el correo está en tu base de datos local
        // y marcarlo como verificado
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO usuarioDAO = daof.getIUsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuariobyEmail(userEmail);
        if(usuario != null && !usuario.isVerificado()){
            usuario.setVerificado(true);
            usuarioDAO.update(usuario); 
        }else{
            return false;
        }
        return true;
    }

    /**
     * Envía un correo electrónico a un destinatario.
     * 
     * @param destinatario Dirección de correo del destinatario.
     * @param asunto Asunto del correo.
     * @param cuerpo Cuerpo del correo.
     */
    public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = "innovatechshop2024@gmail.com";
        String claveemail = "lmsz pfke tfmg vmkm";
    
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", claveemail);    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.port", "587"); 
    
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
    
        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); 
            message.setSubject(asunto);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(cuerpo, "text/html");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            
            
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   
        }
      }
}
