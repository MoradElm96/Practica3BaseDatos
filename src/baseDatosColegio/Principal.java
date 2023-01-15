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
import java.util.Scanner;

/**
 *
 * @author Morad
 */
public class Principal {
 
    public static void main(String[] args) throws SQLException {

        Connection con = null; //objeto para hacer conexion
        con = conexion();//conectamos con la ayuda del metodo conexion

        if (con != null) {

            System.out.println("Se ha conectado a la base datos  correctamente");

            Statement st = con.createStatement();
          
           
//TABLA ANTES DE ACTUALIZAR --------------
            String cadena = "SELECT * FROM Alumnos";
            ResultSet rs1 = st.executeQuery(cadena);
            System.out.println("---------------Tabla alumnos ANTES  ---------------");
            while (rs1.next()) {
                int id = rs1.getInt("Id");
                String nombre = rs1.getString("Nombre");
                String apellidos = rs1.getString("Apellido");
                int nota = rs1.getInt("Nota");
                System.out.println("Matricula: " + id + "  Nombre: " + nombre + " ----------- Apellidos: " + apellidos + " Nota: " + nota);
            }
            
            cadena = "SELECT * FROM cambios";
            ResultSet rsCambios = st.executeQuery(cadena);
            
            while(rsCambios.next()){
                int id = rsCambios.getInt(1);
                String tipo = rsCambios.getString(2);
                int matricula = rsCambios.getInt(3);
                int incremento = rsCambios.getInt(4);
                        
               
                
                 if (tipo.equalsIgnoreCase("BJ")) {
                     //es una baja
                     cadena="DELETE FROM Alumnos WHERE id=?";
                     PreparedStatement psDelete = con.prepareStatement(cadena);
                     psDelete.setInt(1, matricula);
                     
                     if(psDelete.executeUpdate()==0){
                         System.out.println("No existe un alumno con la la matricula " + matricula);
                         darAlta(con, matricula);
                        
                     }else {
                            System.out.println("El alumno con matricula " + matricula + " ha sido dado de baja");
                            psDelete.close();
                     }
                    
                  

                 } 
                 else if(tipo.equalsIgnoreCase("MD")) {
                     cadena= "UPDATE Alumnos SET nota=nota+? WHERE id=?";
                     PreparedStatement psModificar = con.prepareStatement(cadena);
                     psModificar.setInt(1,incremento);
                     psModificar.setInt(2, matricula);
                        if(psModificar.executeUpdate()==0){
                            System.out.println("No existe un alumno con la la matricula " + matricula);
                            darAlta(con, matricula);
                        }else{
                             System.out.println("El alumno con matricula " + matricula + " ha sido modificado");
                             psModificar.close();
                        }
                       
                   }
                 
                    
            }
            
            //TABLA ACTUALIZADA
            cadena = "SELECT * FROM Alumnos";
            ResultSet rs3 = st.executeQuery(cadena);
            System.out.println("---------------Tabla alumnos Actualizada ---------------");
            while (rs3.next()) {
                int matricula = rs3.getInt(1);
                String nombre = rs3.getString(2);
                String apellidos = rs3.getString(3);
                int nota = rs3.getInt(4);
                System.out.println("Matricula: " + matricula + "  Nombre: " + nombre + " Apellidos: " + apellidos + " Nota: " + nota);

            }
            
            st.close();
            
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
    
    public static void darAlta(Connection con, int matricula) throws SQLException{
        Scanner sc = new Scanner(System.in);
        String respuesta;
      
        System.out.println("¿Desea darle de alta?(Y/N)");
        respuesta = sc.next();
        if(respuesta.equalsIgnoreCase("Y")){
            if (respuesta.equalsIgnoreCase("Y")) {
                    System.out.println("Introduzca los datos del alumno");
                 
                    System.out.println("Nombre");
                    String nombre = sc.next();
                    System.out.println("Apellidos");
                   
                    String apellido = sc.nextLine();
                    sc.next();
                    System.out.println("Nota");
                    int nota = sc.nextInt();
                    
                    String cadena = "INSERT INTO Alumnos VALUES(?,?,?,?)";
                    PreparedStatement psInsert = con.prepareStatement(cadena);
                     psInsert.setInt(1, matricula);
                     psInsert.setString(2, nombre);
                     psInsert.setString(3, apellido);
                     psInsert.setInt(4, nota);
                     psInsert.executeUpdate();

                    System.out.println("El alumno ha sido dado de alta de manera exitosa"); 
            }else{
                 System.out.println("El alumno no se ha dado de alta"); 
            }
        
    }
}
}
/*
            //EJERCICIO
            cadena = "SELECT * FROM Cambios";
            PreparedStatement stCambios = con.prepareStatement(cadena);
            ResultSet rsCambios = stCambios.executeQuery();
            

            while (rsCambios.next()) {
                int matricula = rsCambios.getInt("Matricula");
                String tipo = rsCambios.getString("Tipo");
                int incremento = rsCambios.getInt("Incremento");

                System.out.println("Matricula:  " + matricula + "    Tipo: " + tipo + "    Incremento: " + incremento);

                //comprobamos que exista el alumno en la tabla alumnos
                cadena = "SELECT * FROM Alumnos WHERE Matricula=?";

               PreparedStatement stAlumnos = con.prepareStatement(cadena);
               stAlumnos.setInt(1, matricula);
               ResultSet rsAlumnos= stAlumnos.executeQuery();

                if (tipo.equals("BJ")) {
                    //es una baja
                    if (rsAlumnos.next()) {
                        cadena = "DELETE FROM Alumnos WHERE Matricula=?";
                        ps = con.prepareStatement(cadena);
                        ps.setInt(1, matricula);
                        ps.executeUpdate();
                        System.out.println("El alumno con matricula " + matricula + " ha sido dado de baja");

                    } else {
                        System.out.println("No existe un alumno con la la matricula " + matricula);
                    }
                } else if (tipo.equals("MD")) {
                    if (rsAlumnos.next()) {
                        int nota = rsAlumnos.getInt("Nota");
                        //incrementamos la nota
                        nota += incremento;
                        cadena = "UPDATE Alumnos SET Nota=? WHERE Matricula=?";
                        ps = con.prepareStatement(cadena);
                        ps.setInt(1, nota);
                        ps.setInt(2, matricula);
                        ps.executeUpdate();
                        System.out.println("El alumno con matricula " + matricula + " ha sido modificado");

                    } else {
                        System.out.println("No existe el alumno con la matricula " + matricula);
                        Scanner sc = new Scanner(System.in);
                        System.out.println("¿Desea darle de alta?(Y/N)");
                        String respuesta = sc.nextLine();
                        if (respuesta.equalsIgnoreCase("Y")) {
                            System.out.println("Introduzca los datos del alumno");
                            System.out.println("Nombre");
                            String nombre = sc.nextLine();
                            System.out.println("Apellidos");
                            String apellidos = sc.nextLine();
                            System.out.println("Nota");
                            int nota = sc.nextInt();

                            cadena = "INSERT INTO Alumnos VALUES(?,?,?,?)";
                            ps = con.prepareStatement(cadena);
                            ps.setInt(1, matricula);
                            ps.setString(2, nombre);
                            ps.setString(3, apellidos);
                            ps.setInt(4, nota);
                            ps.executeUpdate();
                            System.out.println("El alumno ha sido dado de alta de manera exitosa");

                        }

                    }
                }

            }

            //TABLA ACTUALIZADA
            cadena = "SELECT * FROM Alumnos";
            ResultSet rs3 = st.executeQuery(cadena);
            System.out.println("---------------Tabla alumnos Actualizada ---------------");
            while (rs3.next()) {
                String matricula = rs3.getString("Matricula");
                String nombre = rs3.getString("Nombre");
                String apellidos = rs3.getString("Apellidos");
                int nota = rs3.getInt("Nota");
                System.out.println("Matricula: " + matricula + "  Nombre: " + nombre + " Apellidos: " + apellidos + " Nota: " + nota);

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
*/