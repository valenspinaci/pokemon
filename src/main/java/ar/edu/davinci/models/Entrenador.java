package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.CapturarPokemonException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrenador {
    private int id;
    private String nombre;
    private String nacionalidad;
    private String genero;
    private int edad;
    private List<Pokemon> pokemons;

    public Entrenador(String nombre, String nacionalidad, String genero, int edad){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.edad = edad;
        this.pokemons = new ArrayList<Pokemon>();
    };

    public Entrenador(int id, String nombre, String nacionalidad, String genero, int edad){
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.edad = edad;
        this.pokemons = new ArrayList<Pokemon>();
    };

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
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

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public int cantPokemons() {
        return pokemons.size();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void enfrentarseA(Entrenador otroEntrenador) throws AtaqueException {
        System.out.println(this.nombre + " se enfrenta a " + otroEntrenador.getNombre());

        int miIndice = 0;
        int suIndice = 0;
        Arbitro arbitro = new Arbitro();

        while (miIndice < this.cantPokemons() && suIndice < otroEntrenador.cantPokemons()) {
            Pokemon miPokemon = this.pokemons.get(miIndice);
            Pokemon suPokemon = otroEntrenador.getPokemons().get(suIndice);

            System.out.println(miPokemon.getEspecie() + " de " + this.nombre + " enfrenta a " + suPokemon.getEspecie() + " de " + otroEntrenador.getNombre());

            while (miPokemon.getVida() > 0 && suPokemon.getVida() > 0) {
                miPokemon.atacar(suPokemon);
            }

            if (miPokemon.getVida() <= 0) {
                miIndice++;
            }

            if (suPokemon.getVida() <= 0) {
                suIndice++;
            }
        }

        arbitro.verificarGanador(this, otroEntrenador, miIndice);
    }

    public void capturarPokemon(Pokemon pokemon) throws CapturarPokemonException {
        if (pokemons.size() >= 5) {
            throw new CapturarPokemonException("No podes capturar más de 5 Pokemones");
        }else{
            this.pokemons.add(pokemon);
        }
    }
}