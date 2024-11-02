package ar.edu.davinci;

import ar.edu.davinci.models.Pokemon;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class PokemonTest {
    @Test
    @DisplayName("Un pokemon resta su vida 50 puntos y pasa a contar con 50 puntos de vida")
    public void testParaVerificarElRestoDeVida(){
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);

        pokemon.restarVida(50);

        int resultado = pokemon.getVida();

        assertEquals(50, resultado);
    }

    @Test
    @DisplayName("Un pokemon resta su vida 120 puntos y pasa a contar con 0 puntos de vida")
    public void testParaVerificarElRestoExcesivoDeVida(){
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);

        pokemon.restarVida(120);

        int resultado = pokemon.getVida();

        assertEquals(0, resultado);
    }

    @Test
    @DisplayName("Controlar que cuando un pokemon ataca se le resta energia al mismo y vida al oponente")
    public void testParaVerificarEficienciaDeAtaque(){
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);
        Pokemon oponente = new Pokemon("Agua", "Squirtle", 15, 60);

        pokemon.atacar(oponente);

        assertEquals(60, pokemon.getEnergia());
        assertEquals(80, oponente.getVida());
    }

    @Test
    @DisplayName("Un pokemon aumenta su vida 50 puntos y sigue contando con 100 puntos")
    public void testParaVerificarElAumentoExcesivoDeVida(){
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);

        pokemon.aumentarVida(50);

        int resultado = pokemon.getVida();

        assertEquals(100, resultado);
    }

    @Test
    @DisplayName("Un pokemon resta su vida 30 puntos, aumenta en 10 y pasa a contar con 80 puntos de vida")
    public void testParaVerificarElAumentoDeVida(){
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);

        pokemon.restarVida(30);
        pokemon.aumentarVida(10);

        int resultado = pokemon.getVida();

        assertEquals(80, resultado);
    }
}
