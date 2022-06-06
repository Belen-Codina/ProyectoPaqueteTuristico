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
public class Destino {
    private int idDestino;
    private String nombre;
    private String pais;
    private boolean activo;

    public Destino(int idDestino, String nombre, String pais, boolean activo) {
        this.idDestino = idDestino;
        this.nombre = nombre;
        this.pais = pais;
        this.activo=activo;
    }

    public Destino(String nombre, String pais, boolean activo) {
        this.nombre = nombre;
        this.pais = pais;
        this.activo= activo;
    }

    public Destino() {
    }

    public int getIdDestino() {
        return idDestino;
    }
  
    

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
     public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Destino{" + "idDestino=" + idDestino + ", nombre=" + nombre + ", pais=" + pais + '}';
    }
    
    
}
