package com.example.beu2w2project.entities;

import com.example.beu2w2project.enumerated.StatoViaggio;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Data
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String destinazione;
    @Column(name = "data_del_viaggio",nullable = false)
    private LocalDate dataViaggio;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;
}
