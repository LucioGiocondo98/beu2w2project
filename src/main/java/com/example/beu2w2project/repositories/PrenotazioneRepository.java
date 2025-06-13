package com.example.beu2w2project.repositories;

import com.example.beu2w2project.entities.Dipendente;
import com.example.beu2w2project.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Integer> {
    boolean existsByDipendenteAndViaggio_DataViaggio(Dipendente dipendente, LocalDate dataViaggio);
}
