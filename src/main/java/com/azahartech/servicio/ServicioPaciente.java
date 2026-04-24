package com.azahartech.servicio;

import com.azahartech.modelo.Paciente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicioPaciente {
    //CODIGOS PARA LEER Y MODIFICAR LA BD
    public Connection con;
    static ArrayList<Paciente> listaPacientes = new ArrayList<>();

    public static void leerPacientes(Connection con) {
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
                            rs.getString("propietario"),
                            rs.getString("raza"),
                            rs.getInt("peso")
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

//    public static void insertPaciente(Connection con){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Introduce el nombre del paciente");
//        String nombre = scanner.nextLine();
//        System.out.println("Introduce especie del paciente");
//        String especie = scanner.nextLine();
//        System.out.println("Introduce raza del paciente");
//        String raza = scanner.nextLine();
//        System.out.println("Introduce la edad del paciente");
//        int edad = scanner.nextInt();
//        System.out.println("Introduce la peso del paciente");
//        int peso = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Introduce el dni del propietario");
//        String propietario = scanner.nextLine();
//        String sql = "INSERT INTO pacientestest (nombre, especie, raza, edad, peso, propietario, peso) VALUES ('"
//                + nombre + "', '"
//                + especie + "', "
//                + raza + "', '"
//                + edad + ", '"
//                + peso + "', '"
//                + propietario + "')";
//        try{
//            if(con != null){
//                Statement st = con.createStatement();
//                st.executeUpdate(sql);
//                st.close();
//            }
//        } catch (Exception e) {
//            System.err.println("ERROR" + e.getMessage());
//        }
//    }
}
