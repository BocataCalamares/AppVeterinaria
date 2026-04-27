package com.azahartech.presentacion;
import com.azahartech.modelo.Paciente;
import com.azahartech.servicio.ServicioPaciente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {
    //ATRIBUTOS
    public static Connection con;
    static ArrayList<Paciente> listaPacientes = new ArrayList<>();

    // 1. El método que contiene la lógica de conexión
    public static void testConnection() {
        String url = "jdbc:postgresql://ep-lively-sunset-aby2qoj7-pooler.eu-west-2.aws.neon.tech:5432/proyecto_alumno3?sslmode=require";
        String usuari = "neondb_owner";
        String password = "npg_3FCiZhx7VnBo";
        System.out.println("Intentando conectar a la base de datos...");
        try {
            con = DriverManager.getConnection(url, usuari, password);
            if (con != null && !con.isClosed()) {
                System.out.println("ÉXITO: Conexión establecida con proyecto_alumno3");
            }
        } catch (SQLException e) {
            System.err.println("ERROR de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 2. El punto de entrada que llama al método anterior
    public static void main(String[] args) {
        launch(args);
        // Ahora el IDE encontrará el método porque está definido justo arriba
        testConnection();
        System.out.println("--- Aplicación Veterinaria Iniciada ---");
        //AÑADIR PACIENTES
        //ServicioPaciente.insertPaciente(con);
        //LEER LISTA PACIENTES
        ServicioPaciente.leerPacientes(con);
    }
    public static void crearPaciente(String nombre, String especie, int edad, String propietario, String raza, int peso) {
        try {
            // 1. Creamos el objeto (Record, inmutable) con todos los datos
            Paciente p = new Paciente(0, nombre, especie, edad, propietario, raza, peso);
            // 2. Creamos la query con "?" y usamos PreparedStatement para insertar
            // vinculamos cada dato con la query mediante los métodos setString, setInt, etc...
            String sql = "INSERT INTO pacientestest (nombre, especie, raza, edad, peso, propietario) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, especie);
            pst.setString(3, raza);
            pst.setInt(4, edad);
            pst.setInt(5, peso);
            pst.setString(6, propietario);
            pst.execute();
            System.out.println("Paciente guardado con éxito.");
            pst.close();
        }
        catch (Exception e){
            System.err.println("ERROR 2- No se pudo crear el Statement: " + e.getMessage());
        }
    };
    @Override
    public void start(Stage primaryStage) {
        testConnection();
        try {
            // 1. Cargamos el archivo FXML desde la carpeta de recursos
            // Nota: La ruta debe empezar por / y coincidir con la jerarquía de carpetas
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("InterfazVeterinariaPrueba.fxml"));
            Parent root = loader.load();
            // 2. Creamos la "Escena" (el contenido de la ventana)
            Scene scene = new Scene(root);
            // 3. Configuramos el "Escenario" (la ventana en sí)
            primaryStage.setTitle("Veterinaria JRA - Registro de Pacientes");
            primaryStage.setScene(scene);
            // 4. Hacemos que la ventana sea visible
            primaryStage.show();
        } catch (Exception e) {
            // Es vital capturar errores aquí por si la ruta del FXML está mal
            e.printStackTrace();
        }
    };


}