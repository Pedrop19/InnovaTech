/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.innovatech.DAO;
import es.innovatech.beans.Usuario;

/**
 *
 * @author pedro
 */
public interface IUsuariosDAO {
    public void add(Usuario usuario);

    public void update(Usuario usuario);
    
    public Usuario getUsuariobyEmail(String email);

    public Usuario getUsuario(String password, String email);

    public void delete(String email);

    public Usuario getUsuarioPorId(int idUsuario);

    public int getLastIdUsuario();

    public void closeConnection();
}
