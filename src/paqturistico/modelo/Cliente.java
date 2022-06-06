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
public class Cliente {
    private int idCliente;
    private String nombre;
    private int dni; //Probar de usar long por la cantidad de digitos
    private String mail;
    private boolean activo;

    public Cliente(int idCliente, String nombre, int dni, String mail, boolean activo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.activo = activo;
    }

    public Cliente(String nombre, int dni, String mail, boolean activo) {
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.activo = activo;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
