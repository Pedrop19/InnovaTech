/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.innovatech.beans.LineaPedido;

/**
 *
 * @author pedro
 */
public class LineasPedidoDAO implements ILineasPedidosDAO{

    @Override
    public void registrarLineaPedido(int idPedido, int idProducto, int cantidad) {
        Connection connection = null;
        String query = "INSERT INTO lineaspedidos (idPedido, idProducto, cantidad) VALUES (?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            obtenerPedidos.setInt(2, idProducto);
            obtenerPedidos.setInt(3, cantidad);
            obtenerPedidos.executeUpdate();
            connection.commit();
            this.closeConnection();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    e.printStackTrace();
                    connection.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<LineaPedido> getLineasPedido(int idPedido) {
        Connection connection = null;
        String query = "SELECT * FROM lineaspedidos WHERE idPedido = ?";
        List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();  
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            try (ResultSet rs = obtenerPedidos.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    int cantidad = rs.getInt("cantidad");
                    PedidosDAO pedidosDAO = new PedidosDAO();
                    ArticulosDAO articulosDAO = new ArticulosDAO();
                    LineaPedido lineaPedido = new LineaPedido();
                    lineaPedido.setPedido(pedidosDAO.getPedidoPorId(idPedido));
                    lineaPedido.setArticulo(articulosDAO.getArticulo(idProducto));
                    lineaPedido.setCantidad(cantidad);
                    lineasPedido.add(lineaPedido);
                }
                
            }
            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
        if(lineasPedido.isEmpty()){
            return null;
        }else{
            return lineasPedido;
        }
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
