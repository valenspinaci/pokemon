package ar.edu.davinci.DAO.interfaces;

import ar.edu.davinci.models.Pokemon;

import java.util.List;

public interface PokemonDAO {
    //CREATE
    Pokemon create(Pokemon pokemon);

    //READ
    Pokemon getPokemonById(int id);
    List<Pokemon> getAll();

    //UPDATE
    Pokemon update(Pokemon pokemon, int id);

    //DELETE
    void delete(Pokemon pokemon);
}
