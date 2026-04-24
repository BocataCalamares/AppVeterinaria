package com.azahartech.modelo;

import com.azahartech.presentacion.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PacienteController {



    public class CrearPacienteController {
        @FXML
        private TextField fieldNombre;
        @FXML
        private TextField fieldEspecie;
        @FXML
        private TextField fieldEdad;
        @FXML
        private TextField fieldPropietario;
        @FXML
        private Button btn_guardar;
        @FXML
        private TextField fieldPeso;
        @FXML
        private TextField fieldRaza;

        @FXML
        public void accionGuardar() {
            // A. Extraer el texto de los campos
            String nombre = fieldNombre.getText();
            String especie = fieldEspecie.getText();
            String edadString = fieldEdad.getText();
            String propietario = fieldPropietario.getText();
            String raza = fieldRaza.getText();
            String pesoString = fieldPeso.getText();
            // B. Convertir el texto de la edad a un número entero
            try {
                int edad = Integer.parseInt(edadString);
                int peso = Integer.parseInt(pesoString);
                // C. Llamar a tu método estático para crear el paciente
                // (Asumiendo que crearPaciente está en la clase Main)
                Main.crearPaciente(nombre, especie, edad, propietario, raza, peso);
                // Opcional: Limpiar el formulario después de guardar
                fieldNombre.clear();
                fieldEspecie.clear();
                fieldEdad.clear();
                fieldPropietario.clear();
                fieldRaza.clear();
                fieldPeso.clear();

                System.out.println("¡Paciente enviado a la base de datos!");
            } catch (NumberFormatException e) {
                // Si el usuario escribe "cinco" en lugar de "5", el programa no petará.
                System.err.println("Error: La edad debe ser un número válido.");
            }
        }

    }


}
