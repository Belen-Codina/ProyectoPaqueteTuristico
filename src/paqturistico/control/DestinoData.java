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

/**
 *
 * @author daniel
 */
public class DestinoData {
    private Connection con;

    public DestinoData(Conexion conexion) {
        try {
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }

    DestinoData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void guardarDestino(Destino dest) {
        String sql = "INSERT INTO destino(nombre, pais, activo) VALUES (?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, dest.getNombre());
                ps.setString(2, dest.getPais());
                ps.setBoolean(3, dest.isActivo());
                

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID 
                if (rs.next()) {
                    System.out.println("Destino " + dest.getNombre() + ", cargado correctamente.");
                    
                    dest.setIdDestino(rs.getInt("idDestino"));
                    System.out.println("id del destino: " + dest.getIdDestino());
                    
                }
                
                ps.close();
                
                JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+dest.getIdDestino());
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar destino\n" + ex);
        }
    }
    
    //BORRADO LOGICO, habría que agregar un atributo activo
    public void borrarDestino(int id){
        String sql = "UPDATE destino SET activo = 0 WHERE idDestino = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    
    //borradoFisico
    public void eliminarDestino(int id){
        String sql = "DELETE FROM destino WHERE idDestino = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.execute();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    
    
    
    public Destino obtenerDestino(String nombre){
        
        Destino destino = new Destino();
        String sql = "SELECT * FROM destino WHERE nombre = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                destino.setIdDestino(rs.getInt("idDestino"));
                destino.setNombre(rs.getString("nombre"));
                destino.setPais(rs.getString("pais"));
                destino.setActivo(rs.getBoolean("activo"));
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar destino " + sqlE);
        }
        
        return destino;
    }
    
    public List<Destino> obtenerDestinos(){
        
        List<Destino> destinos = new ArrayList<>();
        Destino destino;
        String sql = "SELECT * FROM destino;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                destino = new Destino();
                
                destino.setIdDestino(rs.getInt("idDestino"));
                destino.setNombre(rs.getString("nombre"));
                destino.setPais(rs.getString("pais"));
                destino.setActivo(rs.getBoolean("activo"));
                
                destinos.add(destino);
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al obtener destinos " + sqlE);
        }
        
        return destinos;
    }
    
    public List<Destino> obtenerDestinosActivos(){
        
        List<Destino> destinos = new ArrayList<>();
        Destino destino;
        String sql = "SELECT * FROM destino WHERE activo = 1;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                destino = new Destino();
                
                destino.setIdDestino(rs.getInt("idDestino"));
                destino.setNombre(rs.getString("nombre"));
                destino.setPais(rs.getString("pais"));
                destino.setActivo(true);
                
                destinos.add(destino);
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al obtener destinos " + sqlE);
        }
        
        return destinos;
    }
    
      public Destino buscarDestino(int id) {
       
            Destino d = null;
            
            String sql = "SELECT * FROM destino WHERE idDestino = ?";
            PreparedStatement ps;
         try{   
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            //ps.execute();

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Destino();
                d.setIdDestino(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setPais(rs.getString(3));
                d.setActivo(rs.getBoolean(4));
                
            }
             ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DestinoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
      }
      
      public void actualizarDestino(Destino destino){
          
          try{
              
              String sql = "UPDATE destino SET nombre = ?, pais = ?, activo = ? WHERE idDestino = ?; ";
              PreparedStatement ps = con.prepareStatement(sql);
              
              ps.setString(1, destino.getNombre());
              ps.setString(2, destino.getPais());
              ps.setBoolean(3, destino.isActivo());
              ps.setInt(4, destino.getIdDestino());
              
              ps.executeUpdate();
              
              JOptionPane.showMessageDialog(null, "Actualizado con exito, id: "+destino.getIdDestino());
              
          }catch(SQLException error){
              System.out.println("error al actualizar destino \n"+error);
          }
      }
}

