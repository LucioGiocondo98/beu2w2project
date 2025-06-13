package com.example.beu2w2project.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DipendenteDTO {
    @NotEmpty(message = "Lo username è obbligatorio")
    @Size(min = 3,max = 20,message = "Lo username deve avere tra i 3 e i 20 caratteri")
    private String username;
    @NotEmpty(message = "Il nome è obbligatorio")
    private String nome;
    @NotEmpty(message = "Il cognome è obbligatorio")
    private String cognome;
    @NotEmpty(message = "L'email è obbligatoria")
    @Email(message = "Il formato dell'email non è valido")
    private String email;
}
