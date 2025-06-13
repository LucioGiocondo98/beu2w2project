package com.example.beu2w2project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.beu2w2project.dtos.DipendenteDTO;
import com.example.beu2w2project.entities.Dipendente;
import com.example.beu2w2project.repositories.DipendenteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DipendenteServices {
    @Autowired
    private DipendenteRepository dipendenteRepo;
    @Autowired
    private Cloudinary cloudinary;

    public Page<Dipendente> getDipendenti(int page, int size, String sortBy){
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortBy));
        return dipendenteRepo.findAll(pageable);
    }

    public Dipendente findById(int id){
        return dipendenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Dipendente con ID "+ id+" non trovato"));
    }

    public Dipendente save(DipendenteDTO body) throws BadRequestException {
        if (dipendenteRepo.existsByEmail(body.getEmail())){
            throw new BadRequestException("L'email " + body.getEmail()+" è già in uso");
        }
        if (dipendenteRepo.existsByUsername(body.getUsername())){
            throw new BadRequestException("L'username " + body.getUsername() + " è già in uso");
        }
        Dipendente nuovoDipendente= new Dipendente();
        nuovoDipendente.setUsername(body.getUsername());
        nuovoDipendente.setNome(body.getNome());
        nuovoDipendente.setCognome(body.getCognome());
        nuovoDipendente.setEmail(body.getEmail());
        nuovoDipendente.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        return dipendenteRepo.save(nuovoDipendente);
    }

    public Dipendente updateDipendente(int id,DipendenteDTO body){
        Dipendente dipendenteUpdate=findById(id);
        dipendenteUpdate.setNome(body.getNome());
        dipendenteUpdate.setUsername(body.getUsername());
        dipendenteUpdate.setCognome(body.getCognome());
        dipendenteUpdate.setEmail(body.getEmail());
        return dipendenteRepo.save(dipendenteUpdate);
    }

    public void deleteDipendente(int id){
        Dipendente dipendenteRemove=findById(id);
        dipendenteRepo.delete(dipendenteRemove);
    }

    public Dipendente uploadAvatar(int id, MultipartFile file) throws IOException {
        Dipendente dipendente= findById(id);
        String avatarUrl=(String)cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        dipendente.setAvatarUrl(avatarUrl);
        return dipendenteRepo.save(dipendente);
    }
}
