package com.epam.izh.rd.online.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFetchingServiceTest {

    SimplePokemonFetching simplePokemonFetching = new SimplePokemonFetching();

    @Test
    void testFetchByName() {
        String pokemonName = "pikachu";
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png";
        assertEquals("pikachu", simplePokemonFetching.fetchByName(pokemonName).getPokemonName());
        assertEquals(imageUrl, simplePokemonFetching.fetchByName(pokemonName).getImageUrl());
    }

    @Test
    void testGetPokemonImage() {
        String pokemonName = "slowpoke";
        assertNotNull(simplePokemonFetching.getPokemonImage(pokemonName));
    }
}