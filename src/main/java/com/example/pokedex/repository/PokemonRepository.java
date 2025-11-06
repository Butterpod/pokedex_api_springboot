package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByType1(String type1);
    Optional<Pokemon> findByName(String name);


}
