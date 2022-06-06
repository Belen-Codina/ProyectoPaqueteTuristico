/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import paqturistico.control.AlojamientoData;
import paqturistico.control.ClienteData;
import paqturistico.control.DestinoData;
import paqturistico.control.MenuData;
import paqturistico.control.PaqueteData;
import paqturistico.control.TransporteData;
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;
import paqturistico.modelo.Menu;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            Conexion con = new Conexion();            
            ClienteData cd = new ClienteData(con);
            AlojamientoData ad = new AlojamientoData(con);
            DestinoData dd = new DestinoData(con);
            MenuData md = new MenuData(con);
            TransporteData td = new TransporteData(con);
            PaqueteData pd = new PaqueteData(con);
            
            /*ad.borrarAlojamiento(1);
            cd.borrarCliente(1);
            dd.eliminarDestino(1);
            md.borrarMenu(1);
            pd.borrarPaquete(1);
            td.borrarTransporte(1);*/
            
            //Alojamiento alojamiento = ad.obtenerAlojamiento("Ausonia");
            
            //System.out.println(alojamiento.toString());
            
            //alojamiento.setNombre("Masedonia");
            
            //ad.actualizarAlojamiento(alojamiento);
            
            //System.out.println(alojamiento.toString());
            
            //List<Alojamiento> alojamientos = ad.obtenerAlojPorDestino("mendoza");
            //System.out.println(alojamientos.toString());
            
            //Destino mendoza = dd.obtenerDestino("mendoza");
            //System.out.println(mendoza.toString());
            
            List<Alojamiento> alojamientos = ad.obtenerAlojPorDestino("San Francisco, San Luis");
            
            //System.out.println(alojamientos);
            
            List<Menu> menus = md.obtenerMenuPorAlojamiento("Prueba 1");
            
            //System.out.println(menus);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
