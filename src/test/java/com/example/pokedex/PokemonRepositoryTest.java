package com.example.pokedex;

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

@SpringBootTest
@Transactional // chaque test sera rollbacké à la fin
class PokemonRepositoryTest {

    @Autowired
    PokemonRepository repository;

    @PersistenceContext           // <-- injecte l'EntityManager JPA
    EntityManager em;


    @Test
    void crud_test() {
        // CREATE
        Pokemon p =new Pokemon();
        p.setName("Poppy");
        p.setType1("Acier");
        p.setColor("Gris");
        p.setAttack(10);
        p.setHp(10);
        p.setDefense(10);
        p.setSpeed(10);

        repository.save(p);

        assertNotNull((p.getId()));

        // READ
        var load= repository.findById(p.getId())
                            .orElseThrow(() -> new RuntimeException("Pokemon pas trouver"));


        System.out.println(load.getName());
        assertThat(load.getName()).isEqualTo("Poppy");



        // UPDATE
        System.out.println("HP avant : "+load.getHp());
        load.setHp(20);
        repository.flush();
        em.clear();


        var load1= repository.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Pokemon pas trouver"));

        System.out.println("Hp aprés modif: "+load1.getHp());
        assertThat(load.getHp()).isEqualTo(20);




        // DELETE

        repository.delete(p);
        System.out.println(p.getName());;

    }
}
