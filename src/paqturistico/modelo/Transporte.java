/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico.modelo;

/**
 *
 * @author daniel
 */
public class Transporte {
    private int idTransporte;
    private String tipo;
    private int precio;
    private Destino idDestino;
    private boolean activo;

    public Transporte(int idTransporte, String tipo, int precio, Destino idDestino, boolean activo) {
        this.idTransporte = idTransporte;
        this.tipo = tipo;
        this.precio = precio;
        this.idDestino = idDestino;
        this.activo = activo;
    }

    public Transporte(String tipo, int precio, Destino idDestino, boolean activo) {
        this.tipo = tipo;
        this.precio = precio;
        this.idDestino = idDestino;
        this.activo = activo;
    }

    public Transporte() {
    }

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Destino getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Destino idDestino) {
        this.idDestino = idDestino;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Transporte{" + "idTransporte=" + idTransporte + ", tipo=" + tipo + ", precio=" + precio + ", idDestino=" + idDestino + ", activo=" + activo + '}';
    }
    
    
}
