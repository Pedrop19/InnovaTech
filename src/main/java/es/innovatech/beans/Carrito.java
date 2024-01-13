/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;

/**
 * La clase Carrito representa un artículo y la cantidad seleccionada en el carrito de compras.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class Carrito implements Serializable {
    private Articulo articulo;
    private int cantidad;

    /**
     * Constructor predeterminado de la clase Carrito.
     */
    public Carrito() {}

    /**
     * Obtiene el artículo asociado al carrito.
     * 
     * @return El artículo asociado al carrito.
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * Establece el artículo asociado al carrito.
     * 
     * @param articulo El nuevo artículo asociado al carrito.
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Obtiene la cantidad de unidades del artículo en el carrito.
     * 
     * @return La cantidad de unidades del artículo en el carrito.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades del artículo en el carrito.
     * 
     * @param cantidad La nueva cantidad de unidades del artículo en el carrito.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
