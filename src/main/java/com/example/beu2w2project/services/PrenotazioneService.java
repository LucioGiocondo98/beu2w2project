package com.example.beu2w2project.services;

import com.example.beu2w2project.dtos.PrenotazioneDTO;
import com.example.beu2w2project.entities.Dipendente;
import com.example.beu2w2project.entities.Prenotazione;
import com.example.beu2w2project.entities.Viaggio;
import com.example.beu2w2project.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepo;
    @Autowired
    private DipendenteServices dipendenteServ;
    @Autowired
    private ViaggioService viaggioServ;

    public Prenotazione creaPrenotazione(PrenotazioneDTO prenotazioneDTO){
        //Dipendente dipendente=dipendenteServ.findById(prenotazioneDTO.getDipendenteId());
        //Viaggio viaggio= viaggioServ.findById(prenotazioneDTO)
    }
}
