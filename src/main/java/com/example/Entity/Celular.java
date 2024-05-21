package com.example.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "celular")
public class Celular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String serial;
    private LocalDate fechaCompra;
    private int anoLanzamiento;
    private double precio;
    private String sistemaOperativo;
    private String gama;
    private boolean eliminado = false;
}
