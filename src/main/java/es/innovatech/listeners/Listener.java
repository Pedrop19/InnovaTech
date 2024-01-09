/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ICategoriasDAO;
import es.innovatech.beans.Categoria;
import es.innovatech.beans.Articulo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author pedro
 */
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ICategoriasDAO categoriasDAO = daoFactory.getICategoriasDAO();
        List<Categoria> categorias = categoriasDAO.getCategorias();
        sce.getServletContext().setAttribute("categorias", categorias);

        IArticulosDAO articulosDAO = daoFactory.getIArticulosDAO();
        List<Articulo> articulos = articulosDAO.getArticulos();

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

        sce.getServletContext().setAttribute("articulos", articulos2);

    }
}
