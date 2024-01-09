/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author pedro
 */

public class CategoriaDAO implements ICategoriasDAO {


    @Override
    public List<Categoria> getCategorias() {
        Connection conexion = null;
        String query = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<Categoria>();
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerCategorias = conexion.prepareStatement(query);
            try(ResultSet rs = obtenerCategorias.executeQuery()){
                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("idCategoria"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setImagen(rs.getString("imagen"));
                    categorias.add(categoria);
                }
            }
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error al obtener las categorias: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if(categorias.isEmpty()){
            return null;
        }else{
            return categorias;
        }
    }

    @Override
    public Categoria getCategoria(int id) {
        Categoria categoria = new Categoria();
        Connection conexion = null;
        String query = "SELECT * FROM categorias WHERE idCategoria = ?";
        try{
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerCategorias = conexion.prepareStatement(query);
            obtenerCategorias.setInt(1, id);
            ResultSet rs = obtenerCategorias.executeQuery();
            while (rs.next()) {
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setImagen(rs.getString("imagen"));
            }
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoria: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return categoria;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
