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
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Destino;

/**
 *
 * @author daniel
 */
public class AlojamientoData {
    private Connection con;
    private Conexion conexion;

    public AlojamientoData(Conexion conexion) {
        try {
            this.conexion=conexion;
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    
    public void guardarAlojamiento(Alojamiento alojamiento) {
        String sql = "INSERT INTO alojamiento(nombre, tipo, precio, idDestino, activo) VALUES (?,?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, alojamiento.getNombre());
                ps.setString(2, alojamiento.getTipo());
                ps.setInt(3, alojamiento.getPrecio());
                ps.setInt(4,alojamiento.getIdDestino().getIdDestino());
                ps.setBoolean(5, alojamiento.isActivo());
                

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (idCliente)
                if (rs.next()) {
                    System.out.println("Alojamiento " + alojamiento.getNombre() + ", cargado correctamente.");
                    //cliente.setIdCliente(1);
                    alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                    System.out.println("id del alojamiento: " + alojamiento.getIdAlojamiento());
                    JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+alojamiento.getIdAlojamiento());
                }
                
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar alojamiento\n" + ex);
        }
    }
    
    public void borrarAlojamiento(int id){
        String sql = "UPDATE alojamiento SET activo = 0 WHERE idAlojamiento = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    
    public void actualizarAlojamiento(Alojamiento alojamiento){
        
        String sql = "UPDATE alojamiento SET nombre = ?, tipo = ?, precio = ?, activo = ? WHERE idAlojamiento = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getTipo());
            ps.setInt(3, alojamiento.getPrecio());
            ps.setBoolean(4, alojamiento.isActivo());
            ps.setInt(5, alojamiento.getIdAlojamiento());

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Alojamiento actualizado exitosamente.");
        }catch(SQLException sqlE){
            System.out.println("error al actualizar + sqlE");
        }
    }
    
    public Alojamiento obtenerAlojamiento(String nombre){
        
        Alojamiento alojamiento = new Alojamiento();        
        Destino destino;
        String sql = "SELECT * FROM alojamiento WHERE nombre=?;";
        PreparedStatement ps;
        
        try{
             ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            //ps.setInt(2, idDestino);
            
            ResultSet rs = ps.executeQuery();
            
            //if(rs.next())
              //  System.out.println(buscarDestino(rs.getInt(5)));
            
            if(rs.next()){                
                
                destino = buscarDestino(rs.getInt("idDestino"));
                
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setTipo(rs.getString("tipo"));
                alojamiento.setActivo(rs.getBoolean("activo"));
                alojamiento.setPrecio(rs.getInt("precio"));
                alojamiento.setIdDestino(destino);
                
                //alojamiento.getIdDestino().setIdDestino(rs.getInt("idDestino"));
                
            }else{
                System.out.println("No se pudo obtener el alojamiento pero no hubo exception");
            }
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error al obtener alojamiento" + sqlE);
        }
        
        return alojamiento;        
    }
    
    public List<Alojamiento> obtenerAlojamientos(){
        
        Alojamiento alojamiento;
        List<Alojamiento> alojamientos = new ArrayList<>();
        Destino destino;
        String sql = "SELECT * FROM alojamiento;";
        PreparedStatement ps;
        
        try{
             ps = con.prepareStatement(sql);
            
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while(rs.next()){                
                
                alojamiento = new Alojamiento();
                destino = buscarDestino(rs.getInt("idDestino"));
                
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setTipo(rs.getString("tipo"));
                alojamiento.setActivo(rs.getBoolean("activo"));
                alojamiento.setPrecio(rs.getInt("precio"));
                alojamiento.setIdDestino(destino);
                
                alojamientos.add(alojamiento);
                
                
            }
            
            rs.close();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error al obtener alojamiento" + sqlE);
        }
        
        return alojamientos;        
    }
    
    public List<Alojamiento> obtenerAlojamientosActivos(){
        
        Alojamiento alojamiento;
        List<Alojamiento> alojamientos = new ArrayList<>();
        Destino destino;
        String sql = "SELECT * FROM alojamiento WHERE activo=1;";
        PreparedStatement ps;
        
        try{
             ps = con.prepareStatement(sql);
            
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while(rs.next()){                
                
                alojamiento = new Alojamiento();
                destino = buscarDestino(rs.getInt("idDestino"));
                
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setTipo(rs.getString("tipo"));
                alojamiento.setActivo(rs.getBoolean("activo"));
                alojamiento.setPrecio(rs.getInt("precio"));
                alojamiento.setIdDestino(destino);
                
                alojamientos.add(alojamiento);
                
                
            }
            
            rs.close();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error al obtener alojamiento" + sqlE);
        }
        
        return alojamientos;        
    }
    
    public List<Alojamiento> obtenerAlojPorDestino(String destino){
        
        List<Alojamiento> alojamientos = new ArrayList<>();
        Alojamiento alojamiento;
        Destino dest;
        String sql ="SELECT alojamiento.* FROM alojamiento, destino WHERE destino.nombre = ? AND destino.idDestino = alojamiento.idDestino AND alojamiento.activo = 1;";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, destino);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                dest = buscarDestino(rs.getInt("idDestino"));
                
                alojamiento = new Alojamiento();
                
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setTipo(rs.getString("tipo"));
                alojamiento.setActivo(rs.getBoolean("activo"));
                alojamiento.setPrecio(rs.getInt("precio"));
                alojamiento.setIdDestino(dest);
                
                alojamientos.add(alojamiento);
            }
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("error listando por destino" + sqlE);
        }
        
        return alojamientos;
    }
    
    public Destino buscarDestino(int id) {        
        DestinoData dd = new DestinoData(conexion);
        return dd.buscarDestino(id);   

    }
     

      public Alojamiento buscarAlojamiento(int id) {
       
       
            Alojamiento a = null;
            Destino destino;
            String sql = "SELECT * FROM alojamiento WHERE idAlojamiento = ?";
            PreparedStatement ps;
           try {  
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alojamiento();
                destino = buscarDestino(rs.getInt("idDestino"));  
                a.setIdAlojamiento(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setTipo(rs.getString(3));
                a.setActivo(rs.getBoolean(6));
                a.setPrecio(rs.getInt(4));
                a.setIdDestino(destino);
                
            }
            ps.close();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoData.class.getName()).log(Level.SEVERE, null, ex);
        }
            return a;
      }
}
