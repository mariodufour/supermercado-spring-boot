package com.proyectocode.Supermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductoDTO {
    private long id;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;
}
