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
public class Alojamiento {
    private int idAlojamiento;
    private String nombre;
    private String tipo;
    private int precio; //fijarse si es necesario usar double para los centavos
    private Destino idDestino;
    private boolean activo;

    public Alojamiento(int idAlojamiento, String nombre, String tipo, int precio, Destino idDestino, boolean activo) {
        this.idAlojamiento = idAlojamiento;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.idDestino = idDestino;
        this.activo = activo;
    }

    public Alojamiento(String nombre, String tipo, int precio, Destino idDestino, boolean activo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.idDestino = idDestino;
        this.activo = activo;
    }

    public Alojamiento() {
    }

    
    
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Alojamiento{" + "idAlojamiento=" + idAlojamiento + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", idDestino=" + idDestino + ", activo=" + activo + '}';
    }
    
    
}
