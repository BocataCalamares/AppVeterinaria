package com.azahartech.modelo;

import java.sql.Timestamp;
import java.time.LocalDate;

public record Cita(int id_cita, int id_paciente, Veterinario veterinario, LocalDate fecha, Timestamp hora, String estado) {
}
