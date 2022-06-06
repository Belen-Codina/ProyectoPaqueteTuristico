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
import javax.swing.JOptionPane;
import paqturistico.modelo.Alojamiento;
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Menu;

/**
 *
 * @author daniel
 */
public class MenuData {

    private Connection con;
    private Conexion conexion;

    public MenuData(Conexion conexion) {
        try {
            this.conexion = conexion;
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public void guardarMenu(Menu menu) {
        String sql = "INSERT INTO menu(tipo, precio, idAlojamiento, activo) VALUES (?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, menu.getTipo());
                ps.setInt(2, menu.getPrecio());
                ps.setInt(3, menu.getIdAlojamiento().getIdAlojamiento());
                ps.setBoolean(4, menu.isActivo());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (idCliente)
                if (rs.next()) {
                    System.out.println("Menu tipo: " + menu.getTipo() + ", cargado correctamente.");

                    menu.setIdMenu(rs.getInt("idMenu"));
                    System.out.println("id del menú: " + menu.getIdMenu());

                }

                ps.close();
                
                JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+menu.getIdMenu());
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar menu\n" + ex);
        }
    }
    public void actualizarMenu(Menu menu) {
        String sql = "UPDATE menu SET precio = ?, activo = ? WHERE idMenu = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menu.getPrecio());
            ps.setBoolean(2, menu.isActivo());
            
            ps.setInt(3, menu.getIdMenu());
            

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Menú actualizado exitosamente.");
        }catch(SQLException sqlE){
            System.out.println("error al actualizar + sqlE");
        }
    }
    
    public void borrarMenu(int id) {
        String sql = "UPDATE menu SET activo = 0 WHERE idMenu = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqlE) {
            System.out.println("Error al borrar\n" + sqlE);
        }
    }

    public List<Menu> obtenerMenuPorAlojamiento(String alojamiento) {

        List<Menu> m = new ArrayList<>();
        Menu menu;
        Alojamiento al;
        String sql = "SELECT menu.* FROM menu,alojamiento WHERE alojamiento.nombre= ? AND menu.idAlojamiento = alojamiento.idAlojamiento AND alojamiento.activo = 1 and menu.activo=1;";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, alojamiento);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                al = buscarAlojamiento(rs.getInt("idAlojamiento"));

                menu = new Menu();

                menu.setIdAlojamiento(al);
                menu.setIdMenu(rs.getInt("idMenu"));
                menu.setPrecio(rs.getInt("precio"));
                menu.setTipo(rs.getString("tipo"));
                menu.setActivo(rs.getBoolean("activo"));

                //System.out.println(menu);
                m.add(menu);

            }
            ps.close();

        } catch (SQLException sqlE) {
            System.out.println("error listando por alojamiento" + sqlE);
        }
        return m;
    }

    private Alojamiento buscarAlojamiento(int id) {
        AlojamientoData ad = new AlojamientoData(conexion);
        return ad.buscarAlojamiento(id);

    }
      public Menu obtenerMenu(String tipo){
        Alojamiento a;
       Menu m=new Menu();
        String sql = "SELECT * FROM menu WHERE tipo = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                a= buscarAlojamiento(rs.getInt("idAlojamiento"));
                m.setIdMenu(rs.getInt("idMenu"));
                m.setTipo(rs.getString("tipo"));
                m.setPrecio(rs.getInt("precio"));
                m.setIdAlojamiento(a);
                m.setActivo(rs.getBoolean("activo"));
             
                
            }
            
        }catch(SQLException sqlE){
            System.out.println("error al buscar menu " + sqlE);
        }
        
        return m;
    }
      public Menu buscarMenu(int id) {      
       
            Alojamiento a = null;
            Menu m = new Menu();
            String sql = "SELECT * FROM menu WHERE idMenu = ? and activo=true";
            PreparedStatement ps;
           try {  
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a= buscarAlojamiento(rs.getInt("idAlojamiento"));
                m.setIdMenu(rs.getInt("idMenu"));
                m.setTipo(rs.getString("tipo"));
                m.setPrecio(rs.getInt("precio"));
                m.setIdAlojamiento(a);
                m.setActivo(rs.getBoolean("activo"));
             
                
            }
            ps.close();
            
           
        }catch(SQLException sqlE){
            System.out.println("error al buscar menu " + sqlE);
        }
        
        return m;
      }
      
}
