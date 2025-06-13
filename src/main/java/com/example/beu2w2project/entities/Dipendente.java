package com.example.beu2w2project.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dipendenti")
@Data
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false,unique = true)
    private String email;
    private String avatarUrl;
}
