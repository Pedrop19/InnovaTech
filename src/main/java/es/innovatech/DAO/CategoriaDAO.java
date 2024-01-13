/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.DAO;

import es.innovatech.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * La clase CategoriaDAO implementa la interfaz ICategoriasDAO y maneja la interacción con la base de datos
 * para obtener información relacionada con las categorías.
 * 
 * Se utiliza el patrón DAO (Data Access Object) para encapsular el acceso a la base de datos.
 * 
 * La clase también contiene un método para cerrar la conexión con la base de datos.
 * 
 * @author pedro
 */
public class CategoriaDAO implements ICategoriasDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> getCategorias() {
        Connection conexion = null;
        String query = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<Categoria>();
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerCategorias = conexion.prepareStatement(query);
            try (ResultSet rs = obtenerCategorias.executeQuery()) {
                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId((byte) rs.getInt("idCategoria"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setImagen(rs.getString("imagen"));
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las categorias: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (categorias.isEmpty()) {
            return null;
        } else {
            return categorias;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria getCategoria(byte id) {
        Categoria categoria = new Categoria();
        Connection conexion = null;
        String query = "SELECT * FROM categorias WHERE idCategoria = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerCategorias = conexion.prepareStatement(query);
            obtenerCategorias.setInt(1, id);
            ResultSet rs = obtenerCategorias.executeQuery();
            while (rs.next()) {
                categoria.setId((byte) rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setImagen(rs.getString("imagen"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoria: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return categoria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
