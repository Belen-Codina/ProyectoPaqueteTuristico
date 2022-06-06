/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqturistico.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;
import paqturistico.modelo.Transporte;

/**
 *
 * @author daniel
 */
public class TransporteData {
    private Connection con;
    private Conexion conexion;

    public TransporteData(Conexion conexion) {
        try {
            this.conexion=conexion;
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    
    public void guardarTransporte(Transporte t) {
        String sql = "INSERT INTO transporte(tipo, precio, idDestino, activo) VALUES (?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, t.getTipo());
                ps.setInt(2, t.getPrecio());
                ps.setInt(3, t.getIdDestino().getIdDestino());
                ps.setBoolean(4, t.isActivo());
                
                

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (idCliente)
                if (rs.next()) {
                    System.out.println("Transporte tipo: " + t.getTipo() + ", cargado correctamente.");
                    
                    t.setIdTransporte(rs.getInt("tipo"));
                    System.out.println("id del transporte: " + t.getIdTransporte());
                    
                }
                
                ps.close();
                JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+t.getIdTransporte());
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar transporte\n" + ex);
        }
    }
    
    public void borrarTransporte(int id){
        String sql = "UPDATE transporte SET activo = 0 WHERE idTransporte = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    public List<Transporte> obtenerTransportes (){
        List<Transporte> trans = new ArrayList<>();
        Transporte t;
        Destino d = new Destino(); 
        String sql ="SELECT * FROM transporte WHERE transporte.activo=1;";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                t = new Transporte();
                d = buscarDestino(rs.getInt("idDestino"));
                t.setIdTransporte(rs.getInt("idTransporte"));
                t.setTipo(rs.getString("tipo"));
                t.setPrecio(rs.getInt("precio"));
                t.setIdDestino(d);
                t.setActivo(rs.getBoolean("activo"));             
               
                trans.add(t);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error obteniendo transportes" + ex);
        }
        return trans;
    }
    public List<Transporte> obtenerTransportesPorDestino(String destino){
        List<Transporte> trans = new ArrayList<>();
        Transporte t;
        Destino d;        
        String sql ="SELECT transporte.* FROM transporte, destino WHERE destino.nombre= ? AND transporte.idDestino = destino.idDestino AND destino.activo = 1 AND transporte.activo=1;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, destino);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                d = buscarDestino(rs.getInt("idDestino"));
                
                t = new Transporte();
                
                t.setIdTransporte(rs.getInt("idTransporte"));
                t.setTipo(rs.getString("tipo"));
                t.setPrecio(rs.getInt("precio"));
                t.setIdDestino(d);
                t.setActivo(rs.getBoolean("activo"));             
               
                trans.add(t);
               
            }
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error obteniendo transportes" + sqlE);
        }  
        return trans;
    }
    
    public List<Transporte> obtenerTodosTransportesPorDestino(String destino){
        List<Transporte> trans = new ArrayList<>();
        Transporte t;
        Destino d;        
        String sql ="SELECT transporte.* FROM transporte, destino WHERE destino.nombre= ? AND transporte.idDestino = destino.idDestino AND destino.activo = 1;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, destino);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                d = buscarDestino(rs.getInt("idDestino"));
                
                t = new Transporte();
                
                t.setIdTransporte(rs.getInt("idTransporte"));
                t.setTipo(rs.getString("tipo"));
                t.setPrecio(rs.getInt("precio"));
                t.setIdDestino(d);
                t.setActivo(rs.getBoolean("activo"));             
               
                trans.add(t);
               
            }
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error obteniendo transportes" + sqlE);
        }  
        return trans;
    }
    
    public void actualizarTransporte(Transporte t) {
        String sql = "UPDATE transporte SET precio = ?, activo = ? WHERE idTransporte = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getPrecio());
            ps.setBoolean(2, t.isActivo());
            
            ps.setInt(3, t.getIdTransporte());
            

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Transporte actualizado exitosamente.");
        }catch(SQLException sqlE){
            System.out.println("error al actualizar + sqlE");
        }
    }
     public Destino buscarDestino (int id) {      
        DestinoData dd = new DestinoData(conexion);         
        return dd.buscarDestino(id);

    }
       public Transporte obtenerTransporte(String tipo){
        Destino d;
       Transporte t=new Transporte();
        String sql = "SELECT * FROM transporte WHERE tipo = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
               d = buscarDestino(rs.getInt("idDestino"));
                t.setIdTransporte(rs.getInt("idTransporte"));
                t.setTipo(rs.getString("tipo"));
                t.setPrecio(rs.getInt("precio"));
                t.setIdDestino(d);
                t.setActivo(rs.getBoolean("activo"));
                
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar transporte " + sqlE);
        }
        
        return t;
    }
       
    public Transporte buscarTransporte(int id){
        Destino d;
       Transporte t=new Transporte();
        String sql = "SELECT * FROM transporte WHERE idTransporte = ? and activo=true";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
               d = buscarDestino(rs.getInt("idDestino"));
                t.setIdTransporte(rs.getInt("idTransporte"));
                t.setTipo(rs.getString("tipo"));
                t.setPrecio(rs.getInt("precio"));
                t.setIdDestino(d);
                t.setActivo(rs.getBoolean("activo"));
                
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar transporte " + sqlE);
        }
        
        return t;
    }
}
