package com.azahartech;
import com.azahartech.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class Main {

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
        // Ahora el IDE encontrará el método porque está definido justo arriba
        testConnection();
        System.out.println("--- Aplicación Veterinaria Iniciada ---");
        leerPacientes();
    }


//CODIGOS PARA LEER Y MODIFICAR LA BD


    public static void leerPacientes() {
        String sql = "SELECT * FROM pacientestest";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                System.out.println("\n--- LISTADO DE PACIENTES (Usando Records) ---");
                while (rs.next()) {
                    // 1. Creamos el objeto Paciente con los datos de la BD
                    Paciente p = new Paciente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("especie"),
                            rs.getInt("edad"),
                            rs.getString("propietario")
                    );
                    // 2. Ahora 'p' es un objeto. Podemos imprimirlo directamente
                    // gracias al toString() autom·tico de los Records.
                    System.out.println(p);
                    listaPacientes.add(p);
                }
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("ERROR - No se pudo crear el Statement para lectura: " + e.getMessage());
        }
    }
}