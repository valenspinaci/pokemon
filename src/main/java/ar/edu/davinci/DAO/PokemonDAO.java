package ar.edu.davinci.DAO;

import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;

import java.util.List;

public interface PokemonDAO {
    //CREATE
    void create(Pokemon pokemon);

    //READ
    List<Pokemon> getAll();

    //UPDATE
    void update(Pokemon pokemon);

    //DELETE
    void delete(Pokemon pokemon);
}
