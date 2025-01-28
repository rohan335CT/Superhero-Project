package com.cleartax.superhero.dto;

import lombok.Data;

@Data
public class SuperheroRequestBody {
    private String superheroName;
    private String superheroPower;
    private String superheroUniverse;
}
