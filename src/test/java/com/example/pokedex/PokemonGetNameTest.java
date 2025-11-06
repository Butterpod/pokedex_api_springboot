package com.example.pokedex;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
public class PokemonGetNameTest {

    @Autowired
    PokemonRepository repository;

    @Test
    void get_name() {
        var p = new Pokemon();
        p.setName("Metamorph");
        p.setType1("Normal");
        p.setColor("Rose");
        p.setHp(1);
        p.setAttack(1);
        p.setDefense(1);
        p.setSpeed(1);
        p = repository.save(p);

        var load = repository.findById(p.getId()).orElseThrow();
        assertThat(load.getName()).isEqualTo("Metamorph");
    }
}

