/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.DAO;

import java.util.List;

import es.innovatech.beans.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class ArticulosDAO implements IArticulosDAO{


    @Override
    public List<Articulo> getArticulos() {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            try(ResultSet rs = obtenerarticulos.executeQuery()){
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                while(rs.next()){
                    Articulo articulo = new Articulo();
                    articulo.setId(rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setCategoria(categoriaDAO.getCategoria(rs.getInt("idCategoria")));
                    articulos.add(articulo);
                }
            }           
            
        }catch(SQLException e){
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        }finally{
            this.closeConnection();
        }
        if(articulos.isEmpty()){
            return null;
        }else{
            return articulos;
        }
    }

    @Override
    public Articulo getArticulo(int id) {
        Articulo articulo = new Articulo();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE idProducto = ?";
        try{
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setInt(1, id);
            ResultSet rs = obtenerarticulos.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while(rs.next()){
                articulo.setId(rs.getInt("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecio(rs.getDouble("precio"));
                articulo.setMarca(rs.getString("marca"));
                articulo.setImagen(rs.getString("imagen"));
                articulo.setCategoria(categoriaDAO.getCategoria(rs.getInt("idCategoria")));
            }
        }catch(SQLException e){
            System.out.println("Error al obtener el articulo: " + e.getMessage());
        }finally{
            this.closeConnection();
        }
        return articulo;
    }

    @Override
    public List<Articulo> getArticulosPorCategoria(int idCategoria) {
        List<Articulo> articulos = new ArrayList<Articulo>();
        Connection conexion = null;
        String query = "SELECT * FROM productos WHERE idCategoria = ?";
        try{
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerarticulos = conexion.prepareStatement(query);
            obtenerarticulos.setInt(1, idCategoria);
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            try(ResultSet rs = obtenerarticulos.executeQuery()){
                while(rs.next()){
                    Articulo articulo = new Articulo();
                    articulo.setId(rs.getInt("idProducto"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMarca(rs.getString("marca"));
                    articulo.setImagen(rs.getString("imagen"));
                    articulo.setCategoria(categoriaDAO.getCategoria(rs.getInt("idCategoria")));
                    articulos.add(articulo);
                }
            }
        }catch(SQLException e){
            System.out.println("Error al obtener los articulos: " + e.getMessage());
        }finally{
            this.closeConnection();
        }
        if(articulos.isEmpty()){
            return null;
        }else{
            return articulos;
        }
    }
    
    @Override
    public void closeConnection(){
        ConnectionFactory.closeConnection();
    }
}
