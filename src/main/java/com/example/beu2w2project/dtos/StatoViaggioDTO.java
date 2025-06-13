package com.example.beu2w2project.dtos;

import com.example.beu2w2project.enumerated.StatoViaggio;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatoViaggioDTO {
    @NotNull(message = "Lo stato del viaggio è obbligatorio")
    private StatoViaggio stato;
}
