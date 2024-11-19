package ar.edu.davinci;

import ar.edu.davinci.DAO.implementacion.PokemonDAOImplH2;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.tipos.Tipo;
import ar.edu.davinci.models.tipos.Agua;
import ar.edu.davinci.models.tipos.Fuego;
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
        Tipo tipo = new Fuego();
        Pokemon pokemon = new Pokemon(tipo, "Charizard", 20F, 70F, 40F);

        Pokemon resultado = conexion.create(pokemon);

        assertEquals(pokemon, resultado);

    };

    @Test
    @DisplayName("Verifico si el método para devolver un Pokémon por el ID lo hace de forma correcta")
    public void testParaDevolverQueSePuedeDevolverUnPokemonPorId() {
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Tipo tipo = new Fuego();
        Pokemon pokemon = new Pokemon(tipo, "Charizard", 20F, 70F, 40F);

        Pokemon creado = conexion.create(pokemon);

        Pokemon resultado = conexion.getPokemonById(creado.getId());

        assertEquals(creado.getEspecie(), resultado.getEspecie());
        assertEquals(creado.getPoder(), resultado.getPoder());
        assertEquals(creado.getEnergia(), resultado.getEnergia());
        assertEquals(creado.getVida(), resultado.getVida());
        assertEquals(creado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Cuando uso el metodo para ver el listado de pokemones me devuelve todos los creados con sus respectivos detalles")
    public void testParaVerificarQueSeMuestranTodosLosPokemones(){
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Tipo fuego = new Fuego();
        Tipo agua = new Agua();
        Pokemon pokemon = new Pokemon(fuego, "Charizard", 20F, 70F, 40F);
        Pokemon pokemon2 = new Pokemon(agua, "Squirtle", 30F, 60F, 25F);

        conexion.create(pokemon);
        conexion.create(pokemon2);

        List<Pokemon> resultado = conexion.getAll();

        assertEquals(2, resultado.size());
        assertEquals("Charizard", resultado.get(0).getEspecie());
        assertEquals(20, resultado.get(0).getPoder(),0);
        assertEquals(70, resultado.get(0).getEnergia(), 0);
        assertEquals(100, resultado.get(0).getVida(), 0);

        assertEquals("Squirtle", resultado.get(1).getEspecie());
        assertEquals(30, resultado.get(1).getPoder(), 0);
        assertEquals(60, resultado.get(1).getEnergia(), 0);
        assertEquals(100, resultado.get(1).getVida(), 0);
    }

    @Test
    @DisplayName("Cuando actualizo un pokemon se guarda de forma exitosa con los datos que le pase")
    public void testParaVerificarQueSePuedenActualizarLosPokemonesDeFormaExitosa() {
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Tipo fuego = new Fuego();
        Tipo agua = new Agua();

        Pokemon pokemon = new Pokemon(fuego, "Charizard", 20F, 70F, 40F);
        Pokemon pokemonCreado = conexion.create(pokemon);

        Pokemon actualizacion = new Pokemon(agua, "Squirtle", 30F, 60F, 25F);
        Pokemon pokemonActualizado = conexion.update(actualizacion, pokemonCreado.getId());

        assertEquals(actualizacion.getTipo(), pokemonActualizado.getTipo());
        assertEquals(actualizacion.getEspecie(), pokemonActualizado.getEspecie());
        assertEquals(actualizacion.getPoder(), pokemonActualizado.getPoder());
        assertEquals(actualizacion.getEnergia(), pokemonActualizado.getEnergia());
    }


    @Test
    @DisplayName("Cuando agrego un único Pokémon a la lista y luego lo elimino, el largo de la misma es 0")
    public void testParaVerificarQueSePuedenEliminarPokemones() {
        PokemonDAOImplH2 conexion = new PokemonDAOImplH2();
        Tipo fuego = new Fuego();
        Pokemon pokemon = new Pokemon(fuego, "Charizard", 20F, 70F, 40F);
        conexion.create(pokemon);

        conexion.delete(pokemon);

        assertEquals(0, conexion.getAll().size());
    }
}
