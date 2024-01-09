/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.innovatech.DAO;

import es.innovatech.beans.Pedido;
import es.innovatech.enums.Estado;

import java.util.List;

/**
 *
 * @author pedro
 */
public interface IPedidosDAO {
    
    public void registrarPedido(Pedido pedido);

    public List<Pedido> listarPedidos(int idUsuario);

    public Pedido getPedidoPorEstado (int idUsuario, Estado estado);

    public void actualizarPedido(Pedido pedido);

    public Pedido getPedidoPorId (int idPedido);

    public int getUltimoIdPedido();

    public void closeConnection();
}
