package com.azahartech.presentacion;
import com.azahartech.modelo.Paciente;
import com.azahartech.servicio.ServicioPaciente;

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
        //AÑADIR PACIENTES
        ServicioPaciente.insertPaciente(con);
        //LEER LISTA PACIENTES
        ServicioPaciente.leerPacientes(con);
    }



}