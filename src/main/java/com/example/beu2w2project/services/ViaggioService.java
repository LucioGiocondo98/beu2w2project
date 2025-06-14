package com.example.beu2w2project.services;

import com.example.beu2w2project.dtos.StatoViaggioDTO;
import com.example.beu2w2project.dtos.ViaggioDTO;
import com.example.beu2w2project.entities.Viaggio;
import com.example.beu2w2project.enumerated.StatoViaggio;
import com.example.beu2w2project.exceptions.NotFoundException;
import com.example.beu2w2project.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepo;

    public Page<Viaggio> getViaggi(int page,int size, String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return viaggioRepo.findAll(pageable);
    }

    public Viaggio findById(int id){
        return  viaggioRepo.findById(id).orElseThrow(() -> new NotFoundException("Viaggio con ID "+ id+" non trovato"));
    }

    public Viaggio save(ViaggioDTO viaggioDto){
        Viaggio nuovoViaggio= new Viaggio();
        nuovoViaggio.setDestinazione(viaggioDto.getDestinazione());
        nuovoViaggio.setDataViaggio(viaggioDto.getDataViaggio());
        nuovoViaggio.setStato(StatoViaggio.IN_PROGRAMMA);
        return viaggioRepo.save(nuovoViaggio);
    }

    public Viaggio updateViaggio(int id,ViaggioDTO viaggioDTO){
        Viaggio viaggioAggiornato= findById(id);
        viaggioAggiornato.setDestinazione(viaggioDTO.getDestinazione());
        viaggioAggiornato.setDataViaggio(viaggioDTO.getDataViaggio());
        return viaggioRepo.save(viaggioAggiornato);
    }

    public Viaggio updateStato(int id, StatoViaggioDTO statoViaggioDTO){
        Viaggio viaggio= findById(id);
        viaggio.setStato(statoViaggioDTO.getStato());
        return viaggioRepo.save(viaggio);
    }

    public void deleteViaggio(int id){
        Viaggio viaggio= findById(id);
        viaggioRepo.delete(viaggio);
    }


}
