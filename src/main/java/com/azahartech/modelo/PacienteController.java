package com.azahartech.modelo;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.*;

public class PacienteController {



    public class CrearPacienteController {
        @FXML
        private TextField field_nombre;
        @FXML
        private TextField field_especie;
        @FXML
        private TextField field_edad;
        @FXML
        private TextField field_propietario;
        @FXML
        private Button btn_guardar;
        @FXML

        public void accionGuardar() {
            // A. Extraer el texto de los campos
            String nombre = field_nombre.getText();
            String especie = field_especie.getText();
            String edadString = field_edad.getText();
            String propietario = field_propietario.getText();
            // B. Convertir el texto de la edad a un número entero
            try {
                int edad = Integer.parseInt(edadString);
                // C. Llamar a tu método estático para crear el paciente
                // (Asumiendo que crearPaciente está en la clase Main)
                Main.crearPaciente(nombre, especie, edad, propietario);
                // Opcional: Limpiar el formulario después de guardar
                field_nombre.clear();
                field_especie.clear();
                field_edad.clear();
                field_propietario.clear();
                System.out.println("¡Paciente enviado a la base de datos!");
            } catch (NumberFormatException e) {
                // Si el usuario escribe "cinco" en lugar de "5", el programa no petará.
                System.err.println("Error: La edad debe ser un número válido.");
            }
        }

    }


}
