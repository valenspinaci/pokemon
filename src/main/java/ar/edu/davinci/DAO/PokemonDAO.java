package ar.edu.davinci.DAO;

import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;

import java.util.List;

public interface PokemonDAO {
    //CREATE
    Pokemon create(Pokemon pokemon);

    //READ
    List<Pokemon> getAll();

    //UPDATE
    Pokemon update(Pokemon pokemon, int id);

    //DELETE
    void delete(Pokemon pokemon);
}
