package ar.edu.davinci;

import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EntrenadorTest {

    @Test
    @DisplayName("Cuando un entrenador captura un pokemon, el largo de la lista de pokemones pasa a ser 1")
    public void testCuandoUnEntrenadorCapturaUnPokemonLaListaPasaATenerUnLargoDeUno() {
        Entrenador entrenador = new Entrenador("Ash", new Date(), "Kanto", "Masculino", 10);
        Pokemon pokemon = new Pokemon("Agua", "Squirtle", 20, 50);

        entrenador.capturarPokemon(pokemon);

        assertEquals(1, entrenador.getPokemons().size());
    }

    @Test
    @DisplayName("Cuando un entrenador captura dos pokemones, el largo de la lista de pokemones pasa a ser 2")
    public void testCuandoUnEntrenadorCapturaDosPokemonesLaListaPasaATenerUnLargoDeDos() {
        Entrenador entrenador = new Entrenador("Ash", new Date(), "Kanto", "Masculino", 10);
        Pokemon pokemon = new Pokemon("Agua", "Squirtle", 20, 50);
        Pokemon pokemon2 = new Pokemon("Fuego", "Charmander", 25, 50);

        entrenador.capturarPokemon(pokemon);
        entrenador.capturarPokemon(pokemon2);

        assertEquals(2, entrenador.getPokemons().size());
    }

    @Test
    @DisplayName("Cuando un entrenador intenta capturar mas de 5 pokemones, el largo de la lista es cinco")
    public void testCuandoUnEntrenadorCapturaMasDeCincoPokemonesLaListaSigueTeniendoUnLargoDeCinco() {
        Entrenador entrenador = new Entrenador("Ash", new Date(), "Kanto", "Masculino", 10);

        for (int i = 0; i <= 10; i++) {
            entrenador.capturarPokemon(new Pokemon("Agua", "Squirtle" + i, 20, 50));
        }

        assertEquals(5, entrenador.getPokemons().size());
    }

    @Test
    @DisplayName("Cuando se realiza un enfrentamiento entre dos entrenadores, la vida de sus pokemones se ve reducida")
    public void testCuandoSeEnfrentanDosEntrenadoresSusPokemonesPierdenVida(){
        Entrenador entrenador = new Entrenador("Ash", new Date(), "Kanto", "Masculino", 10);
        Entrenador entrenador2 = new Entrenador("Misty", new Date(), "Kanto", "Femenino", 10);
        Pokemon pokemon = new Pokemon("Agua", "Squirtle", 20, 50);
        Pokemon pokemon2 = new Pokemon("Fuego", "Charmander", 25, 50);

        entrenador.capturarPokemon(pokemon);
        entrenador2.capturarPokemon(pokemon2);

        entrenador.enfrentarseA(entrenador2);

        assertTrue(pokemon.getVida() <= 100);
        assertTrue(pokemon2.getVida() <= 100);
    }
}
