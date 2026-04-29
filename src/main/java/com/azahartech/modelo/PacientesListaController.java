package com.azahartech.modelo;

import com.azahartech.presentacion.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.Statement;

public class PacientesListaController {

    @FXML private TableView<Paciente> tablaPacientes;

    // Columnas — coinciden con las definidas en el FXML
    @FXML private TableColumn<Paciente, Integer> colId;
    @FXML private TableColumn<Paciente, String>  colNombre;
    @FXML private TableColumn<Paciente, String>  colEspecie;
    @FXML private TableColumn<Paciente, String>  colRaza;
    @FXML private TableColumn<Paciente, Integer> colEdad;
    @FXML private TableColumn<Paciente, Integer> colPeso;
    @FXML private TableColumn<Paciente, String>  colPropietario;

    /**
     * initialize() se ejecuta automáticamente al cargar el FXML.
     * Aquí configuramos las columnas y cargamos los datos.
     */
    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatos();
    }

    /**
     * Vincula cada columna al campo correspondiente del record Paciente.
     *
     * NOTA: Paciente es un record, cuyos accessors se llaman igual que el campo
     * (p.ej. paciente.nombre()), NO con prefijo "get".
     * Por eso usamos setCellValueFactory con lambda en lugar de PropertyValueFactory,
     * que busca métodos getNombre(), getEdad(), etc. y no los encontraría.
     */
    private void configurarColumnas() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().id()).asObject());

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().nombre()));

        colEspecie.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().especie()));

        colRaza.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().raza()));

        colEdad.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().edad()).asObject());

        colPeso.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().peso()).asObject());

        colPropietario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().propietario()));
    }

    /**
     * Consulta la BD y rellena la tabla.
     */
    private void cargarDatos() {
        ObservableList<Paciente> lista = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pacientestest ORDER BY id";
        try {
            if (Main.con != null && !Main.con.isClosed()) {
                Statement st = Main.con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Paciente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("especie"),
                            rs.getInt("edad"),
                            rs.getString("propietario"),
                            rs.getString("raza"),
                            rs.getInt("peso")
                    ));
                }
                rs.close();
                st.close();
            } else {
                System.err.println("Sin conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar pacientes: " + e.getMessage());
        }

        tablaPacientes.setItems(lista);
    }

    /**
     * Acción del botón "Recargar lista": vuelve a consultar la BD.
     */
    @FXML
    public void recargarPacientes() {
        cargarDatos();
    }
}