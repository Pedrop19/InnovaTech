/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package es.innovatech.DAO;

import java.util.List;

import es.innovatech.beans.LineaPedido;

/**
 * La interfaz ILineasPedidosDAO define los métodos que deben ser implementados
 * por las clases que manejan la interacción con la base de datos para gestionar
 * las líneas de pedidos.
 * 
 * @author pedro
 */
public interface ILineasPedidosDAO {

    /**
     * Registra una nueva línea de pedido en la base de datos.
     * 
     * @param lineaPedido Objeto LineaPedido a registrar
     */
    public void registrarLineaPedido(LineaPedido lineaPedido);

    /**
     * Obtiene la lista de líneas de pedido asociadas a un pedido.
     * 
     * @param idPedido ID del pedido
     * @return Lista de objetos LineaPedido
     */
    public List<LineaPedido> getLineasPedido(short idPedido);

    /**
     * Elimina todas las líneas de pedido asociadas a un pedido.
     * 
     * @param idPedido ID del pedido
     */
    public void deleteLineasPedido(short idPedido);

    /**
     * Elimina una línea de pedido específica.
     * 
     * @param lineaPedido Objeto LineaPedido a eliminar
     */
    public void deleteLineaPedido(LineaPedido lineaPedido);

    /**
     * Actualiza la información de una línea de pedido en la base de datos.
     * 
     * @param lineaPedido Objeto LineaPedido a actualizar
     */
    public void actualizarLineaPedido(LineaPedido lineaPedido);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
