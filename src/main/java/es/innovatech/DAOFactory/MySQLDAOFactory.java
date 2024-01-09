package es.innovatech.DAO;

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
