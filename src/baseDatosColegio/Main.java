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

            System.out.println("Se ha conectado a la base datos  correctamente");

                //sentencia para recuperar los registros de la tabla cambios
            String sentenciaCambios = "SELECT * FROM cambios";
            //registro para recuperar la tabla alumnos
            String sentenciaAlumnos="SELECT * FROM alumnos";
            Statement st = null;
            ResultSet rs;
            
            rs = st.executeQuery(sentenciaCambios);
            while(rs.next()){
                //recuperamos los valores de cada registro
                int matricula = rs.getInt("Matricula");
                String tipo = rs.getString("Tipo");
                int incremento = rs.getInt("Incremento");
                //sentencia para recuperar la nota indicando la matricula
                String sentenciaNota = "SELECT Nota FROM alumnos WHERE Matricula=?";
                //
                String sentenciaModificar = "UPDATE alumnos SET Nota=? WHERE Matricula =?";
                String sentenciaAlta ="INSERT INTO alumnos (Matricula, Nota) VALUES(?,?)";
                //creamos los objetos necesarios
                PreparedStatement sentenciaNotaPreparada = con.prepareStatement(sentenciaNota);
                PreparedStatement sentenciaModificarPreparada = con.prepareStatement(sentenciaModificar);
                PreparedStatement sentenciaAltaPreparada = con.prepareStatement(sentenciaAlta);
          
                //asignamos a los rs los registros recuperados de la tabla alumnos
                rs = st.executeQuery(sentenciaAlumnos);
                
              //  sentenciaModificarPreparada.setInt(1, nota);
                
                
            }
            
            

        } else {
            System.out.println("no se ha podido conectar");
        }
        cerrarConexion(con);
       

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
            System.out.println("La conexion a la base de datos se ha cerrado correctamente");
        } catch (SQLException e) {
            System.out.println("la conexion no se ha cerrado");
            System.out.println(e.getMessage().toString());
        }
    }
}
