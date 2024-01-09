/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.innovatech.DAO;

import java.util.List;

import es.innovatech.beans.LineaPedido;

/**
 *
 * @author pedro
 */
public interface ILineasPedidosDAO {
    public void registrarLineaPedido(int idPedido, int idProducto, int cantidad);

    public List<LineaPedido> getLineasPedido(int idPedido);

    public void closeConnection();
}
