package com.example.beu2w2project.services;
import com.example.beu2w2project.dtos.PrenotazioneDTO;
import com.example.beu2w2project.entities.Dipendente;
import com.example.beu2w2project.entities.Prenotazione;
import com.example.beu2w2project.entities.Viaggio;
import com.example.beu2w2project.exceptions.BadRequestException;
import com.example.beu2w2project.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepo;
    @Autowired
    private DipendenteServices dipendenteServ;
    @Autowired
    private ViaggioService viaggioServ;

    public Prenotazione creaPrenotazione(PrenotazioneDTO body){
        Dipendente dipendente=dipendenteServ.findById(body.getDipendenteId());
        Viaggio viaggio= viaggioServ.findById(body.getViaggioId());
        if (prenotazioneRepo.existsByDipendenteAndViaggio_DataViaggio(dipendente,viaggio.getDataViaggio())){
            throw new BadRequestException("Il dipendente"+ dipendente.getNome()+" "+dipendente.getCognome()+ " con ID :" +dipendente.getId() + " ha già una prenotazione per la data"+ viaggio.getDataViaggio());
        }
        Prenotazione nuovaPrenotazione= new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggio(viaggio);
        nuovaPrenotazione.setDataRichiesta(LocalDate.now());
        nuovaPrenotazione.setNote(body.getNote());
        return prenotazioneRepo.save(nuovaPrenotazione);
    }
}
