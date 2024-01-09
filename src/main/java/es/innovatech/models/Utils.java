/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import es.innovatech.DAO.CategoriaDAO;
import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.ICategoriasDAO;
import es.innovatech.beans.Carrito;
import es.innovatech.beans.Articulo;

/**
 *
 * @author pedro
 */
public class Utils {

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
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Date convertirAfechaEspanola(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = formatter.format(fecha);
        Date fechaEspanola = null;
        try {
            fechaEspanola = formatter.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaEspanola;
    }

    public List<Carrito> cookieCarritoaList(String cookieCarrito) {
        List<Carrito> carrito = new ArrayList<Carrito>();
        String[] productos = cookieCarrito.split(";");
        DAOFactory daof = DAOFactory.getDAOFactory();
        ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
        categoriaDAO = new CategoriaDAO();
        for (int i = 0; i < productos.length; i++) {
            String[] producto = productos[i].split(",");
            Articulo p = new Articulo();
            // 1%2C19%2CProcesador+-+Intel+Celeron+G1840+2.8Ghz+Box%2C40.0%2C+Intel%2Chttp%3A%2F%2Flocalhost%3A8080%2FInnovaTech%2FIMG%2Fproductos%2Fintelsocket%2F14258595863011386.jpg%2C1%3B
            p.setId(Integer.parseInt(producto[0]));
            p.setCategoria(categoriaDAO.getCategoria(Integer.parseInt(producto[1])));
            p.setNombre(producto[2]);
            p.setPrecio(Double.parseDouble(producto[3]));
            p.setMarca(producto[4]);
            p.setImagen(producto[5]);
            Carrito c = new Carrito();
            c.setArticulo(p);
            c.setCantidad(Integer.parseInt(producto[6]));
            carrito.add(c);
        }
        return carrito;
    }

    public String listCarritoToCookie(List<Carrito> carrito) {
        String cookieCarrito = "";
        for (int i = 0; i < carrito.size(); i++) {
            cookieCarrito += carrito.get(i).getArticulo().getId() + ","
                    + carrito.get(i).getArticulo().getCategoria().getId() + ","
                    + carrito.get(i).getArticulo().getNombre() + "," + carrito.get(i).getArticulo().getPrecio() + ","
                    + carrito.get(i).getArticulo().getMarca() + "," + carrito.get(i).getArticulo().getImagen() + ","
                    + carrito.get(i).getCantidad() + ";";
        }
        return cookieCarrito;
    }

    public String codificarCookie(String cookie) {
        String cookieCodificada = null;
        try {
            cookieCodificada = URLEncoder.encode(cookie, "UTF-8");
            return cookieCodificada;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String decodificarCookie(String cookie) {
        String cookieDecodificada = null;
        try {
            cookieDecodificada = URLDecoder.decode(cookie, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookieDecodificada;
    }

    public Date getFechaActual() {
        Date fechaActual = new Date();
        return fechaActual;
    }

}
