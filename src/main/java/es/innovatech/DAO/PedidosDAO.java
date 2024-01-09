/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.innovatech.beans.Pedido;
import es.innovatech.enums.Estado;

/**
 *
 * @author pedro
 */
public class PedidosDAO implements IPedidosDAO {

    @Override
    public void registrarPedido(Pedido pedido) {
        Connection connection = null;
        String query = "INSERT INTO pedidos (idUsuario, fecha, estado, importe, iva) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, pedido.getUsuario().getId());
            obtenerPedidos.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            obtenerPedidos.setString(3, pedido.getEstado().toString());
            obtenerPedidos.setDouble(4, pedido.getImporte());
            obtenerPedidos.setDouble(5, pedido.getIva());
            obtenerPedidos.executeUpdate();
            connection.commit();
            this.closeConnection();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    e.printStackTrace();
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<Pedido> listarPedidos(int idUsuario) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            Connection conexion = ConnectionFactory.getConnection();
            String query = "SELECT * FROM pedidos WHERE idUsuario = ?";
            PreparedStatement obtenerPedidos = conexion.prepareStatement(query);
            obtenerPedidos.setInt(1, idUsuario);
            try (ResultSet rs = obtenerPedidos.executeQuery()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setIdPedido(rs.getInt("idPedido"));
                    pedido.setUsuario(new UsuarioDAO().getUsuarioPorId(rs.getInt("idUsuario")));
                    pedido.setFecha(rs.getDate("fecha"));
                    pedido.setEstado(Estado.valueOf(rs.getString("estado")));
                    pedido.setImporte(rs.getDouble("importe"));
                    pedido.setIva(rs.getDouble("iva"));
                    pedidos.add(pedido);
                }
            }
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        if (pedidos.isEmpty()) {
            return null;
        } else {
            return pedidos;
        }
    }

    @Override
    public Pedido getPedidoPorEstado(int idUsuario, Estado estado) {
        Pedido pedido = new Pedido();
        Connection conexion = null;
        String query = "SELECT * FROM pedidos WHERE idUsuario = ? AND estado = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = conexion.prepareStatement(query);
            obtenerPedidos.setInt(1, idUsuario);
            obtenerPedidos.setString(2, estado.toString());
            ResultSet rs = obtenerPedidos.executeQuery();
            while (rs.next()) {
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setUsuario(new UsuarioDAO().getUsuarioPorId(rs.getInt("idUsuario")));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setEstado(Estado.valueOf(rs.getString("estado")));
                pedido.setImporte(rs.getDouble("importe"));
                pedido.setIva(rs.getDouble("iva"));
            }
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return pedido;
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        Connection connection = null;
        String query = "UPDATE pedidos SET importe = ? WHERE idPedido = ?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setDouble(1, pedido.getImporte());
            obtenerPedidos.setInt(2, pedido.getIdPedido());
            obtenerPedidos.executeUpdate();
            connection.commit();
            this.closeConnection();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    e.printStackTrace();
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public Pedido getPedidoPorId(int idPedido) {
        Pedido pedido = new Pedido();
        Connection connection = null;
        String query = "SELECT * FROM pedidos WHERE idPedido = ?";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            ResultSet rs = obtenerPedidos.executeQuery();
            while (rs.next()) {
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setUsuario(new UsuarioDAO().getUsuarioPorId(rs.getInt("idUsuario")));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setEstado(Estado.valueOf(rs.getString("estado")));
                pedido.setImporte(rs.getDouble("importe"));
                pedido.setIva(rs.getDouble("iva"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return pedido;
    }

    @Override
    public int getUltimoIdPedido() {
        int idPedido = 0;
        Connection connection = null;
        String query = "SELECT LAST_INSERT_ID()";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            ResultSet rs = obtenerPedidos.executeQuery();
            while (rs.next()) {
                idPedido = rs.getInt("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return idPedido;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
