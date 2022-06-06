/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import paqturistico.control.AlojamientoData;
import paqturistico.control.ClienteData;
import paqturistico.control.DestinoData;
import paqturistico.control.MenuData;
import paqturistico.control.PaqueteData;
import paqturistico.control.TransporteData;
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Cliente;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;
import paqturistico.modelo.Menu;
import paqturistico.modelo.Paquete;
import paqturistico.modelo.Transporte;

/**
 *
 * @author Usuario
 */
public class TestLucio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            Conexion con = new Conexion();            
            ClienteData cd = new ClienteData(con);
            AlojamientoData ad = new AlojamientoData(con);
            DestinoData dd = new DestinoData(con);
            MenuData md = new MenuData(con);
            TransporteData td = new TransporteData(con);
            PaqueteData pd = new PaqueteData(con);           
            /*Cliente c = new Cliente (12, "Lucio Martín Di Sanzo", 42994310, "luciodisanzo@gmail.com" , true);
            System.out.println(pd.obtenerPaquetesPorCliente(c));*/
            /*Destino d1 = new Destino(11, "Villa Carlos Paz", "Argentina", true);
            Transporte t1 = new Transporte(12, "Micro", 7000, d1, false);
            td.actualizarTransporte(t1);*/
            //System.out.println(pd.obtenerPaquetes());
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestLucio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
