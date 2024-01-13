/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;

/**
 * La clase LineaPedido representa una línea de un pedido que incluye un artículo y la cantidad solicitada.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class LineaPedido implements Serializable {
    private Pedido pedido;
    private Articulo articulo;
    private int cantidad;

    /**
     * Constructor predeterminado de la clase LineaPedido.
     */
    public LineaPedido() {
    }

    /**
     * Obtiene el pedido al que pertenece la línea.
     * 
     * @return El pedido al que pertenece la línea.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido al que pertenece la línea.
     * 
     * @param pedido El nuevo pedido al que pertenece la línea.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene el artículo asociado a la línea de pedido.
     * 
     * @return El artículo asociado a la línea de pedido.
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * Establece el artículo asociado a la línea de pedido.
     * 
     * @param articulo El nuevo artículo asociado a la línea de pedido.
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Obtiene la cantidad de unidades del artículo en la línea de pedido.
     * 
     * @return La cantidad de unidades del artículo en la línea de pedido.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades del artículo en la línea de pedido.
     * 
     * @param cantidad La nueva cantidad de unidades del artículo en la línea de pedido.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
