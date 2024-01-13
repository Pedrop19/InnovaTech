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

import es.innovatech.beans.Articulo;
import es.innovatech.beans.Categoria;
import es.innovatech.beans.LineaPedido;
import es.innovatech.beans.Pedido;

/**
 * Clase que implementa la interfaz ILineasPedidosDAO para gestionar las líneas de pedidos en la base de datos.
 * 
 * @author pedro
 */
public class LineasPedidoDAO implements ILineasPedidosDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarLineaPedido(LineaPedido lineaPedido) {
        Connection connection = null;
        String query = "INSERT INTO lineaspedidos (idPedido, idProducto, cantidad) VALUES (?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, lineaPedido.getPedido().getIdPedido());
            obtenerPedidos.setInt(2, lineaPedido.getArticulo().getId());
            obtenerPedidos.setInt(3, lineaPedido.getCantidad());
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LineaPedido> getLineasPedido(short idPedido) {
        Connection connection = null;
        String query = "SELECT lp.idProducto, lp.cantidad, p.*, a.*, c.nombre as nombre_categoria, c.IdCategoria, c.Imagen as imagen_categoria FROM lineaspedidos lp JOIN pedidos p ON lp.idPedido = p.idPedido JOIN productos a ON lp.idProducto = a.idProducto JOIN categorias c ON a.IdCategoria = c.IdCategoria WHERE lp.idPedido = ?";
        List<LineaPedido> lineasPedido = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            try (ResultSet rs = obtenerPedidos.executeQuery()) {
                while (rs.next()) {
                    short idProducto =(short) rs.getInt("idProducto");
                    int cantidad = rs.getInt("cantidad");
                    Pedido pedido = new Pedido();
                    Articulo articulo = new Articulo();
                    Categoria categoria = new Categoria();
                    categoria.setId((byte) rs.getInt("IdCategoria"));
                    categoria.setNombre(rs.getString("nombre_categoria"));
                    categoria.setImagen(rs.getString("imagen_categoria"));
                    articulo.setId(idProducto);
                    articulo.setNombre(rs.getString("Nombre"));
                    articulo.setDescripcion(rs.getString("Descripcion"));
                    articulo.setPrecio(rs.getDouble("Precio"));
                    articulo.setImagen(rs.getString("Imagen"));
                    articulo.setIva(articulo.getPrecio() * 0.18);
                    articulo.setCategoria(categoria);
                    pedido.setIdPedido(idPedido);
                    pedido.setImporte(rs.getDouble("importe"));
                    pedido.setIva(pedido.getImporte() * 0.18);
                    pedido.setFecha(rs.getDate("fecha"));
                    LineaPedido lineaPedido = new LineaPedido();
                    lineaPedido.setPedido(pedido);
                    lineaPedido.setArticulo(articulo);
                    lineaPedido.setCantidad(cantidad);
                    lineasPedido.add(lineaPedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
        if (lineasPedido.isEmpty()) {
            return null;
        } else {
            return lineasPedido;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteLineasPedido(short idPedido) {
        Connection connection = null;
        String query = "DELETE FROM lineaspedidos WHERE idPedido = ?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */    
    @Override
    public void deleteLineaPedido(LineaPedido lineaPedido) {
        Connection connection = null;
        String query = "DELETE FROM lineaspedidos WHERE idPedido = ? AND idProducto = ?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, lineaPedido.getPedido().getIdPedido());
            obtenerPedidos.setInt(2, lineaPedido.getArticulo().getId());
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarLineaPedido(LineaPedido lineaPedido) {
        Connection connection = null;
        String query = "UPDATE lineaspedidos SET cantidad = ? WHERE idPedido = ? AND idProducto = ?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, lineaPedido.getCantidad());
            obtenerPedidos.setInt(2, lineaPedido.getPedido().getIdPedido());
            obtenerPedidos.setInt(3, lineaPedido.getArticulo().getId());
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
