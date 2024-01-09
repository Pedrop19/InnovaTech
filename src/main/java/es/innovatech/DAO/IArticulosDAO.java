/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.innovatech.DAO;
import es.innovatech.beans.Articulo;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface IArticulosDAO {
    public List<Articulo> getArticulos();

    public Articulo getArticulo(int id);

    public List<Articulo> getArticulosPorCategoria(int idCategoria);

    public void closeConnection();
}
