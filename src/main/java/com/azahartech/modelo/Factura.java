package com.azahartech.modelo;

import java.time.LocalDate;

public record Factura(int id_factura, int id_paciente, LocalDate fecha, double importeTotal, String estado, MetodoPago metodoPago) {
}
