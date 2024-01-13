/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;

/**
 * La clase Categoria representa una categoría de artículos en el sistema.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class Categoria implements Serializable {
    private byte id;
    private String nombre;
    private String imagen;

    /**
     * Constructor predeterminado de la clase Categoria.
     */
    public Categoria() {}

    /**
     * Obtiene el identificador de la categoría.
     * 
     * @return El identificador de la categoría.
     */
    public byte getId() {
        return id;
    }

    /**
     * Establece el identificador de la categoría.
     * 
     * @param id El nuevo identificador de la categoría.
     */
    public void setId(byte id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * 
     * @return El nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * 
     * @param nombre El nuevo nombre de la categoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ruta de la imagen asociada a la categoría.
     * 
     * @return La ruta de la imagen de la categoría.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la ruta de la imagen asociada a la categoría.
     * 
     * @param imagen La nueva ruta de la imagen de la categoría.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
