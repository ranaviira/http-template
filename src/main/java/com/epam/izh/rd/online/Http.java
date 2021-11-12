package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.SimplePokemonFetching;
import com.epam.izh.rd.online.service.SimplePokemonFightingClub;

public class Http {

    public static void main(String[] args) {

        SimplePokemonFightingClub simplePokemonFightingClub = new SimplePokemonFightingClub();

        Pokemon winner = simplePokemonFightingClub.doBattle(new SimplePokemonFetching().fetchByName("pikachu")
                , new SimplePokemonFetching().fetchByName("slowpoke"));

        simplePokemonFightingClub.showWinner(winner);
    }
}
