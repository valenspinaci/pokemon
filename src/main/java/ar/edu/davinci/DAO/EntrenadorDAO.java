package ar.edu.davinci.DAO;

import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;

import java.util.List;

public interface EntrenadorDAO {
    //CREATE
    void create(Entrenador entrenador);

    //READ
    List<Entrenador> getAll();

    //UPDATE
    void update(Entrenador entrenador);

    //DELETE
    void delete(Entrenador entrenador);
}
