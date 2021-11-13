package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimplePokemonFightingClub implements PokemonFightingClubService {

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        Pokemon tempPokemon;
        List<Pokemon> pokemonList = new ArrayList<>();
        if (p1.getPokemonId() > p2.getPokemonId()) {
            pokemonList.add(p1);
            pokemonList.add(p2);

        } else {
            pokemonList.add(p2);
            pokemonList.add(p1);
        }
        while (true) {
            doDamage(pokemonList.get(0), pokemonList.get(1));

            if (pokemonList.get(1).getHp() <= 0) {
                System.out.println(pokemonList.get(0).getPokemonName() + " победил!");
                return pokemonList.get(0);
            } else {
                tempPokemon = pokemonList.get(0);
                pokemonList.remove(0);
                pokemonList.add(tempPokemon);
            }
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        SimplePokemonFetching pokemonFetchingService = new SimplePokemonFetching();
        byte[] imageBytes = pokemonFetchingService.getPokemonImage(winner.getPokemonName());
        BufferedImage bufferedImage;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes)) {
            bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "png", new File("win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damage = (short) (from.getAttack() - (from.getAttack() * to.getDefense() / 100));
        to.setHp((short) (to.getHp() - damage));
    }
}
