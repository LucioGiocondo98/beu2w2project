package com.example.beu2w2project.controllers;

import com.example.beu2w2project.dtos.PrenotazioneDTO;
import com.example.beu2w2project.entities.Prenotazione;
import com.example.beu2w2project.exceptions.BadRequestException;
import com.example.beu2w2project.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione creaPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation){
        System.out.println("Dati ricevuti nel controller: " + body);
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return prenotazioneService.creaPrenotazione(body);
    }
}
