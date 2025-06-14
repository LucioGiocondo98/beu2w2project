package com.example.beu2w2project.controllers;

import com.example.beu2w2project.dtos.DipendenteDTO;
import com.example.beu2w2project.entities.Dipendente;
import com.example.beu2w2project.exceptions.BadRequestException;
import com.example.beu2w2project.services.DipendenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteServices dipendenteServices;

    @GetMapping
    public Page<Dipendente>getDipendenti(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10")int size,
                                         @RequestParam(defaultValue = "id")String sortBy){
        return dipendenteServices.getDipendenti(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendenteServices.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return dipendenteServices.save(body);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable int id,@RequestBody @Validated DipendenteDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return dipendenteServices.updateDipendente(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable int id){
        dipendenteServices.deleteDipendente(id);
    }

    @PostMapping("/{id}/avatar")
    public Dipendente uploadAvatar(@PathVariable int id, @RequestParam("avatar")MultipartFile file) throws IOException {
        return dipendenteServices.uploadAvatar(id,file);
    }
}
