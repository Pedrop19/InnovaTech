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
import es.innovatech.DAOFactory.DAOFactory;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public IArticulosDAO getIArticulosDAO() {
        return new ArticulosDAO();
    }

    @Override
    public ILineasPedidosDAO getILineasPedidoDAO() {
        return new LineasPedidoDAO();
    }

    @Override
    public IPedidosDAO getIPedidosDAO() {
        return new PedidosDAO();
    }

    @Override
    public IUsuariosDAO getIUsuarioDAO() {
        return new UsuarioDAO();
    }

    @Override
    public ICategoriasDAO getICategoriasDAO() {
        return new CategoriaDAO();
    }

}
