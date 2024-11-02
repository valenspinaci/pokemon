package ar.edu.davinci;

import ar.edu.davinci.DAO.PokemonDAOImplH2;
import ar.edu.davinci.models.Pokemon;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokemonDAOImplH2Test {

    @Test
    @DisplayName("Test utilizado para verificar si la conexion se logra de forma exitosa")
    public void testParaVerificarSiLaConexionSeHaceDeFormaExitosa(){
        try{
            new PokemonDAOImplH2();
        }catch(Exception e){
            System.out.println("Error al establecer la conexion: " + e.getMessage());
            assert false: "No se pudo establcer la conexion";
        }
    };

    @Test
    @DisplayName("Cuando uso el metodo para guardar un pokemon en memoria lo hace de forma exitosa")
    public void testParaVerificarQueSePuedeAgregarUnPokemon(){
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);

        Pokemon resultado = conexion.create(pokemon);

        assertEquals(pokemon, resultado);

    };

    @Test
    @DisplayName("Cuando uso el metodo para ver el listado de pokemones me devuelve todos los creados con sus respectivos detalles")
    public void testParaVerificarQueSeMuestranTodosLosPokemones(){
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);
        Pokemon pokemon2 = new Pokemon("Agua", "Squirtle", 30, 60);

        conexion.create(pokemon);
        conexion.create(pokemon2);

        List<Pokemon> resultado = conexion.getAll();

        assertEquals(2, resultado.size());
        assertEquals("Fuego", resultado.get(0).getTipo());
        assertEquals("Charizard", resultado.get(0).getEspecie());
        assertEquals(20, resultado.get(0).getPoder());
        assertEquals(70, resultado.get(0).getEnergia());
        assertEquals(100, resultado.get(0).getVida());

        assertEquals("Agua", resultado.get(1).getTipo());
        assertEquals("Squirtle", resultado.get(1).getEspecie());
        assertEquals(30, resultado.get(1).getPoder());
        assertEquals(60, resultado.get(1).getEnergia());
        assertEquals(100, resultado.get(1).getVida());
    }

    @Test
    @DisplayName("Cuando actualizo un pokemon se guarda de forma exitosa con los datos que le pase")
    public void testParaVerificarQueSePuedenActualizarLosPokemonesDeFormaExitosa(){
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);
        Pokemon pokemonAActualizar = conexion.create(pokemon);
        Pokemon pokemonActualizado = conexion.update(pokemon, 0);

        assertEquals(pokemon, pokemonActualizado);
    }

    @Test
    @DisplayName("Cuando agrego un único Pokémon a la lista y luego lo elimino, el largo de la misma es 0")
    public void testParaVerificarQueSePuedenEliminarPokemones() {
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Pokemon pokemon = new Pokemon("Fuego", "Charizard", 20, 70);
        conexion.create(pokemon);

        conexion.delete(pokemon);

        assertEquals(0, conexion.getAll().size());
    }
}
