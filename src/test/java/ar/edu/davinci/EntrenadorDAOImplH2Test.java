package ar.edu.davinci;

import ar.edu.davinci.DAO.EntrenadorDAOImplH2;
import ar.edu.davinci.DAO.PokemonDAOImplH2;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntrenadorDAOImplH2Test {
    @Test
    @DisplayName("Test utilizado para verificar si la conexion se logra de forma exitosa")
    public void testParaVerificarSiLaConexionSeHaceDeFormaExitosa(){
        try{
            new EntrenadorDAOImplH2();
        }catch(Exception e){
            System.out.println("Error al establecer la conexion: " + e.getMessage());
            assert false: "No se pudo establcer la conexion";
        }
    };

    @Test
    @DisplayName("Cuando uso el metodo para guardar un entrenador en memoria lo hace de forma exitosa")
    public void testParaVerificarQueSePuedeAgregarUnEntrenador(){
        EntrenadorDAOImplH2 conexion = new EntrenadorDAOImplH2();
        Entrenador entrenador = new Entrenador("Ash Ketchum", Date.valueOf("2000-01-01"), "Kanto", "Masculino", 10);

        Entrenador resultado = conexion.create(entrenador);

        assertEquals(entrenador, resultado);
    };

    @Test
    @DisplayName("Verifico si el método para devolver un Entrenador por el ID lo hace de forma correcta")
    public void testParaDevolverQueSePuedeDevolverUnEntrenadorPorId() {
        EntrenadorDAOImplH2 conexion = new EntrenadorDAOImplH2();
        Entrenador entrenador = new Entrenador("Ash Ketchum", Date.valueOf("2000-01-01"), "Kanto", "Masculino", 10);

        Entrenador creado = conexion.create(entrenador);

        Entrenador resultado = conexion.getEntrenadorById(creado.getId());

        assertEquals(creado.getNombre(), resultado.getNombre());
        assertEquals(creado.getFechaNacimiento(), resultado.getFechaNacimiento());
        assertEquals(creado.getNacionalidad(), resultado.getNacionalidad());
        assertEquals(creado.getGenero(), resultado.getGenero());
        assertEquals(creado.getEdad(), resultado.getEdad());
        assertEquals(creado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Cuando uso el metodo para ver el listado de entrenadores me devuelve todos los creados con sus respectivos detalles")
    public void testParaVerificarQueSeMuestranTodosLosEntrenadores(){
        EntrenadorDAOImplH2 conexion = new EntrenadorDAOImplH2();
        Entrenador entrenador = new Entrenador("Ash Ketchum", Date.valueOf("2000-01-01"), "Kanto", "Masculino", 10);
        Entrenador entrenador2 = new Entrenador("Misty", Date.valueOf("1995-05-12"), "Kanto", "Femenino", 22);

        conexion.create(entrenador);
        conexion.create(entrenador2);

        List<Entrenador> resultado = conexion.getAll();

        assertEquals("Ash Ketchum", resultado.get(0).getNombre());
        assertEquals(Date.valueOf("2000-01-01"), resultado.get(0).getFechaNacimiento());
        assertEquals("Kanto", resultado.get(0).getNacionalidad());
        assertEquals("Masculino", resultado.get(0).getGenero());
        assertEquals(10, resultado.get(0).getEdad());

        assertEquals("Misty", resultado.get(1).getNombre());
        assertEquals(Date.valueOf("1995-05-12"), resultado.get(1).getFechaNacimiento());
        assertEquals("Kanto", resultado.get(1).getNacionalidad());
        assertEquals("Femenino", resultado.get(1).getGenero());
        assertEquals(22, resultado.get(1).getEdad());
    }

    @Test
    @DisplayName("Cuando actualizo un entrenador se guarda de forma exitosa con los datos que le pase")
    public void testParaVerificarQueSePuedenActualizarLosEntrenadoresDeFormaExitosa() {
        EntrenadorDAOImplH2 conexion = new EntrenadorDAOImplH2();

        Entrenador entrenador = new Entrenador("Ash Ketchum", Date.valueOf("2000-01-01"), "Kanto", "Masculino", 10);
        Entrenador entrenadorCreado = conexion.create(entrenador);

        Entrenador actualizacion = new Entrenador("Misty", Date.valueOf("1995-05-12"), "Kanto", "Femenino", 22);
        Entrenador entrenadorActualizado = conexion.update(actualizacion, entrenadorCreado.getId());

        assertEquals(actualizacion.getNombre(), entrenadorActualizado.getNombre());
        assertEquals(actualizacion.getFechaNacimiento(), entrenadorActualizado.getFechaNacimiento());
        assertEquals(actualizacion.getNacionalidad(), entrenadorActualizado.getNacionalidad());
        assertEquals(actualizacion.getGenero(), entrenadorActualizado.getGenero());
        assertEquals(actualizacion.getEdad(), entrenadorActualizado.getEdad());
        assertEquals(actualizacion.getId(), entrenadorActualizado.getId());
    }


    @Test
    @DisplayName("Cuando agrego un único entrenador a la lista y luego lo elimino, el largo de la misma es 0")
    public void testParaVerificarQueSePuedenEliminarEntrenadores() {
        EntrenadorDAOImplH2 conexion = new EntrenadorDAOImplH2();
        Entrenador entrenador = new Entrenador("Ash Ketchum", Date.valueOf("2000-01-01"), "Kanto", "Masculino", 10);
        conexion.create(entrenador);

        conexion.delete(entrenador);

        assertEquals(0, conexion.getAll().size());
    }
}
