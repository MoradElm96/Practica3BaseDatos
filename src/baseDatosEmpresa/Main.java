/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatosEmpresa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        File f = new File("password.dat");

        Scanner sc = new Scanner(System.in);

      

        String insertar = "insertar";
        String borrar = "borrar";
        String actualizar = "actualizar";

        int opcion;
        do {
            System.out.println("Usuarios:");
            mostrarUsuarios(f);
            
        System.out.println("Menu base datos ");
        System.out.println("1. Insertar");
        System.out.println("2. Borrar");
        System.out.println("3. Actualizar");
        System.out.println("4. Consultar");
        System.out.println("0. Salir");
        System.out.print("Introduce la opción: ");
            
            opcion = sc.nextInt();
            

            switch (opcion) {

                case 1:
                    realizarOperacion(f, insertar);

                    break;
                case 2:
                    //borrar
                    realizarOperacion(f, borrar);

                    break;

                case 3:
                    //actualizar

                    realizarOperacion(f, actualizar);

                    break;

                case 4:
                    // consultar();
                    consultarTabla();
                    break;

                default:
                    System.out.println("Opcion no valida ");
                    break;
            }

        } while (opcion != 0);
        sc.close();

    }

    public static Connection conexion() {

        String bbdd = "jdbc:mysql://localhost/Empresa";//se usa la base de datos america,facilitada por el script
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

    public static void insertarEnTabla() throws SQLException {
        Connection con = conexion(); //objeto para hacer conexion
        //conectamos con la ayuda del metodo conexion
        Scanner sc = new Scanner(System.in);

        if (con != null) {

            System.out.println("Se ha conectado a la base datos  correctamente");

            System.out.println("Introduce la tabla para insertar");
            String tabla = sc.next();

            while (!tabla.equalsIgnoreCase("departamentos") && !tabla.equalsIgnoreCase("empleados") && !tabla.equalsIgnoreCase("proyectos")) {
                System.out.println("Escribe el nombre de una tabla correcta, departamentos, empleados o proyectos");
                tabla = sc.next();
            }

            Statement stConsultaTabla = con.createStatement();

            ResultSet rs;
            if (tabla.equalsIgnoreCase("departamentos") || tabla.equalsIgnoreCase("empleados") || tabla.equalsIgnoreCase("proyectos")) {

                if (tabla.equalsIgnoreCase("departamentos")) {

                    String sentencia = "INSERT INTO departamentos VALUES (?,?,?,?,?)";
                    PreparedStatement psInsert = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero departamento:");
                    int numDep = sc.nextInt();
                    System.out.println("Introduce nombre proyecto:");
                    String nombreDep = sc.next();
                    System.out.println("Introduce planta:");
                    int planta = sc.nextInt();
                    System.out.println("Introduce edificio:");
                    String edificio = sc.next();
                    System.out.println("Introduce ciudad:");
                    String ciudad = sc.next();

                    System.out.println("numDep: " + numDep + " nombreDep: " + nombreDep + " planta: " + planta + " edificio: " + edificio + " ciudad: " + ciudad);

                    psInsert.setInt(1, numDep);
                    psInsert.setString(2, nombreDep);
                    psInsert.setInt(3, planta);
                    psInsert.setString(4, edificio);
                    psInsert.setString(5, ciudad);

                    if (psInsert.executeUpdate() == 1) {
                        System.out.println("Registro insertado con exito");
                        psInsert.close();
                    } else {
                        System.out.println("No se ha podido insertar");
                         psInsert.close();
                    }

                } else if (tabla.equalsIgnoreCase("empleados")) {

                    String sentencia = "INSERT INTO empleados VALUES (?,?,?,?,?,?)";
                    PreparedStatement psInsert = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero empleado:");
                    int numEmpl = sc.nextInt();
                    System.out.println("Introduce nombre empleado:");
                    String nombreEmpl = sc.next();
                    System.out.println("Introduce sueldo");

                    int sueldo = sc.nextInt();
                    System.out.println("Introduce ciudad:");
                    String ciudad = sc.next();
                    System.out.println("Introduce numero departamento");
                    int numdpt = sc.nextInt();
                    System.out.println("Introduce numero proyecto");
                    int numproy = sc.nextInt();

                    psInsert.setInt(1, numEmpl);
                    psInsert.setString(2, nombreEmpl);
                    psInsert.setInt(3, sueldo);
                    psInsert.setString(4, ciudad);
                    psInsert.setInt(5, numdpt);
                    psInsert.setInt(6, numproy);

                    if (psInsert.executeUpdate() == 1) {
                        System.out.println("Registro insertado con exito");
                        psInsert.close();
                    } else {
                        System.out.println("No se ha podido insertar");
                        psInsert.close();
                    }

                } else if (tabla.equalsIgnoreCase("proyectos")) {

                    String sentencia = "INSERT INTO proyectos VALUES (?,?,?,?)";
                    PreparedStatement psInsert = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero proyecto:");
                    int numproy = sc.nextInt();
                    System.out.println("Introduce nombre proyecto:");
                    String nombreProy = sc.next();
                    System.out.println("Introduce producto");
                    String producto = sc.next();
                    System.out.println("Introduce presupuesto");
                    int presupuesto = sc.nextInt();

                    psInsert.setInt(1, numproy);
                    psInsert.setString(2, nombreProy);
                    psInsert.setString(3, producto);
                    psInsert.setInt(4, presupuesto);

                    if (psInsert.executeUpdate() == 1) {
                        System.out.println("Registro insertado con exito");
                        psInsert.close();
                    } else {
                        System.out.println("No se ha podido insertar");
                        psInsert.close();
                    }
                }

            } else {
                System.out.println("la tabla que has introducido no existe");
            }

        } else {
            System.out.println("no se ha podido conectar");
        }
        cerrarConexion(con);
    }

    public static boolean autenticarUsuario(File f, int dni, String password) {

        boolean autentificado = false;

        try {

            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            try {
                while (true) {

                    int dniLeido = dis.readInt();
                    String passwordLeida = dis.readUTF();
                    //comprobamos si las credenciales
                    if (dniLeido == dni && passwordLeida.equalsIgnoreCase(password)) {
                        autentificado = true;
                        break;
                    }

                }
            } catch (EOFException e) {
                System.out.println("Fin del fichero");
            } catch (IOException ex) {
                System.out.println("Error al leer fichero");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: el fichero " + f + "no existe");
        }

        return autentificado;
    }

    public static boolean registrarUsuario(File f, int dni, String password) {

        boolean registrado = false;

        try {

            FileOutputStream fos = new FileOutputStream(f, true);
            DataOutputStream dos = new DataOutputStream(fos);

            
            dos.writeInt(dni);
            dos.writeUTF(password);
 
            registrado = true;
           
            
            

            dos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: el fichero " + f + "no existe");

        } catch (IOException ex) {
            System.out.println("Error al leer fichero");
        }

        return registrado;
    }

    public static void mostrarUsuarios(File f) {

        try {

            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            try {
                while (true) {

                    int dniLeido = dis.readInt();
                    String passwordLeida = dis.readUTF();

                    System.out.println("Dni: " + dniLeido + "  Password: " + passwordLeida);

                }

            } catch (EOFException e) {
                System.out.println("Fin del fichero");
            } catch (IOException ex) {
                System.out.println("Error al leer fichero");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: el fichero " + f + "no existe");
        }

    }

    public static boolean consultarTabla() throws SQLException {
        boolean consultaRealizada = false;
        Connection con = conexion(); //objeto para hacer conexion
        //conectamos con la ayuda del metodo conexion
        Scanner sc = new Scanner(System.in);

        if (con != null) {

            System.out.println("Se ha conectado a la base datos  correctamente");

            System.out.println("Introduce tabla a consultar");
            System.out.println("");
            String tabla = sc.next();

            while (!tabla.equalsIgnoreCase("departamentos") && !tabla.equalsIgnoreCase("empleados") && !tabla.equalsIgnoreCase("proyectos")) {
                System.out.println("Escribe el nombre de una tabla correcta, departamentos, empleados o proyectos");
                tabla = sc.next();
            }

            String cadenaDepartamentos = "SELECT * FROM departamentos";
            String cadenaEmpleados = "SELECT * FROM empleados";
            String cadenaProyectos = "SELECT * FROM proyectos";

            Statement stConsultaTabla = con.createStatement();

            ResultSet rs;
            if (tabla.equalsIgnoreCase("departamentos") || tabla.equalsIgnoreCase("empleados") || tabla.equalsIgnoreCase("proyectos")) {

                if (tabla.equalsIgnoreCase("departamentos")) {

                    rs = stConsultaTabla.executeQuery(cadenaDepartamentos);
                    while (rs.next()) {

                        int numDep = rs.getInt(1);
                        String nombreDep = rs.getString(2);
                        int planta = rs.getInt(3);
                        String edificio = rs.getString(4);
                        String ciudad = rs.getString(5);
                        System.out.println("numDep: " + numDep + " nombreDep: " + nombreDep + " planta: " + planta + " edificio: " + edificio + " ciudad: " + ciudad);

                    }
                    stConsultaTabla.close();
                    rs.close();

                } else if (tabla.equalsIgnoreCase("empleados")) {

                    rs = stConsultaTabla.executeQuery(cadenaEmpleados);

                    while (rs.next()) {
                        int numEmpl = rs.getInt(1);
                        String nombreEmpl = rs.getString(2);
                        int sueldo = rs.getInt(3);
                        String ciudad = rs.getString(4);
                        int numdpt = rs.getInt(5);
                        int numproy = rs.getInt(6);
                        System.out.println("numEmpleado: " + numEmpl + " nombreEmpleado: " + nombreEmpl + " sueldo: " + sueldo + " ciudad: " + ciudad
                                + "numDpt: " + numdpt + " numProyecto: " + numproy);

                    }

                } else if (tabla.equalsIgnoreCase("proyectos")) {
                    rs = stConsultaTabla.executeQuery(cadenaProyectos);
                    while (rs.next()) {
                        int numproy = rs.getInt(1);
                        String nombreProy = rs.getString(2);
                        String producto = rs.getString(3);
                        int presupuesto = rs.getInt(4);

                        System.out.println("numProyecto: " + numproy + " nombreProyecto: " + nombreProy + " producto: " + producto
                                + "presupuesto: " + presupuesto);
                    }
                }

            } else {
                System.out.println("la tabla que has introducido no existe");
            }

        } else {
            System.out.println("no se ha podido conectar");
        }
        cerrarConexion(con);

        return consultaRealizada;
    }

   private static void realizarOperacion(File f, String operacion) throws SQLException {
        int dni;
        String password;

        Scanner sc = new Scanner(System.in);

        System.out.println("Insertar");
        System.out.println("Introduce el dni");
        dni = sc.nextInt();
        System.out.println("Introduce la contraseña");
        password = sc.next();

        if (!autenticarUsuario(f, dni, password)) {
            if (registrarUsuario(f, dni, password)==true) {
                System.out.println("Usuario registrado con exito");

            } else {
                System.out.println("Error al registrar al usuario");

            }

        } else {
            System.out.println("Usuario autorizado para realizar la operacion");

            if (operacion.equalsIgnoreCase("insertar")) {
                insertarEnTabla();
            } else if (operacion.equalsIgnoreCase("borrar")) {
                borrarEnTabla();
            } else if (operacion.equalsIgnoreCase("actualizar")) {
                actualizarEnTabla();
            }

        }
        sc.close();
    }


    private static void borrarEnTabla() throws SQLException {
        Connection con = conexion(); //objeto para hacer conexion
        //conectamos con la ayuda del metodo conexion
        Scanner sc = new Scanner(System.in);

        if (con != null) {

            System.out.println("Se ha conectado a la base datos  correctamente");

            System.out.println("Elige una tabla");
            String tabla = sc.next();

            while (!tabla.equalsIgnoreCase("departamentos") && !tabla.equalsIgnoreCase("empleados") && !tabla.equalsIgnoreCase("proyectos")) {
                System.out.println("Escribe el nombre de una tabla correcta, departamentos, empleados o proyectos");
                tabla = sc.next();
            }

            Statement stConsultaTabla = con.createStatement();

            ResultSet rs;
            if (tabla.equalsIgnoreCase("departamentos") || tabla.equalsIgnoreCase("empleados") || tabla.equalsIgnoreCase("proyectos")) {

                if (tabla.equalsIgnoreCase("departamentos")) {

                    String sentencia = "DELETE FROM DEPARTAMENTOS WHERE num_dpt=?";
                    PreparedStatement psDelete = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero departamento:");
                    int numDep = sc.nextInt();

                    psDelete.setInt(1, numDep);

                    if (psDelete.executeUpdate() == 1) {
                        System.out.println("Registro borrado con exito");
                    } else {
                        System.out.println("No se ha borrado ");
                    }

                } else if (tabla.equalsIgnoreCase("empleados")) {

                    String sentencia = "DELETE FROM EMPLEADOS WHERE num_empl=?";
                    PreparedStatement psDelete = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero empleado:");
                    int numEmpl = sc.nextInt();

                    psDelete.setInt(1, numEmpl);

                    if (psDelete.executeUpdate() == 1) {
                        System.out.println("Registro borrado con exito");
                    } else {
                        System.out.println("No se ha borrado ");
                    }

                } else if (tabla.equalsIgnoreCase("proyectos")) {

                    String sentencia = "DELETE FROM PROYECTOS num_proy=?";
                    PreparedStatement psDelete = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero proyecto:");
                    int numproy = sc.nextInt();

                    psDelete.setInt(1, numproy);

                    // psDelete.executeUpdate();
                    //System.out.println("Registro borrado con exito");
                    if (psDelete.executeUpdate() == 1) {
                        System.out.println("Registro borrado con exito");
                    } else {
                        System.out.println("No se ha borrado ");
                    }

                }

            } else {
                System.out.println("la tabla que has introducido no existe");
            }

        } else {
            System.out.println("no se ha podido conectar");
        }
        cerrarConexion(con);

    }

    private static void actualizarEnTabla() throws SQLException {
        Connection con = conexion(); //objeto para hacer conexion
        //conectamos con la ayuda del metodo conexion
        Scanner sc = new Scanner(System.in);

        if (con != null) {

            System.out.println("Se ha conectado a la base datos  correctamente");

            System.out.println("Introduce la tabla para actualizar");
            String tabla = sc.next();

            while (!tabla.equalsIgnoreCase("departamentos") && !tabla.equalsIgnoreCase("empleados") && !tabla.equalsIgnoreCase("proyectos")) {
                System.out.println("Escribe el nombre de una tabla correcta, departamentos, empleados o proyectos");
                tabla = sc.next();
            }

            Statement stConsultaTabla = con.createStatement();

            ResultSet rs;
            if (tabla.equalsIgnoreCase("departamentos") || tabla.equalsIgnoreCase("empleados") || tabla.equalsIgnoreCase("proyectos")) {

                if (tabla.equalsIgnoreCase("departamentos")) {

                    String sentencia = "UPDATE departamentos SET nombre_dep=?, planta=?, edificio=?,ciudad=? WHERE num_dep=?";
                    PreparedStatement psUpdate = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero departamento para actualizar:");
                    int numDep = sc.nextInt();
                    System.out.println("Introduce nombre proyecto:");
                    String nombreDep = sc.next();
                    System.out.println("Introduce planta:");
                    int planta = sc.nextInt();
                    System.out.println("Introduce edificio:");
                    String edificio = sc.next();
                    System.out.println("Introduce ciudad:");
                    String ciudad = sc.next();

                    psUpdate.setString(1, nombreDep);
                    psUpdate.setInt(2, planta);
                    psUpdate.setString(3, edificio);
                    psUpdate.setString(4, ciudad);
                    psUpdate.setInt(5, numDep);
                    if (psUpdate.executeUpdate() == 1) {
                        System.out.println("Registro actualizado con exito");
                    } else {
                        System.out.println("No se ha podido actualizar");
                    }

                } else if (tabla.equalsIgnoreCase("empleados")) {

                    String sentencia = "UPDATE empleados SET nombre_empl=?, sueldo=?, ciudad=?, num_dpt=?, num_proy=? WHERE num_empl=?";
                    PreparedStatement psUpdate = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero empleado para actualizar:");
                    int numEmpl = sc.nextInt();
                    System.out.println("Introduce nombre empleado:");
                    String nombreEmpl = sc.next();
                    System.out.println("Introduce sueldo");

                    int sueldo = sc.nextInt();
                    System.out.println("Introduce ciudad:");
                    String ciudad = sc.next();
                    System.out.println("Introduce numero departamento");
                    int numdpt = sc.nextInt();
                    System.out.println("Introduce numero proyecto");
                    int numproy = sc.nextInt();

                    psUpdate.setString(1, nombreEmpl);
                    psUpdate.setInt(2, sueldo);
                    psUpdate.setString(3, ciudad);
                    psUpdate.setInt(4, numdpt);
                    psUpdate.setInt(5, numproy);
                    psUpdate.setInt(6, numEmpl);

                    if (psUpdate.executeUpdate() == 1) {
                        System.out.println("Registro actualizado con exito");
                    } else {
                        System.out.println("No se ha podido actualizar");
                    }

                } else if (tabla.equalsIgnoreCase("proyectos")) {

                    String sentencia = "UPDATE proyectos SET nombre_proy=?, producto=?, presupuesto=? WHERE num_proy=?";
                    PreparedStatement psUpdate = con.prepareStatement(sentencia);

                    System.out.println("Introduce numero proyecto para actualizar:");
                    int numproy = sc.nextInt();
                    System.out.println("Introduce nombre proyecto:");
                    String nombreProy = sc.next();
                    System.out.println("Introduce producto");
                    String producto = sc.next();
                    System.out.println("Introduce presupuesto");
                    int presupuesto = sc.nextInt();

                    psUpdate.setString(1, nombreProy);
                    psUpdate.setString(2, producto);
                    psUpdate.setInt(3, presupuesto);
                    psUpdate.setInt(4, numproy);

                    if (psUpdate.executeUpdate() == 1) {
                        System.out.println("Registro actualizado con exito");
                    } else {
                        System.out.println("No se ha podido actualizar");
                    }

                }

            } else {
                System.out.println("la tabla que has introducido no existe");
            }

        } else {
            System.out.println("no se ha podido conectar");
        }
        cerrarConexion(con);
    }

}
