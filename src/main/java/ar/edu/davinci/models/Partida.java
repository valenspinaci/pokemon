package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.SeleccionarEntrenadorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    private List<Usuario> usuarios;

    public Partida() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void sumarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public Usuario buscarRival(int idBuscando) throws SeleccionarEntrenadorException {
        List<Usuario> posiblesRivales = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() != idBuscando) {
                posiblesRivales.add(usuario);
            }
        }

        if (posiblesRivales.isEmpty()) {
            throw new SeleccionarEntrenadorException("No se encontró un rival disponible.");
        }

        Random random = new Random();
        return posiblesRivales.get(random.nextInt(posiblesRivales.size()));
    }
}
