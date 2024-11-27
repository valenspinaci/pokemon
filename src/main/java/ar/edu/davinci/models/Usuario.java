package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AgregarEntrenadorException;
import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.SeleccionarEntrenadorException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String email;
    private String nombre;
    private String apellido;
    private String nickname;
    private String contrasena;
    private int telefono;
    private List<Entrenador> entrenadores;

    public Usuario(String email, String nombre,String apellido,String nickname, String contrasena, int telefono) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.entrenadores = new ArrayList<Entrenador>();
    }

    public Usuario(int id, String email, String nombre,String apellido,String nickname, String contrasena, int telefono) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.contrasena = contrasena;
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

    public String getContrasena() {
        return contrasena;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
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

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public void sumarEntrenador(Entrenador entrenador) throws AgregarEntrenadorException {
        if (entrenadores.size() >= 3) {
            throw new AgregarEntrenadorException("No podes agregar mas de 3 entrenadores");
        }else{
            this.entrenadores.add(entrenador);
        }
    };

    public Usuario buscarRival() throws SeleccionarEntrenadorException {
        Partida partida = new Partida();
        return partida.buscarRival(this.getId());
    }

    public Entrenador seleccionarEntrenador(int id){
        Entrenador entrenadorSeleccionado = null;
        for (Entrenador entrenador:entrenadores){
            if (entrenador.getId() == id){
                entrenadorSeleccionado = entrenador;
            }
        }
        return entrenadorSeleccionado;
    }

    public Entrenador enfrentarseA(Entrenador entrenadorUsuarioRival) throws AtaqueException, SeleccionarEntrenadorException {
        return this.seleccionarEntrenador(this.getId()).enfrentarseA(entrenadorUsuarioRival);
    }
}
