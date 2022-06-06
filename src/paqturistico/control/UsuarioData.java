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
import paqturistico.modelo.Conexion;
import paqturistico.modelo.Usuario;

/**
 *
 * @author Belen
 */
public class UsuarioData {
        private Connection con;
    private Conexion conexion;
    public UsuarioData(Conexion conexion) {

        try {
            this.conexion=conexion;
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }

   
   public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre,apellido, mail, areaDeTrabajo, password) VALUES (?,?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellido());
                ps.setString(3, usuario.getMail());
                ps.setString(4, usuario.getAreaDeTrabajo());
                ps.setString(5, usuario.getPassword());
                
              
                ps.executeUpdate(); 
                ResultSet rs = ps.getGeneratedKeys(); 
                if (rs.next()) {
                    System.out.println("Usuario " + usuario.getNombre() + "  cargado exitosamente");
                  
                   usuario.setIdUsuario(rs.getInt("idUsuario"));
                    System.out.println("Id del Usuario: " + usuario.getIdUsuario());
                    
                }
                
                ps.close();
                
                JOptionPane.showMessageDialog(null, "Cargado con exito, id: "+usuario.getIdUsuario());
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar Usuario\n" + ex);
        }
    }
    
    
    
            public List<Usuario> obtenerUsuariosPorApellido(String apellido) {

        List<Usuario> u = new ArrayList<>();
        Usuario usuario;
       
        String sql = "SELECT * FROM usuario WHERE apellido= ? ";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, apellido);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

               usuario = new Usuario();
                
               usuario.setNombre(rs.getString("nombre"));
               usuario.setApellido(rs.getString("apellido"));
               usuario.setMail(rs.getString("mail"));
               usuario.setAreaDeTrabajo(rs.getString("areaDeTrabajo"));
               usuario.setPassword(rs.getString("password"));
               usuario.setIdUsuario(rs.getInt("idUsuario"));
              
                u.add(usuario);

            }
            ps.close();

        } catch (SQLException sqlE) {
            System.out.println("Error al obtener usuarios por apellido" + sqlE);
        }
        return u;
    }
    
}
