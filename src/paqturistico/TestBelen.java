/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico;

import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import paqturistico.control.AlojamientoData;
import paqturistico.control.ClienteData;
import paqturistico.control.DestinoData;
import paqturistico.control.MenuData;
import paqturistico.control.PaqueteData;
import paqturistico.control.TransporteData;
import paqturistico.control.UsuarioData;
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Cliente;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;
import paqturistico.modelo.Menu;
import paqturistico.modelo.Paquete;
import paqturistico.modelo.Transporte;
import paqturistico.modelo.Usuario;

/**
 *
 * @author Belen
 */
public class TestBelen {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
     try {            
            Conexion c = new Conexion();
           
            UsuarioData ud= new UsuarioData(c);
            
            Usuario u1= new Usuario("jose@gmail", "123jose", "Alvarez", "Jose", "Recursos humanos");
            Usuario u2= new Usuario("marta@gmail", "486k", "Nuñez", "Marta", "Finanzas");
            Usuario u3= new Usuario("laura@gmail", "abc32", "Alvarez", "Laura", "Marketing");
            Usuario u4= new Usuario("raul@gmail", "147ab", "Alvarez", "Raul", "Marketing");
            
            ud.guardarUsuario(u1);
             ud.guardarUsuario(u2);
              ud.guardarUsuario(u3);
               ud.guardarUsuario(u4);
         System.out.println(ud.obtenerUsuariosPorApellido("Alvarez"));

            
            

            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestBelen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}
