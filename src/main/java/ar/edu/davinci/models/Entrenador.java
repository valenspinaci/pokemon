package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.CapturarPokemonException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrenador {
    private int id;
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
        this.pokemons = new ArrayList<Pokemon>();
    };

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    };

    public java.sql.Date getFechaNacimiento() {
        return (java.sql.Date) fechaNacimiento;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public void enfrentarseA(Entrenador otroEntrenador) {
        System.out.println(this.nombre + " se enfrenta a " + otroEntrenador.getNombre());

        int miIndice = 0;
        int suIndice = 0;

        //Pelean mientras ambos entrenadores tengan pokemones vivos
        while (miIndice < this.pokemons.size() && suIndice < otroEntrenador.getPokemons().size()) {
            Pokemon miPokemon = this.pokemons.get(miIndice);
            Pokemon suPokemon = otroEntrenador.getPokemons().get(suIndice);

            System.out.println(miPokemon.getEspecie() + " de " + this.nombre + " enfrenta a " + suPokemon.getEspecie() + " de " + otroEntrenador.getNombre());

            //Pelean hasta que uno de los pokemones se queda sin vida
            while (miPokemon.getVida() > 0 && suPokemon.getVida() > 0) {
                //Mi Pokemon ataca
                miPokemon.atacar(suPokemon);
                System.out.println(miPokemon.getEspecie() + " ataca! " + suPokemon.getEspecie() + " queda con " + suPokemon.getVida() + " vida.");

                if (suPokemon.getVida() <= 0) {
                    System.out.println(suPokemon.getEspecie() + " ha sido derrotado!");
                    break;
                }

                //Su Pokemon ataca
                suPokemon.atacar(miPokemon);
                System.out.println(suPokemon.getEspecie() + " ataca! " + miPokemon.getEspecie() + " queda con " + miPokemon.getVida() + " vida.");

                if (miPokemon.getVida() <= 0) {
                    System.out.println(miPokemon.getEspecie() + " ha sido derrotado!");
                    break;
                }
            }

            //Si el Pokémon mio pierde, paso al siguiente pokemon
            if (miPokemon.getVida() <= 0) {
                miIndice++;
            }

            //Si el pokemon de mi oponente fue derrotado, paso al siguiente pokemon
            if (suPokemon.getVida() <= 0) {
                suIndice++;
            }
        }

        if (miIndice < this.pokemons.size()) {
            System.out.println(this.nombre + " gana la pelea!");
        } else{
            System.out.println(otroEntrenador.getNombre() + " gana la pelea!");
        }
    }

    public void capturarPokemon(Pokemon pokemon) {
        try {
            if (pokemon == null) {
                throw new CapturarPokemonException("El Pokemon no existe");
            }
            if (pokemon.getVida() > 0) {
                throw new CapturarPokemonException("El Pokemon debe tener vida igual a 0 para ser capturado");
            }
            if (pokemons.size() >= 5) {
                throw new CapturarPokemonException("No podes capturar más de 5 Pokemones");
            }

            this.pokemons.add(pokemon);

        } catch (CapturarPokemonException e) {
            System.out.println("Error al capturar Pokemon: " + e.getMessage());
        }
    }
}