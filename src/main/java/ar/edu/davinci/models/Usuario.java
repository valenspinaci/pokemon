package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AgregarEntrenadorException;
import ar.edu.davinci.exceptions.CapturarPokemonException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String email;
    private String nombre;
    private String apellido;
    private String nickname;
    private int telefono;
    private List<Entrenador> entrenadores;

    public Usuario(String email, String nombre,String apellido,String nickname, int telefono) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.telefono = telefono;
        this.entrenadores = new ArrayList<Entrenador>();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void sumarEntrenador(Entrenador entrenador) throws AgregarEntrenadorException {
        if (entrenadores.size() >= 3) {
            throw new AgregarEntrenadorException("No podes agregar mas de 3 entrenadores");
        }else{
            this.entrenadores.add(entrenador);
        }
    };

    public int contarEntrenadores() {
        return this.entrenadores.size();
    };
}
