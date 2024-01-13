/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.DAO;

import java.util.List;

import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Articulo;
import es.innovatech.beans.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La clase ArticulosDAO implementa la interfaz IArticulosDAO y maneja la interacción con la base de datos
 * para obtener información relacionada con los artículos.
 * 
 * Se utiliza el patrón DAO (Data Access Object) para encapsular el acceso a la base de datos.
 * 
 * La clase también contiene métodos para cerrar la conexión con la base de datos.
 * 
 * @author pedro
 */
public class ArticulosDAO implements IArticulosDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Articulo> getArticulosOrdenadosAlfabeticamente() {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos ORDER BY nombre ASC";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            DAOFactory daof = DAOFactory.getDAOFactory();
            ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
            Categoria categoria = new Categoria();
            try (ResultSet rs = obtenerarticulos.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId((short) rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setIva(articulo.getPrecio() * 0.18);
                    categoria = categoriaDAO.getCategoria((byte) rs.getInt("idCategoria"));
                    articulo.setCategoria(categoria);
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (articulos.isEmpty()) {
            return null;
        } else {
            return articulos;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Articulo getArticulo(short id) {
        Articulo articulo = new Articulo();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE idProducto = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setInt(1, id);
            ResultSet rs = obtenerarticulos.executeQuery();
            DAOFactory daof = DAOFactory.getDAOFactory();
            ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
            Categoria categoria = new Categoria();
            while (rs.next()) {
                articulo.setId((short) rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecio(rs.getDouble("precio"));
                articulo.setMarca(rs.getString("marca"));
                articulo.setImagen(rs.getString("imagen"));
                articulo.setIva(articulo.getPrecio() * 0.18);
                categoria = categoriaDAO.getCategoria((byte) rs.getInt("idCategoria"));
                articulo.setCategoria(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el articulo: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return articulo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Articulo> getArticulosPorCategoria(byte idCategoria) {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE idCategoria = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setInt(1, idCategoria);
            DAOFactory daof = DAOFactory.getDAOFactory();
            ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
            Categoria categoria = new Categoria();
            categoria = categoriaDAO.getCategoria(idCategoria);
            try (ResultSet rs = obtenerarticulos.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId((short) rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setIva(articulo.getPrecio() * 0.18);
                    articulo.setCategoria(categoria);
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (articulos.isEmpty()) {
            return null;
        } else {
            return articulos;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMarcas() {
        List<String> marcas = new ArrayList<String>();
        Connection conexion = null;
        String query = "SELECT DISTINCT marca FROM productos";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenermarcas = conexion.prepareStatement(query);
            try (ResultSet rs = obtenermarcas.executeQuery()) {
                while (rs.next()) {
                    marcas.add(rs.getString("marca"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las marcas: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (marcas.isEmpty()) {
            return null;
        } else {
            return marcas;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Articulo> getArticulosPorMarca(String marca) {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE marca = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setString(1, marca);
            DAOFactory daof = DAOFactory.getDAOFactory();
            ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
            Categoria categoria = new Categoria();
            try (ResultSet rs = obtenerarticulos.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId((short) rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setIva(articulo.getPrecio() * 0.18);
                    categoria = categoriaDAO.getCategoria((byte) rs.getInt("idCategoria"));
                    articulo.setCategoria(categoria);
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (articulos.isEmpty()) {
            return null;
        } else {
            return articulos;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Articulo> getArticulosPrecioMenorA(int precio) {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE precio <= ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setInt(1, precio);
            DAOFactory daof = DAOFactory.getDAOFactory();
            ICategoriasDAO categoriaDAO = daof.getICategoriasDAO();
            Categoria categoria = new Categoria();
            try (ResultSet rs = obtenerarticulos.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId((short) rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setIva(articulo.getPrecio() * 0.18);
                    categoria = categoriaDAO.getCategoria((byte) rs.getInt("idCategoria"));
                    articulo.setCategoria(categoria);
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (articulos.isEmpty()) {
            return null;
        } else {
            return articulos;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrecioMasAlto() {
        int precio = 0;
        Connection conexion = null;
        String query = "SELECT MAX(precio) AS precio FROM productos";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerprecio = conexion.prepareStatement(query);
            try (ResultSet rs = obtenerprecio.executeQuery()) {
                while (rs.next()) {
                    precio = rs.getInt("precio");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el precio mas alto: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return precio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
