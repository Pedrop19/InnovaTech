/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package es.innovatech.DAO;

import es.innovatech.beans.Pedido;
import es.innovatech.enums.Estado;

import java.util.List;

/**
 * La interfaz IPedidosDAO define los métodos que deben ser implementados
 * por las clases que manejan la interacción con la base de datos para gestionar
 * los pedidos.
 * 
 * @author pedro
 */
public interface IPedidosDAO {
    
    /**
     * Registra un nuevo pedido en la base de datos.
     * 
     * @param pedido Objeto Pedido a registrar
     */
    public void registrarPedido(Pedido pedido);

    /**
     * Obtiene la lista de pedidos de un usuario con un estado específico.
     * 
     * @param idUsuario ID del usuario
     * @param estado Estado del pedido
     * @return Lista de objetos Pedido
     */
    public List<Pedido> listarPedidos(short idUsuario, Estado estado);

    /**
     * Obtiene el pedido de un usuario con un estado específico.
     * 
     * @param idUsuario ID del usuario
     * @param estado Estado del pedido
     * @return Objeto Pedido
     */
    public Pedido getPedidoPorEstado(short idUsuario, Estado estado);

    /**
     * Actualiza la información de un pedido en la base de datos.
     * 
     * @param pedido Objeto Pedido a actualizar
     */
    public void actualizarPedido(Pedido pedido);

    /**
     * Obtiene un pedido por su ID.
     * 
     * @param idPedido ID del pedido
     * @return Objeto Pedido
     */
    public Pedido getPedidoPorId(short idPedido);

    /**
     * Obtiene el último ID de pedido registrado.
     * 
     * @return Último ID de pedido
     */
    public short getUltimoIdPedido();

    /**
     * Elimina un pedido de la base de datos.
     * 
     * @param idPedido ID del pedido a eliminar
     */
    public void deletePedido(short idPedido);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
