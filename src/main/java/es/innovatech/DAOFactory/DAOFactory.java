/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.DAO;

/**
 *
 * @author pedro
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;
    
    public abstract IArticulosDAO getIArticulosDAO();
    public abstract ILineasPedidosDAO getILineasPedidoDAO();
    public abstract IPedidosDAO getIPedidosDAO();
    public abstract IUsuariosDAO getIUsuarioDAO();
    public abstract ICategoriasDAO getICategoriasDAO();

    
    public static DAOFactory getDAOFactory(int tipo){
        DAOFactory daof = null;
        
        switch(tipo){
            case MYSQL:
                daof = new MySQLDAOFactory();
                break;
        }
        
        return daof;
    }
    
}
