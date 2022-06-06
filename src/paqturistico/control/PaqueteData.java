/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Cliente;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;
import paqturistico.modelo.Menu;
import paqturistico.modelo.Paquete;
import paqturistico.modelo.Transporte;

/**
 *
 * @author daniel
 */
public class PaqueteData {
    private Connection con;
    private Conexion conexion;

    public PaqueteData(Conexion conexion) {
        try {
            this.conexion=conexion;
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    
    public void guardarPaquete(Paquete paq) {
        String sql = "INSERT INTO paquete(idCliente, idAlojamiento, idMenu, fechaDesde, fechaHasta, idTransporte, precioFinal, cantidadPersonas, activo) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setInt(1, paq.getIdCliente().getIdCliente());
                ps.setInt(2, paq.getIdAlojamiento().getIdAlojamiento());
                ps.setInt(3, paq.getIdMenu().getIdMenu());
                ps.setDate(4, Date.valueOf(paq.getFechaDesde()));
                ps.setDate(5, Date.valueOf(paq.getFechaHasta()));
                ps.setInt(6, paq.getIdTransporte().getIdTransporte());
                ps.setInt(7, paq.getPrecioFinal());
                ps.setInt(8, paq.getCantidadPersonas());
                ps.setBoolean(9, paq.isActivo());
                

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (idCliente)
                if (rs.next()) {
                    System.out.println("Paquete cargado correctamente.");
                    
                    paq.setIdPaquete(rs.getInt("idPaquete"));
                    System.out.println("id del paquete: " + paq.getIdPaquete());
                    
                }
                
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar paquete\n" + ex);
        }
    }
    
    public void borrarPaquete(int id){
        String sql = "UPDATE paquete SET activo = 0 WHERE idPaquete = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    
    public List<Paquete> obtenerPaquetes(){
        
        List<Paquete> paquetes = new ArrayList<>();
        Paquete paquete;
        
        Cliente cliente=new Cliente();
        Alojamiento alojamiento=new Alojamiento();
        Menu menu = new Menu();
        Transporte trans = new Transporte();
        
        String sql = "SELECT * FROM paquete WHERE activo = 1;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                paquete = new Paquete();
                cliente = buscarCliente(rs.getInt("idCliente"));
                alojamiento = buscarAlojamiento(rs.getInt("idAlojamiento"));
                menu = buscarMenu(rs.getInt("idMenu"));
                trans = buscarTransporte(rs.getInt("idTransporte"));
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setIdCliente(cliente);
                paquete.setIdAlojamiento(alojamiento);
                paquete.setIdMenu(menu);
                paquete.setFechaDesde(rs.getDate("fechaDesde").toLocalDate());
                paquete.setFechaHasta(rs.getDate("fechaHasta").toLocalDate());
                paquete.setIdTransporte(trans);
                paquete.setPrecioFinal(rs.getInt("precioFinal"));
                paquete.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                paquete.setActivo(true);
                
                
                
                
                paquetes.add(paquete);
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar paquete" + sqlE);
        }
        
        return paquetes;
    }
    public List<Paquete> obtenerPaquetesPorCliente(Cliente c){
        
        List<Paquete> paquetes = new ArrayList<>();
        Paquete paquete;
        
        Cliente cliente=new Cliente();
        Alojamiento alojamiento=new Alojamiento();
        Menu menu = new Menu();
        Transporte trans = new Transporte();
        
        String sql = "SELECT * FROM paquete WHERE activo = 1 AND idCliente= ?;";
        //SELECT alojamiento.* FROM alojamiento, destino WHERE destino.nombre = ? AND destino.idDestino = alojamiento.idDestino AND alojamiento.activo = 1;
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            
            ps.setInt(1, c.getIdCliente());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                paquete = new Paquete();
                cliente = buscarCliente(rs.getInt("idCliente"));
                alojamiento = buscarAlojamiento(rs.getInt("idAlojamiento"));
                menu = buscarMenu(rs.getInt("idMenu"));
                trans = buscarTransporte(rs.getInt("idTransporte"));
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setIdCliente(cliente);
                paquete.setIdAlojamiento(alojamiento);
                paquete.setIdMenu(menu);
                paquete.setFechaDesde(rs.getDate("fechaDesde").toLocalDate());
                paquete.setFechaHasta(rs.getDate("fechaHasta").toLocalDate());
                paquete.setIdTransporte(trans);
                paquete.setPrecioFinal(rs.getInt("precioFinal"));
                paquete.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                paquete.setActivo(true);
                
                
                
                
                paquetes.add(paquete);
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar paquete" + sqlE);
        }
        
        return paquetes;
    }
    
    public Alojamiento buscarAlojamiento(int id) {        
        AlojamientoData dd = new AlojamientoData(conexion);
        return dd.buscarAlojamiento(id);   

    }
    public Destino buscarDestino(int id) {        
        DestinoData dd = new DestinoData(conexion);
        return dd.buscarDestino(id);   

    }
    public Cliente buscarCliente(int id) {        
        ClienteData dd = new ClienteData(conexion);
        return dd.buscarCliente(id);   

    }
    public Menu buscarMenu(int id) {        
        MenuData dd = new MenuData(conexion);
        return dd.buscarMenu(id);   

    }
    public Transporte buscarTransporte(int id) {        
        TransporteData dd = new TransporteData(conexion);
        return dd.buscarTransporte(id);   

    }

}
