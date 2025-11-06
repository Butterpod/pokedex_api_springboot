package com.example.pokedex.controller;


import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import com.example.pokedex.schema.PokemonSchema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokedex")
//@CrossOrigin(origins = "http://localhost:3000") // autorise le front local
public class PokemonController {

    private final PokemonRepository repository;

    public PokemonController(PokemonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return repository.findAll();
    }

    @PostMapping
    public Pokemon addPokemon(@RequestBody @Valid PokemonSchema pokemonSchema) {
        // Valid vérifie les annotations de validation sur cet objet avant de continuer (PokemonSchema)
        var p = new Pokemon();
        p.setName(pokemonSchema.name());
        p.setType1(pokemonSchema.type1());
        p.setType2(pokemonSchema.type2());
        p.setColor(pokemonSchema.color());
        p.setHp(pokemonSchema.hp());
        p.setAttack(pokemonSchema.attack());
        p.setDefense(pokemonSchema.defense());
        p.setSpeed(pokemonSchema.speed());
        p.setPokeapiId(pokemonSchema.pokeapiId());
        return repository.save(p);
    }

    @GetMapping("/{id}") //http://localhost:9003/api/pokedex/10
    public Pokemon getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Pokemon pas trouvé déso "));
    }

    @PutMapping("/{id}")
    public Pokemon update(@PathVariable Long id, @RequestBody Pokemon payload) {
        Pokemon p = repository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Pokemon pas trouver"));

        p.setName(payload.getName());
        p.setType1(payload.getType1());
        p.setType2(payload.getType2());
        p.setColor(payload.getColor());
        p.setHp(payload.getHp());
        p.setAttack(payload.getAttack());
        p.setDefense(payload.getDefense());
        p.setSpeed(payload.getSpeed());
        p.setPokeapiId(payload.getPokeapiId());
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Pokemon not found");
        }
        repository.deleteById(id);
    }


// Doc util : https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    // https://www.javaguides.net/2023/08/spring-data-jpa-findbyname.html

    @GetMapping("/searchbyName")
    public Optional<Pokemon> searchbyName(@RequestParam String name) {
        return Optional.ofNullable(repository.findByName(name)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Pokemon pas trouvé (byName) ")));
    }
    // Optional permet de dire : s'il trouve rien, alors il retourne un null



    @GetMapping("/searchbyType")
    public List<Pokemon> searchByType(@RequestParam String type) {
        return repository.findByType1(type);
    }


    // les Cris
    // Source : https://pokeapi.co/
    //https://pokeapi.co/docs/v2
    @GetMapping("/{id}/cry")
    public org.springframework.http.ResponseEntity<?> getCry(@PathVariable Long id) {
        var p = repository.findById(id).orElseThrow(() ->
                new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Pokemon pas trouvé"));

        Integer pokeId = p.getPokeapiId();
        if (pokeId == null) {
            return org.springframework.http.ResponseEntity.noContent().build(); // pas d’ID => pas de cri
        }

        String url = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/"
                + pokeId + ".ogg";
        System.out.println("JE SUIS LE ID : "+pokeId);
        return org.springframework.http.ResponseEntity.ok(java.util.Map.of("url", url));
    }





    @RestControllerAdvice
    class RestErrors {
        @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
        @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
        public java.util.Map<String,String> handleSql(org.springframework.dao.DataIntegrityViolationException ex) {
            return java.util.Map.of("error", "Invalid data (DB constraint)", "detail", ex.getMostSpecificCause().getMessage());
        }
    }



}
