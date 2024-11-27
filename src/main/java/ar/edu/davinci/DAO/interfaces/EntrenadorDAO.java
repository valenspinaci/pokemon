package ar.edu.davinci.DAO.interfaces;

import ar.edu.davinci.models.Entrenador;

import java.util.List;

public interface EntrenadorDAO {
    //CREATE
    Entrenador create(Entrenador entrenador);

    //READ
    Entrenador getEntrenadorById(int id);
    List<Entrenador> getAll();

    //UPDATE
    Entrenador update(Entrenador entrenador, int id);

    //DELETE
    void delete(int id);
}
