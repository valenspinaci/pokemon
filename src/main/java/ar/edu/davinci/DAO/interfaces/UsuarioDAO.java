package ar.edu.davinci.DAO.interfaces;

import ar.edu.davinci.models.Usuario;

import java.util.List;

public interface UsuarioDAO {
    //CREATE
    Usuario create(Usuario usuario);

    //READ
    Usuario getUsuarioById(int id);
    List<Usuario> getAll();

    //UPDATE
    Usuario update(Usuario usuario, int id);

    //DELETE
    void delete(int id);
}
