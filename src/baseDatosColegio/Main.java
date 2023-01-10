/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatosColegio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        Connection con = null; //objeto para hacer conexion
        con = conexion();//conectamos con la ayuda del metodo conexion

        if (con != null) {

            System.out.println("conectado correctamente");

            String sentencia = "SELECT * FROM cambios";

            Statement stConsulta = con.createStatement();
            stConsulta.execute(sentencia);
            
            ResultSet rsConsulta = stConsulta.executeQuery(sentencia);
            
            
            sentencia = "SELECT * FROM ALUMNOS where id=?";
            PreparedStatement psConsultarEnAlumnos = con.prepareStatement(sentencia);
            
          
            
            while(rsConsulta.next()){
                int id = rsConsulta.getInt("Id");
                String tipo = rsConsulta.getString("Tipo");//tambien se puede poner el numero de columna
                
                System.out.println("tipo "+ tipo);
             
                //psConsultarEnAlumnos.setS
               // ResultSet rsAlumnos = consultarEnAlumnos.executeQuery();
                
                
                
                
            }
            
            
            

        } else {
            System.out.println("no se ha podido conectar");
        }

       

    }

    public static Connection conexion() {

        String bbdd = "jdbc:mysql://localhost/Colegio";//se usa la base de datos america,facilitada por el script
        String usuario = "root";
        String clave = "";
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(bbdd, usuario, clave);

        } catch (Exception ex) {
            System.out.println("Error al conectar con la base de datos.\n"
                    + ex.getMessage().toString());
        }

        return conn;
    }

    //metodo para cerrar la conexion a la bbdd
    public static void cerrarConexion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("la conexion no se ha cerrado");
            System.out.println(e.getMessage().toString());
        }
    }
}
