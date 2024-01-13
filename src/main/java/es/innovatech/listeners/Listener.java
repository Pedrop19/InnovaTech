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

import java.util.List;

/**
 * @author pedro
 */
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ICategoriasDAO categoriasDAO = daoFactory.getICategoriasDAO();
        IArticulosDAO articulosDAO = daoFactory.getIArticulosDAO();
        List<String> marcas = articulosDAO.getMarcas();
        List<Categoria> categorias = categoriasDAO.getCategorias();
        sce.getServletContext().setAttribute("categorias", categorias);
        sce.getServletContext().setAttribute("marcas", marcas);
    }
}
