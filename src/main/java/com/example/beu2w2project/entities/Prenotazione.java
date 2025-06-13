package com.example.beu2w2project.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Data
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_richiesta",nullable = false)
    private LocalDate dataRichiesta;
    private String note;
    @ManyToOne
    @JoinColumn(name = "dipendente_id",nullable = false)
    private Dipendente dipendente;
    @ManyToOne
    @JoinColumn(name = "viaggio_id",nullable = false)
    private Viaggio viaggio;

}
