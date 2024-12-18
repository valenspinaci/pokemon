package ar.edu.davinci;

import ar.edu.davinci.DAO.implementacion.EntrenadorDAOImplH2;
import ar.edu.davinci.models.Entrenador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EntrenadorDAOImplH2Test {
    EntrenadorDAOImplH2 conexion;
    Entrenador entrenador;
    Entrenador entrenador2;

    @BeforeEach
    public void setUp(){
        conexion = new EntrenadorDAOImplH2();
        entrenador = new Entrenador("Ash Ketchum", "Kanto", "Masculino", 10, 1);
        entrenador2 = new Entrenador("Misty", "Kanto", "Femenino", 22, 1);
    }

    @Test
    @DisplayName("Cuando uso el metodo para guardar un entrenador en memoria lo hace de forma exitosa")
    public void testParaVerificarQueSePuedeAgregarUnEntrenador(){
        Entrenador resultado = conexion.create(entrenador);

        assertEquals(entrenador, resultado);
    };

    @Test
    @DisplayName("Verifico si el método para devolver un Entrenador por el ID lo hace de forma correcta")
    public void testParaDevolverQueSePuedeDevolverUnEntrenadorPorId() {
        Entrenador creado = conexion.create(entrenador);

        Entrenador resultado = conexion.getEntrenadorById(creado.getId());

        assertEquals(creado.getNombre(), resultado.getNombre());
        assertEquals(creado.getNacionalidad(), resultado.getNacionalidad());
        assertEquals(creado.getGenero(), resultado.getGenero());
        assertEquals(creado.getEdad(), resultado.getEdad());
        assertEquals(creado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Cuando uso el metodo para ver el listado de entrenadores me devuelve todos los creados con sus respectivos detalles")
    public void testParaVerificarQueSeMuestranTodosLosEntrenadores(){
        conexion.create(entrenador);
        conexion.create(entrenador2);

        List<Entrenador> resultado = conexion.getAll();

        assertEquals("Ash Ketchum", resultado.get(0).getNombre());
        assertEquals("Kanto", resultado.get(0).getNacionalidad());
        assertEquals("Masculino", resultado.get(0).getGenero());
        assertEquals(10, resultado.get(0).getEdad());

        assertEquals("Misty", resultado.get(1).getNombre());
        assertEquals("Kanto", resultado.get(1).getNacionalidad());
        assertEquals("Femenino", resultado.get(1).getGenero());
        assertEquals(22, resultado.get(1).getEdad());
    }

    @Test
    @DisplayName("Cuando actualizo un entrenador se guarda de forma exitosa con los datos que le pase")
    public void testParaVerificarQueSePuedenActualizarLosEntrenadoresDeFormaExitosa() {
        Entrenador entrenadorCreado = conexion.create(entrenador);

        Entrenador actualizacion = new Entrenador("Misty", "Kanto", "Femenino", 22, 1);
        Entrenador entrenadorActualizado = conexion.update(actualizacion, entrenadorCreado.getId());

        assertEquals(actualizacion.getNombre(), entrenadorActualizado.getNombre());
        assertEquals(actualizacion.getNacionalidad(), entrenadorActualizado.getNacionalidad());
        assertEquals(actualizacion.getGenero(), entrenadorActualizado.getGenero());
        assertEquals(actualizacion.getEdad(), entrenadorActualizado.getEdad());
        assertEquals(actualizacion.getId(), entrenadorActualizado.getId());
    }

    @Test
    @DisplayName("Cuando agrego entrenadores con un mismo usuario a la lista y quiero obtenerlos por el id de usuario lo hace de forma correcta")
    public void testCuandoAgregoEntrenadoresConUnMismoUsuarioPuedoObtenerlosPorElIDDelMismo(){
        conexion.create(entrenador);
        conexion.create(entrenador2);

        assertEquals(2, conexion.getEntrenadoresByUsuario(1).size());
    }


    @Test
    @DisplayName("Cuando agrego un único entrenador a la lista y luego lo elimino, el largo de la misma es 0")
    public void testParaVerificarQueSePuedenEliminarEntrenadores() {
        conexion.create(entrenador);

        conexion.delete(1);

        assertEquals(0, conexion.getAll().size());
    }
}