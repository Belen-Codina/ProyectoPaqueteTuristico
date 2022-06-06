/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico.modelo;

import java.util.Objects;

/**
 *
 * @author daniel
 */
public class Menu {
    private int idMenu;
    private String tipo;
    private int precio;
    private Alojamiento idAlojamiento;
    private boolean activo;

    public Menu(int idMenu, String tipo, int precio, Alojamiento idAlojamiento, boolean activo) {
        this.idMenu = idMenu;
        this.tipo = tipo;
        this.precio = precio;
        this.idAlojamiento = idAlojamiento;
        this.activo = activo;
    }

    public Menu(String tipo, int precio, Alojamiento idAlojamiento, boolean activo) {
        this.tipo = tipo;
        this.precio = precio;
        this.idAlojamiento = idAlojamiento;
        this.activo = activo;
    }
    
    public Menu () {        
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
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

    public Alojamiento getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(Alojamiento idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", tipo=" + tipo + ", precio=" + precio + ", idAlojamiento=" + idAlojamiento + ", activo=" + activo + '}';
    }
    
    
}
