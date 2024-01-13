/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * La clase Usuario representa un usuario en el sistema.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class Usuario implements Serializable {
    private short id;
    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private String NIF;
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String localidad;
    private String provincia;
    private Date ultimoAcceso;
    private String avatar;
    private boolean verificado;

    /**
     * Constructor predeterminado de la clase Usuario.
     */
    public Usuario() {}

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return El identificador del usuario.
     */
    public short getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     * 
     * @param id El nuevo identificador del usuario.
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     * 
     * @return La dirección de correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     * 
     * @param email La nueva dirección de correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return Los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos Los nuevos apellidos del usuario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el NIF (Número de Identificación Fiscal) del usuario.
     * 
     * @return El NIF del usuario.
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Establece el NIF (Número de Identificación Fiscal) del usuario.
     * 
     * @param nIF El nuevo NIF del usuario.
     */
    public void setNIF(String nIF) {
        NIF = nIF;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     * 
     * @return El número de teléfono del usuario.
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * 
     * @param telefono El nuevo número de teléfono del usuario.
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del usuario.
     * 
     * @return La dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del usuario.
     * 
     * @param direccion La nueva dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el código postal del usuario.
     * 
     * @return El código postal del usuario.
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal del usuario.
     * 
     * @param codigoPostal El nuevo código postal del usuario.
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Obtiene la localidad del usuario.
     * 
     * @return La localidad del usuario.
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Establece la localidad del usuario.
     * 
     * @param localidad La nueva localidad del usuario.
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Obtiene la provincia del usuario.
     * 
     * @return La provincia del usuario.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia del usuario.
     * 
     * @param provincia La nueva provincia del usuario.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene la fecha del último acceso del usuario.
     * 
     * @return La fecha del último acceso del usuario.
     */
    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    /**
     * Establece la fecha del último acceso del usuario.
     * 
     * @param ultimoAcceso La nueva fecha del último acceso del usuario.
     */
    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    /**
     * Obtiene la ruta del avatar del usuario.
     * 
     * @return La ruta del avatar del usuario.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Establece la ruta del avatar del usuario.
     * 
     * @param avatar La nueva ruta del avatar del usuario.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Obtiene el estado de verificación del usuario.
     * 
     * @return El estado de verificación del usuario.
     */
    public boolean isVerificado() {
        return verificado;
    }

    /**
     * Establece el estado de verificación del usuario.
     * 
     * @param verificado El nuevo estado de verificación del usuario.
     */
    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
