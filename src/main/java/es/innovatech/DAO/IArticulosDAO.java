/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package es.innovatech.DAO;

import es.innovatech.beans.Articulo;
import java.util.List;

/**
 * La interfaz IArticulosDAO define los métodos que deben ser implementados
 * por las clases que manejan la interacción con la base de datos para obtener información relacionada con los artículos.
 * 
 * @author pedro
 */
public interface IArticulosDAO {

    /**
     * Obtiene la lista de artículos ordenados alfabéticamente por nombre.
     * 
     * @return Lista de objetos Articulo
     */
    public List<Articulo> getArticulosOrdenadosAlfabeticamente();

    /**
     * Obtiene un artículo por su ID.
     * 
     * @param id ID del artículo
     * @return Objeto Articulo
     */
    public Articulo getArticulo(short id);

    /**
     * Obtiene la lista de artículos pertenecientes a una categoría.
     * 
     * @param idCategoria ID de la categoría
     * @return Lista de objetos Articulo
     */
    public List<Articulo> getArticulosPorCategoria(byte idCategoria);

    /**
     * Obtiene la lista de marcas de los artículos.
     * 
     * @return Lista de cadenas de texto con las marcas
     */
    public List<String> getMarcas();

    /**
     * Obtiene la lista de artículos de una marca específica.
     * 
     * @param marca Nombre de la marca
     * @return Lista de objetos Articulo
     */
    public List<Articulo> getArticulosPorMarca(String marca);

    /**
     * Obtiene la lista de artículos con un precio menor o igual al especificado.
     * 
     * @param precio Precio máximo
     * @return Lista de objetos Articulo
     */
    public List<Articulo> getArticulosPrecioMenorA(int precio);

    /**
     * Obtiene el precio más alto entre todos los artículos.
     * 
     * @return Precio más alto
     */
    public int getPrecioMasAlto();

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
