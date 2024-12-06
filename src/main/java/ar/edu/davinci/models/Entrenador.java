package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.CapturarPokemonException;
import ar.edu.davinci.exceptions.VidaException;
import ar.edu.davinci.ui.utils.BatallaLogger;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    private int id;
    private String nombre;
    private String nacionalidad;
    private String genero;
    private int edad;
    private int idUsuario;
    private List<Pokemon> pokemons;
    private static BatallaLogger batallaLogger;

    public Entrenador(){};

    public Entrenador(String nombre, String nacionalidad, String genero, int edad, int idUsuario) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.edad = edad;
        this.idUsuario = idUsuario;
        this.pokemons = new ArrayList<Pokemon>();
    };

    public Entrenador(int id, String nombre, String nacionalidad, String genero, int edad, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.edad = edad;
        this.idUsuario = idUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

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

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void setBatallaLogger(BatallaLogger batallaLogger) {
        this.batallaLogger = batallaLogger;
    }

    public Entrenador enfrentarseA(Entrenador otroEntrenador) throws AtaqueException, VidaException {
        batallaLogger.mostrarMensaje(this.nombre.substring(0, 1).toUpperCase() + this.nombre.substring(1).toLowerCase() + " se enfrenta a " + otroEntrenador.getNombre().substring(0, 1).toUpperCase() + otroEntrenador.getNombre().substring(1).toLowerCase());
        System.out.println(this.nombre.substring(0, 1).toUpperCase() + this.nombre.substring(1).toLowerCase() + " se enfrenta a " + otroEntrenador.getNombre().substring(0, 1).toUpperCase() + otroEntrenador.getNombre().substring(1).toLowerCase());

        int miIndice = 0;
        int suIndice = 0;
        Arbitro arbitro = new Arbitro();

        while (miIndice < this.cantPokemons() && suIndice < otroEntrenador.cantPokemons()) {
            Pokemon miPokemon = this.getPokemons().get(miIndice);
            Pokemon suPokemon = otroEntrenador.getPokemons().get(suIndice);

            batallaLogger.mostrarMensaje(miPokemon.getEspecie() + " de " + this.nombre.substring(0, 1).toUpperCase() + this.nombre.substring(1).toLowerCase() + " enfrenta a " + suPokemon.getEspecie() + " de " + otroEntrenador.getNombre().substring(0, 1).toUpperCase() + otroEntrenador.getNombre().substring(1).toLowerCase());
            System.out.println(miPokemon.getEspecie() + " de " + this.nombre.substring(0, 1).toUpperCase() + this.nombre.substring(1).toLowerCase() + " enfrenta a " + suPokemon.getEspecie() + " de " + otroEntrenador.getNombre().substring(0, 1).toUpperCase() + otroEntrenador.getNombre().substring(1).toLowerCase());

            while (miPokemon.getVida() > 0 && suPokemon.getVida() > 0) {
                try{
                    miPokemon.atacar(suPokemon);
                }catch (AtaqueException | VidaException e) {
                    e.printStackTrace();
                }

            }

            if (miPokemon.getVida() <= 0) {
                miIndice++;
            }

            if (suPokemon.getVida() <= 0) {
                suIndice++;
            }
        }

        Entrenador ganador = arbitro.verificarGanador(this, otroEntrenador, miIndice);

        batallaLogger.mostrarMensaje("¡El ganador es " + ganador.getNombre().substring(0, 1).toUpperCase() + ganador.getNombre().substring(1).toLowerCase() + "!");

        return ganador;
    }

    public void capturarPokemon(Pokemon pokemon) throws CapturarPokemonException {
        if (pokemons.size() >= 5) {
            throw new CapturarPokemonException("No podes capturar más de 5 Pokemones");
        }
        this.pokemons.add(pokemon);
    }
}