package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private long pokemonId;

    private String pokemonName;

    private short hp;

    private short attack;

    private short defense;

    private String imageUrl;

    @JsonCreator
    public Pokemon(
            @JsonProperty("id") long pokemonId,
            @JsonProperty("name") String pokemonName,
            @JsonProperty("stats") List<Stat> stat,
            @JsonProperty("sprites") Sprite sprite) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.hp = stat.get(0).getBaseStat();
        this.attack = stat.get(1).getBaseStat();
        this.defense = stat.get(2).getBaseStat();
        this.imageUrl = sprite.getImageUrl();
    }
}
