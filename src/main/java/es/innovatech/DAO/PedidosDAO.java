/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import es.innovatech.DAOFactory.DAOFactory;
import es.innovatech.beans.Pedido;
import es.innovatech.beans.Usuario;
import es.innovatech.enums.Estado;

/**
 * Clase que implementa la interfaz IPedidosDAO para gestionar los pedidos en la base de datos.
 * 
 * @author pedro
 */
public class PedidosDAO implements IPedidosDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarPedido(Pedido pedido) {
        Connection connection = null;
        String query = "INSERT INTO pedidos (idUsuario, fecha, estado, importe, iva) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, pedido.getUsuario().getId());
            obtenerPedidos.setDate(2, Date.valueOf(LocalDate.now()));
            obtenerPedidos.setString(3, pedido.getEstado().toString());
            obtenerPedidos.setDouble(4, pedido.getImporte());
            obtenerPedidos.setDouble(5, pedido.getIva());
            obtenerPedidos.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    System.out.println("Error al registrar el pedido: " + e.getMessage());
                    connection.rollback();
                } catch (SQLException rollbackException) {
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
    public List<Pedido> listarPedidos(short idUsuario, Estado estado) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Connection conexion = null;
        String query = "SELECT * FROM pedidos WHERE idUsuario = ? AND estado = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = conexion.prepareStatement(query);
            obtenerPedidos.setInt(1, idUsuario);
            obtenerPedidos.setString(2, estado.toString());
            try (ResultSet rs = obtenerPedidos.executeQuery()) {
                DAOFactory daof = DAOFactory.getDAOFactory();
                IUsuariosDAO usuariosDAO = daof.getIUsuarioDAO();
                Usuario usuario = usuariosDAO.getUsuarioPorId(idUsuario);
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setIdPedido((short) rs.getInt("idPedido"));
                    pedido.setUsuario(usuario);
                    if(rs.getDate("fecha") != null){
                        pedido.setFecha(rs.getDate("fecha"));
                    }
                    pedido.setEstado(Estado.valueOf(rs.getString("estado")));
                    pedido.setImporte(rs.getDouble("importe"));
                    pedido.setIva(rs.getDouble("iva"));
                    pedidos.add(pedido);
                }
            }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Pedido getPedidoPorEstado(short idUsuario, Estado estado) {
        Pedido pedido = null;
        Connection conexion = null;
    
        try {
            DAOFactory daof = DAOFactory.getDAOFactory();
            IUsuariosDAO usuariosDAO = daof.getIUsuarioDAO();
            Usuario usuario = usuariosDAO.getUsuarioPorId(idUsuario);
    
            String query = "SELECT idPedido, fecha, estado, importe, iva FROM pedidos WHERE idUsuario = ? AND estado = ? LIMIT 1";
    
            conexion = ConnectionFactory.getConnection();
            try (PreparedStatement obtenerPedidos = conexion.prepareStatement(query)) {
                obtenerPedidos.setInt(1, idUsuario);
                obtenerPedidos.setString(2, estado.toString());
    
                try (ResultSet rs = obtenerPedidos.executeQuery()) {
                    if (rs.next()) {
                        pedido = new Pedido();
                        pedido.setIdPedido((short) rs.getInt("idPedido"));
                        pedido.setUsuario(usuario);
                        if(rs.getDate("fecha") != null){
                            pedido.setFecha(rs.getDate("fecha"));
                        }
                        pedido.setEstado(Estado.valueOf(rs.getString("estado")));
                        pedido.setImporte(rs.getDouble("importe"));
                        pedido.setIva(rs.getDouble("iva"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
    
        return pedido;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarPedido(Pedido pedido) {
        Connection connection = null;
        
        String query = "UPDATE pedidos SET importe = ?, estado = ? WHERE idPedido = ?;";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setDouble(1, pedido.getImporte());
            obtenerPedidos.setString(2, pedido.getEstado().toString());
            obtenerPedidos.setInt(3, pedido.getIdPedido());
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Pedido getPedidoPorId(short idPedido) {
        Pedido pedido = new Pedido();
        Connection connection = null;
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO usuariosDAO = daof.getIUsuarioDAO();
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM pedidos WHERE idPedido = ?";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            ResultSet rs = obtenerPedidos.executeQuery();
            while (rs.next()) {
                usuario = usuariosDAO.getUsuarioPorId((short) rs.getInt("idUsuario"));
                pedido.setIdPedido((short) rs.getInt("idPedido"));
                pedido.setUsuario(usuario);
                if(rs.getDate("fecha") != null){
                    pedido.setFecha(rs.getDate("fecha"));
                }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public short getUltimoIdPedido() {
        short idPedido = 0;
        Connection connection = null;
        String query = "SELECT LAST_INSERT_ID()";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            ResultSet rs = obtenerPedidos.executeQuery();
            while (rs.next()) {
                idPedido = (short) rs.getInt("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return idPedido;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePedido(short idPedido) {
        Connection connection = null;
        String query = "DELETE FROM pedidos WHERE idPedido = ?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement obtenerPedidos = connection.prepareStatement(query);
            obtenerPedidos.setInt(1, idPedido);
            obtenerPedidos.executeUpdate();
            connection.commit();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
