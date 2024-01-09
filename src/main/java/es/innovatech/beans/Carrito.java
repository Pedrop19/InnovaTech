/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.innovatech.beans;

import java.io.Serializable;
/**
 *
 * @author pedro
 */
public class Carrito implements Serializable {
    private Articulo articulo;
    private int cantidad;

    public Carrito(){}

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo Articulo) {
        this.articulo = Articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
