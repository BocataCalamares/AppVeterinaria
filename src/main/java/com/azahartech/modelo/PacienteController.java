package com.azahartech.modelo;

import com.azahartech.presentacion.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteController {

    // Estos nombres deben ser IGUALES a los fx:id del FXML
    @FXML private TextField nombrePaciente;    // Corresponde a fx:id="nombrePaciente"
    @FXML private TextField nombrePropietario; // Corresponde a fx:id="nombrePropietario"
    @FXML private TextField fieldEspecie;
    @FXML private TextField fieldEdad;
    @FXML private TextField fieldRaza;
    @FXML private TextField fieldPeso;

    public void accionGuardar() {
        // Asegúrate de que el nombre de la tabla sea 'pacientes' o 'pacientestest' según tu DB
        String sql = "INSERT INTO pacientestest (nombre, especie, edad, raza, peso, propietario) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = Main.con.prepareStatement(sql)) {
            // 1. Extraer y convertir datos
            String nombre = nombrePaciente.getText();
            String especie = fieldEspecie.getText();
            String propietario = nombrePropietario.getText();
            String raza = fieldRaza.getText();
            int edad = Integer.parseInt(fieldEdad.getText());
            int peso = Integer.parseInt(fieldPeso.getText());

            // 2. Asignar parámetros al PreparedStatement
            ps.setString(1, nombre);
            ps.setString(2, especie);
            ps.setInt(3, edad);
            ps.setString(4, raza);
            ps.setInt(5, peso);
            ps.setString(6, propietario);

            // 3. Ejecutar y limpiar
            ps.executeUpdate();
            System.out.println("¡Paciente " + nombre + " guardado con éxito!");
            limpiarDatos();

        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: La edad y el peso deben ser números válidos.");
        }
    }

    public void limpiarDatos() {
        nombrePaciente.clear();
        nombrePropietario.clear();
        fieldEspecie.clear();
        fieldEdad.clear();
        fieldRaza.clear();
        fieldPeso.clear();
    }
    @FXML
    public void abrirLista() {
        Main.abrirLista();
    }
}
