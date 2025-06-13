package com.example.beu2w2project.dtos;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ViaggioDTO {
    @NotEmpty(message = "La destinaziione è obbligatoria")
    private String destinazione;
    @NotNull(message = "La data del viaggio è obbligatoria")
    @FutureOrPresent(message = "La data del viaggio non può essere nel passato")
    private LocalDate dataViaggio;
}
