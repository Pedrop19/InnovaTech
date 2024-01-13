/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package es.innovatech.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.innovatech.enums.Estado;

/**
 * La clase Pedido representa una orden de compra realizada por un usuario en el sistema.
 * 
 * Esta clase implementa la interfaz Serializable para permitir la serialización.
 * 
 * @author pedro
 */
public class Pedido implements Serializable {
    private short idPedido;
    private Usuario usuario;
    private Date fecha;
    private Estado estado;
    private double importe;
    private double iva;
    private List<LineaPedido> lineasPedido;

    /**
     * Constructor predeterminado de la clase Pedido.
     */
    public Pedido() {
    }

    /**
     * Obtiene el identificador del pedido.
     * 
     * @return El identificador del pedido.
     */
    public short getIdPedido() {
        return idPedido;
    }

    /**
     * Establece el identificador del pedido.
     * 
     * @param idPedido El nuevo identificador del pedido.
     */
    public void setIdPedido(short idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Obtiene el usuario que realizó el pedido.
     * 
     * @return El usuario que realizó el pedido.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó el pedido.
     * 
     * @param usuario El nuevo usuario que realizó el pedido.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la fecha en que se realizó el pedido.
     * 
     * @return La fecha en que se realizó el pedido.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se realizó el pedido.
     * 
     * @param fecha La nueva fecha en que se realizó el pedido.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado actual del pedido.
     * 
     * @return El estado actual del pedido.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del pedido.
     * 
     * @param estado El nuevo estado actual del pedido.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el importe total del pedido.
     * 
     * @return El importe total del pedido.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece el importe total del pedido.
     * 
     * @param importe El nuevo importe total del pedido.
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Obtiene el porcentaje de IVA aplicado al pedido.
     * 
     * @return El porcentaje de IVA del pedido.
     */
    public double getIva() {
        return iva;
    }

    /**
     * Establece el porcentaje de IVA aplicado al pedido.
     * 
     * @param iva El nuevo porcentaje de IVA del pedido.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Obtiene la lista de líneas de pedido asociadas al pedido.
     * 
     * @return La lista de líneas de pedido asociadas al pedido.
     */
    public List<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    /**
     * Establece la lista de líneas de pedido asociadas al pedido.
     * 
     * @param lineasPedido La nueva lista de líneas de pedido asociadas al pedido.
     */
    public void setLineasPedido(List<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }
}
