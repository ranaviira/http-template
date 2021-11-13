package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.SimpleObjectMapper;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class SimplePokemonFetching implements PokemonFetchingService {

    SimpleObjectMapper simpleObjectMapper = new SimpleObjectMapper();

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {

        Pokemon pokemon;
        String uri = "https://pokeapi.co/api/v2/pokemon/" + name;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(uri).openConnection();
            connection.addRequestProperty("User-agent", "");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonText = bufferedReader.lines().collect(Collectors.joining());

            pokemon = simpleObjectMapper.getObjectMapper().readValue(jsonText, Pokemon.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Покемона с таким именем нет!");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {

        byte[] byteArrayImagePokemon = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(fetchByName(name).getImageUrl());
            connection = (HttpURLConnection) url.openConnection();
            byteArrayImagePokemon = new byte[connection.getContentLength()];
            connection.getInputStream().read(byteArrayImagePokemon);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return byteArrayImagePokemon;
    }
}
