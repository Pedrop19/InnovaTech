/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;

/**
 * La clase Articulo representa un artículo en el sistema.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class Articulo implements Serializable {
    private short id;
    private Categoria categoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private String marca;
    private double iva;
    private String imagen;

    /**
     * Constructor predeterminado de la clase Articulo.
     */
    public Articulo() {}

    /**
     * Obtiene el identificador del artículo.
     * 
     * @return El identificador del artículo.
     */
    public short getId() {
        return id;
    }

    /**
     * Establece el identificador del artículo.
     * 
     * @param id El nuevo identificador del artículo.
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     * Obtiene la categoría a la que pertenece el artículo.
     * 
     * @return La categoría del artículo.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría a la que pertenece el artículo.
     * 
     * @param categoria La nueva categoría del artículo.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el nombre del artículo.
     * 
     * @return El nombre del artículo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del artículo.
     * 
     * @param nombre El nuevo nombre del artículo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del artículo.
     * 
     * @return La descripción del artículo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del artículo.
     * 
     * @param descripcion La nueva descripción del artículo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del artículo.
     * 
     * @return El precio del artículo.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del artículo.
     * 
     * @param precio El nuevo precio del artículo.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la marca del artículo.
     * 
     * @return La marca del artículo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del artículo.
     * 
     * @param marca La nueva marca del artículo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el porcentaje de IVA aplicado al artículo.
     * 
     * @return El porcentaje de IVA del artículo.
     */
    public double getIva() {
        return iva;
    }

    /**
     * Establece el porcentaje de IVA aplicado al artículo.
     * 
     * @param iva El nuevo porcentaje de IVA del artículo.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Establece la ruta de la imagen asociada al artículo.
     * 
     * @param imagen La ruta de la imagen del artículo.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la ruta de la imagen asociada al artículo.
     * 
     * @return La ruta de la imagen del artículo.
     */
    public String getImagen() {
        return imagen;
    }
}