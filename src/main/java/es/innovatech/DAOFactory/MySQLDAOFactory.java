/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.DAOFactory;

import es.innovatech.DAO.ArticulosDAO;
import es.innovatech.DAO.CategoriaDAO;
import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ICategoriasDAO;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;
import es.innovatech.DAO.LineasPedidoDAO;
import es.innovatech.DAO.PedidosDAO;
import es.innovatech.DAO.UsuarioDAO;

/**
 * Implementación específica de una factoría DAO para MySQL.
 * Proporciona instancias concretas de las interfaces DAO para interactuar con una fuente de datos MySQL.
 * Extiende la clase abstracta DAOFactory y proporciona implementaciones para cada método abstracto.
 * 
 * @author pedro
 */
public class MySQLDAOFactory extends DAOFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public IArticulosDAO getIArticulosDAO() {
        return new ArticulosDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILineasPedidosDAO getILineasPedidoDAO() {
        return new LineasPedidoDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPedidosDAO getIPedidosDAO() {
        return new PedidosDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUsuariosDAO getIUsuarioDAO() {
        return new UsuarioDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICategoriasDAO getICategoriasDAO() {
        return new CategoriaDAO();
    }

}
