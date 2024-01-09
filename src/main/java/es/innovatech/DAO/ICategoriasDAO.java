/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.innovatech.DAO;

import es.innovatech.beans.Categoria;
import java.util.List;
/**
 *
 * @author pedro
 */
public interface ICategoriasDAO {
    
    public List<Categoria> getCategorias();

    public Categoria getCategoria(int id);

    public void closeConnection();
}
