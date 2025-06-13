package com.example.beu2w2project.repositories;

import com.example.beu2w2project.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {
Optional<Dipendente> findByEmail(String email);
boolean existsByEmail(String email);
boolean existsByUsername(String username);
}
