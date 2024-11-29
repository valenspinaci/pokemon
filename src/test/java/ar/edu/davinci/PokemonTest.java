package ar.edu.davinci;

import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.VidaException;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.tipos.Tipo;
import ar.edu.davinci.models.tipos.Agua;
import ar.edu.davinci.models.tipos.Fuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PokemonTest {
    Tipo fuego;
    Tipo agua;
    Pokemon pokemon;
    Pokemon pokemon2;

    @BeforeEach
    public void setUp(){
        fuego = new Fuego();
        agua = new Agua();
        pokemon = new Pokemon(fuego, "Charizard", 20F, 70F, 40F, 1);
        pokemon2 = new Pokemon(agua, "Squirtle", 15F, 80F, 25F, 1);
    }

    @Test
    @DisplayName("Un pokemon resta su vida 50 puntos y pasa a contar con 50 puntos de vida")
    public void testParaVerificarElRestoDeVida() throws VidaException {
        pokemon.restarVida(50F);

        Float resultado = pokemon.getVida();

        assertEquals(50, resultado, 0);
    }

    @Test
    @DisplayName("Un pokemon resta su vida 120 puntos y pasa a contar con 0 puntos de vida")
    public void testParaVerificarElRestoExcesivoDeVida() throws VidaException {
        pokemon.restarVida(120F);

        Float resultado = pokemon.getVida();

        assertEquals(0, resultado, 0);
    }

    @Test
    @DisplayName("Controlar que cuando un pokemon ataca se le resta energia al mismo y vida al oponente")
    public void testParaVerificarEficienciaDeAtaque() throws AtaqueException, VidaException {
        pokemon.atacar(pokemon2);

        assertEquals(50, pokemon.getEnergia(), 0);
        assertEquals(60, pokemon2.getVida(), 0);
    }

    @Test
    @DisplayName("Un pokemon aumenta su vida 50 puntos y sigue contando con 100 puntos")
    public void testParaVerificarElAumentoExcesivoDeVida() throws VidaException {
        pokemon.aumentarVida(50F);

        Float resultado = pokemon.getVida();

        assertEquals(100, resultado, 0);
    }

    @Test
    @DisplayName("Un pokemon resta su vida 30 puntos, aumenta en 10 y pasa a contar con 80 puntos de vida")
    public void testParaVerificarElAumentoDeVida() throws VidaException {
        pokemon.restarVida(30F);
        pokemon.aumentarVida(10F);

        Float resultado = pokemon.getVida();

        assertEquals(80, resultado, 0);
    }
}
