/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.DAOFactory;

import es.innovatech.DAO.IArticulosDAO;
import es.innovatech.DAO.ICategoriasDAO;
import es.innovatech.DAO.ILineasPedidosDAO;
import es.innovatech.DAO.IPedidosDAO;
import es.innovatech.DAO.IUsuariosDAO;

/**
 * Factoría abstracta para obtener instancias de interfaces DAO específicas de una fuente de datos (por ejemplo, MySQL).
 * Implementa métodos abstractos para cada interfaz DAO que debe proporcionar una subclase concreta.
 * También proporciona un método estático para obtener una instancia de la implementación concreta de esta factoría.
 * 
 * @author pedro
 */
public abstract class DAOFactory {
    /**
     * Constante que representa la fuente de datos MySQL.
     */
    public static final int MYSQL = 1;

    /**
     * Obtiene una instancia de la interfaz IArticulosDAO específica de la fuente de datos.
     * 
     * @return Instancia de la interfaz IArticulosDAO.
     */
    public abstract IArticulosDAO getIArticulosDAO();

    /**
     * Obtiene una instancia de la interfaz ILineasPedidosDAO específica de la fuente de datos.
     * 
     * @return Instancia de la interfaz ILineasPedidosDAO.
     */
    public abstract ILineasPedidosDAO getILineasPedidoDAO();

    /**
     * Obtiene una instancia de la interfaz IPedidosDAO específica de la fuente de datos.
     * 
     * @return Instancia de la interfaz IPedidosDAO.
     */
    public abstract IPedidosDAO getIPedidosDAO();

    /**
     * Obtiene una instancia de la interfaz IUsuariosDAO específica de la fuente de datos.
     * 
     * @return Instancia de la interfaz IUsuariosDAO.
     */
    public abstract IUsuariosDAO getIUsuarioDAO();

    /**
     * Obtiene una instancia de la interfaz ICategoriasDAO específica de la fuente de datos.
     * 
     * @return Instancia de la interfaz ICategoriasDAO.
     */
    public abstract ICategoriasDAO getICategoriasDAO();

    /**
     * Obtiene una instancia de la implementación concreta de esta factoría.
     * 
     * @return Instancia de la implementación concreta de DAOFactory.
     */
    public static DAOFactory getDAOFactory(){
        DAOFactory daof = new MySQLDAOFactory();
        return daof;
    }
}
