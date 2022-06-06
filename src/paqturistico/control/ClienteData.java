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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import paqturistico.modelo.Cliente;
import paqturistico.modelo.Conexion;

/**
 *
 * @author daniel
 */
public class ClienteData {
    private Connection con;

    public ClienteData(Conexion conexion) {
        try {
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    public void guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(nombre, dni, mail, activo) VALUES (?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, cliente.getNombre());
                ps.setInt(2, cliente.getDni());
                ps.setString(3, cliente.getMail());
                ps.setBoolean(4, cliente.isActivo());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (idCliente)
                if (rs.next()) {
                    System.out.println("Cliente " + cliente.getNombre() + ", cargado correctamente.");
                    //cliente.setIdCliente(1);
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    System.out.println("id del cliente: " + cliente.getIdCliente());
                    
                }
                
                ps.close();
                
                JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+cliente.getIdCliente());
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar cliente\n" + ex);
        }
    }
    
    public void borrarCliente(int id){
        String sql = "UPDATE cliente SET activo = 0 WHERE idCliente = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al borrar\n"+sqlE);
        }
    }
    
    public void actualizarCliente(Cliente cliente){
        try {
            String sql = "UPDATE cliente SET nombre = ?, dni=?, mail=?, activo=? WHERE idCliente=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getDni());
            ps.setString(3, cliente.getMail());
            ps.setBoolean(4, cliente.isActivo());
            ps.setInt(5, cliente.getIdCliente());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID
            System.out.println("Cliente " + cliente.getNombre() + ", actualizado correctamente.");

            ps.close();
            
            JOptionPane.showMessageDialog(null, "Actualizado con exito, id: "+cliente.getIdCliente());
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el cliente"+ex);
        }
    }
    
    public Cliente buscarCliente(int idCliente){
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE idCliente= ?";
        try {
            
            PreparedStatement ps;
            ps=con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDni(rs.getInt(3));
                c.setMail(rs.getString(4));
                c.setActivo(rs.getBoolean(5));                
                

            }
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar Cliente"+ ex);
        }
        return c;
    }
    
    public Cliente buscarClienteActivoPorNombre(String nombre){
        
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE cliente.nombre = ? and activo = true";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
              cliente.setActivo(rs.getBoolean("activo"));
              cliente.setDni(rs.getInt("dni"));
              cliente.setIdCliente(rs.getInt("idCliente"));
              cliente.setMail(rs.getString("mail"));
              cliente.setNombre(rs.getString("nombre"));
            }
            
        }catch(SQLException sqlE){
            System.out.println("No se pudo buscar el cliente\n"+sqlE);
        }
        
        return cliente;
    }
    
    public Cliente buscarClientePorNombre(String nombre){
        
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE cliente.nombre = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
              cliente.setActivo(rs.getBoolean("activo"));
              cliente.setDni(rs.getInt("dni"));
              cliente.setIdCliente(rs.getInt("idCliente"));
              cliente.setMail(rs.getString("mail"));
              cliente.setNombre(rs.getString("nombre"));
            }
            
        }catch(SQLException sqlE){
            System.out.println("No se pudo buscar el cliente\n"+sqlE);
        }
        
        return cliente;
    }
    
    public List<Cliente> obtenerClientes(){
        List<Cliente> clientes = new ArrayList<>();
        Cliente c;

        String sql = "SELECT * FROM cliente";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            //ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDni(rs.getInt(3));
                c.setMail(rs.getString(4));
                c.setActivo(rs.getBoolean(5));                
                clientes.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return clientes;
        
        
        
    }
    
    public List<Cliente> obtenerClientesActivos(){
        List<Cliente> clientes = new ArrayList<>();
        Cliente c;

        String sql = "SELECT * FROM cliente WHERE cliente.activo=true";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            //ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDni(rs.getInt(3));
                c.setMail(rs.getString(4));
                c.setActivo(rs.getBoolean(5));                
                clientes.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return clientes;
        
        
    }
    
    public List<Cliente> obtenerClientesInactivos(){
        List<Cliente> clientes = new ArrayList<>();
        Cliente c;

        String sql = "SELECT * FROM cliente WHERE cliente.activo=false";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            //ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDni(rs.getInt(3));
                c.setMail(rs.getString(4));
                c.setActivo(rs.getBoolean(5));                
                clientes.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return clientes;
        
        
    }
}
