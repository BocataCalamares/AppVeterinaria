package com.azahartech.modelo;

import java.time.LocalDate;

public record Consulta(int id_paciente, int id_consulta, LocalDate fecha, String motivo, String diagnostico, String tratamiento) {
}
