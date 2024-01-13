/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package es.innovatech.DAO;
import es.innovatech.beans.Usuario;

/**
 * La interfaz IUsuariosDAO define los métodos que deben ser implementados
 * por las clases que manejan la interacción con la base de datos para gestionar
 * los usuarios.
 * 
 * @author pedro
 */
public interface IUsuariosDAO {
    
    /**
     * Agrega un nuevo usuario a la base de datos.
     * 
     * @param usuario Objeto Usuario a agregar
     */
    public void add(Usuario usuario);

    /**
     * Actualiza la información de un usuario en la base de datos.
     * 
     * @param usuario Objeto Usuario a actualizar
     */
    public void update(Usuario usuario);
    
    /**
     * Obtiene un usuario por su dirección de correo electrónico.
     * 
     * @param email Dirección de correo electrónico del usuario
     * @return Objeto Usuario
     */
    public Usuario getUsuariobyEmail(String email);

    /**
     * Obtiene un usuario por su contraseña y dirección de correo electrónico.
     * 
     * @param password Contraseña del usuario
     * @param email Dirección de correo electrónico del usuario
     * @return Objeto Usuario
     */
    public Usuario getUsuario(String password, String email);

    /**
     * Elimina un usuario de la base de datos por su dirección de correo electrónico.
     * 
     * @param email Dirección de correo electrónico del usuario a eliminar
     */
    public void delete(String email);

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param idUsuario ID del usuario
     * @return Objeto Usuario
     */
    public Usuario getUsuarioPorId(short idUsuario);

    /**
     * Obtiene el último ID de usuario registrado.
     * 
     * @return Último ID de usuario
     */
    public short getLastIdUsuario();

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
