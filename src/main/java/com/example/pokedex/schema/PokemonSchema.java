package com.example.pokedex.schema;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

//trop fort le record en java
    // représente  donnée attendue en entrée pour le Post.
public record PokemonSchema(
        @NotBlank String name,
        @NotBlank String type1,
        String type2,
        @NotBlank String color,
        @Min(1) Integer hp,
        @Min(1) Integer attack,
        @Min(1) Integer defense,
        @Min(1) Integer speed,
        @NotBlank Integer pokeapiId
) {}