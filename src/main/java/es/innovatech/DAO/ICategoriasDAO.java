/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package es.innovatech.DAO;

import es.innovatech.beans.Categoria;
import java.util.List;

/**
 * La interfaz ICategoriasDAO define los métodos que deben ser implementados
 * por las clases que manejan la interacción con la base de datos para obtener información relacionada con las categorías.
 * 
 * @author pedro
 */
public interface ICategoriasDAO {

    /**
     * Obtiene la lista de categorías.
     * 
     * @return Lista de objetos Categoria
     */
    public List<Categoria> getCategorias();

    /**
     * Obtiene una categoría por su ID.
     * 
     * @param id ID de la categoría
     * @return Objeto Categoria
     */
    public Categoria getCategoria(byte id);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
