package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFightingClubServiceTest {

    SimplePokemonFetching simplePokemonFetching = new SimplePokemonFetching();
    SimplePokemonFightingClub simplePokemonFightingClub = new SimplePokemonFightingClub();

    @Test
    void testDoBattle() {
        Pokemon pokemonOne = simplePokemonFetching.fetchByName("pikachu");
        Pokemon pokemonTwo = simplePokemonFetching.fetchByName("slowpoke");

        assertEquals(pokemonTwo, simplePokemonFightingClub.doBattle(pokemonOne, pokemonTwo));
    }

    @Test
    void testShowWinner() {
        assertTrue(new File("win.png").exists());
    }
}