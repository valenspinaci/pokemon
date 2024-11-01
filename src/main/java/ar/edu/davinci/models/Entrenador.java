package ar.edu.davinci.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrenador {
    private String nombre;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String genero;
    private int edad;
    private List<Pokemon> pokemons;

    public Entrenador(String nombre, Date fechaNacimiento, String nacionalidad, String genero, int edad){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.edad = edad;
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
    };

    //Getters & Setters
    public String getNombre() {
        return nombre;
    };

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    };

    public String getNacionalidad() {
        return nacionalidad;
    };

    public String getGenero() {
        return genero;
    };

    public int getEdad() {
        return edad;
    };

    public void enfrentarseA(Entrenador otroEntrenador){
        ////////////////////////////////////////////////////////
    };

    public void capturarPokemon(Pokemon pokemon){
        ///////////////////////////////////////////////////////
    };
}
