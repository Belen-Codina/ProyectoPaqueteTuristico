/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author daniel
 */
public class Paquete {

    private int idPaquete;
    private Cliente idCliente;
    private Alojamiento idAlojamiento;
    private Menu idMenu;
    private Transporte idTransporte;
    LocalDate fechaDesde;
    LocalDate fechaHasta;
    private int precioFinal;
    private int cantidadPersonas;
    private boolean activo;

    public Paquete(int idPaquete, Cliente idCliente, Alojamiento idAlojamiento, Menu idMenu, Transporte idTransporte, LocalDate fechaDesde, LocalDate fechaHasta, int precioFinal, int cantidadPersonas, boolean activo) {
        this.idPaquete = idPaquete;
        this.idCliente = idCliente;
        this.idAlojamiento = idAlojamiento;
        this.idMenu = idMenu;
        this.idTransporte = idTransporte;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.precioFinal = precioFinal;
        this.cantidadPersonas = cantidadPersonas;
        this.activo = activo;
    }

    public Paquete(Cliente idCliente, Alojamiento idAlojamiento, Menu idMenu, Transporte idTransporte, LocalDate fechaDesde, LocalDate fechaHasta, int cantidadPersonas, boolean activo) {
        this.idCliente = idCliente;
        this.idAlojamiento = idAlojamiento;
        this.idMenu = idMenu;
        this.idTransporte = idTransporte;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.cantidadPersonas = cantidadPersonas;
        this.activo = activo;
    }

    public Paquete() {
    }

    public int calcularPrecioFinal() {
        int pFinal = 0;
        long totalDias= ChronoUnit.DAYS.between(fechaDesde, fechaHasta);
       
        pFinal += idAlojamiento.getPrecio();
        pFinal += idMenu.getPrecio();
        pFinal*=totalDias;
        pFinal*=cantidadPersonas;
         pFinal += (idTransporte.getPrecio()*cantidadPersonas);
        if (fechaDesde.getMonthValue() == 1 || fechaDesde.getMonthValue() == 7) {
            //CASO TEMPORADA ALTA
            pFinal *= 1.3;
        }  else {if (fechaDesde.getMonthValue() == 2 || fechaDesde.getMonthValue() == 6) {
               
                //TEMPORADA MEDIA
                pFinal *= 1.15;
       } 
       
    }
         return pFinal;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Alojamiento getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(Alojamiento idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public Transporte getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Transporte idTransporte) {
        this.idTransporte = idTransporte;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getPrecioFinal() { // agrego el calculo de precio final
        if (precioFinal!=0){
        return precioFinal;
    } else {
            return calcularPrecioFinal();
        } 
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", idCliente=" + idCliente + ", idAlojamiento=" + idAlojamiento + ", idMenu=" + idMenu + ", idTransporte=" + idTransporte + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", precioFinal=" + precioFinal + ", cantidadPersonas=" + cantidadPersonas + ", activo=" + activo + '}';
    }

}
