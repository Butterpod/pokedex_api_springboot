package com.example.pokedex.model;
import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type1;

    private String type2;

    @Column(nullable = false)
    private String color;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "pokeapi_id")
    private Integer pokeapiId; // nullable


    // Getters et Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }
    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }
    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHp() {
        return hp;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpeed() {
        return speed;
    }
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPokeapiId() { return pokeapiId; }
    public void setPokeapiId(Integer pokeapiId) { this.pokeapiId = pokeapiId; }
}
