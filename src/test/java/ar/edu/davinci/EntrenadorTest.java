package ar.edu.davinci;

import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.CapturarPokemonException;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.tipos.Tipo;
import ar.edu.davinci.models.tipos.Agua;
import ar.edu.davinci.models.tipos.Fuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class EntrenadorTest {
    Entrenador entrenador;
    Entrenador entrenador2;
    Tipo fuego;
    Tipo agua;
    Pokemon pokemon;
    Pokemon pokemon2;

    @BeforeEach
    public void setUp(){
        entrenador = new Entrenador("Ash","Kanto", "Masculino", 10, 1);
        entrenador2 = new Entrenador("Misty", "Kanto", "Femenino", 10, 1);
        fuego = new Fuego();
        agua = new Agua();
        pokemon = new Pokemon(agua, "Squirtle", 20F, 50F, 25F, 1);
        pokemon2 = new Pokemon(fuego, "Charmander", 25F, 50F, 40F, 1);
    }

    @Test
    @DisplayName("Cuando un entrenador captura un pokemon que cuenta con vida 0, el largo de la lista de pokemones pasa a ser 1")
    public void testCuandoUnEntrenadorCapturaUnPokemonLaListaPasaATenerUnLargoDeUno() throws CapturarPokemonException {
        pokemon.restarVida(100F);
        entrenador.capturarPokemon(pokemon);

        assertEquals(1, entrenador.getPokemons().size());
    }

    @Test
    @DisplayName("Cuando un entrenador intenta capturar mas de 5 pokemones, el largo de la lista es cinco")
    public void testCuandoUnEntrenadorCapturaMasDeCincoPokemonesLaListaSigueTeniendoUnLargoDeCinco() throws CapturarPokemonException {
        for (int i = 0; i <= 4; i++) {
            entrenador.capturarPokemon(new Pokemon(agua, "Squirtle" + i, 20F, 50F, 25F, 1));
        }
        assertThrows(CapturarPokemonException.class, () -> entrenador.capturarPokemon(pokemon));
    }

    @Test
    @DisplayName("Cuando se realiza un enfrentamiento entre dos entrenadores, la vida de sus pokemones se ve reducida")
    public void testCuandoSeEnfrentanDosEntrenadoresSusPokemonesPierdenVida() throws AtaqueException {
        entrenador.enfrentarseA(entrenador2);

        assertTrue(pokemon.getVida() <= 100);
        assertTrue(pokemon2.getVida() <= 100);
    }
}