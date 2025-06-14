package com.example.beu2w2project.controllers;

import com.example.beu2w2project.dtos.StatoViaggioDTO;
import com.example.beu2w2project.dtos.ViaggioDTO;
import com.example.beu2w2project.entities.Viaggio;
import com.example.beu2w2project.exceptions.BadRequestException;
import com.example.beu2w2project.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0")int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return viaggioService.getViaggi(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable int id){
        return viaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated ViaggioDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return viaggioService.save(body);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable int id,@RequestBody @Validated ViaggioDTO body,BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return viaggioService.updateViaggio(id,body);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable int id, @RequestBody @Validated StatoViaggioDTO body,BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return viaggioService.updateStato(id,body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable int id){
        viaggioService.deleteViaggio(id);
    }
}
