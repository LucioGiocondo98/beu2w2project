package com.example.beu2w2project.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneDTO {
    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private int dipendenteId;
    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private int viaggioID;
    private String note;
}
