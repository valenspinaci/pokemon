package ar.edu.davinci;

import ar.edu.davinci.exceptions.AgregarEntrenadorException;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.Usuario;
import ar.edu.davinci.models.tipos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {
    Usuario usuario;
    Usuario usuario2;
    Tipo agua;
    Tipo fuego;
    Tipo electrico;
    Tipo planta;
    Entrenador entrenador;
    Entrenador entrenador2;
    Entrenador entrenador3;
    Entrenador entrenador4;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("juanperez@gmail.com", "Juan", "Perez", "jp123", 1112345678);
        usuario2 = new Usuario("analopez@gmail.com", "Ana", "Lopez", "al.al.123", 1198765432);
        agua = new Agua();
        fuego = new Fuego();
        electrico = new Electrico();
        planta = new Planta();
        entrenador = new Entrenador("Ash", new Date(), "Kanto", "Masculino", 10);
        entrenador2 = new Entrenador("Misty", new Date(), "Kanto", "Femenino", 10);
        entrenador3 = new Entrenador("Brock", new Date(), "Kanto", "Masculino", 15);
        entrenador4 = new Entrenador("Serena", new Date(), "Kalos", "Femenino", 12);
    }

    @Test
    @DisplayName("Cuando un usuario suma un entrenador el largo de la lista pasa a ser 1")
    public void testCuandoUsuarioSumaUnEntrenadorElLargoDeLaListaPasaASer1() throws AgregarEntrenadorException {
        usuario.sumarEntrenador(entrenador);

        assertEquals(1, usuario.contarEntrenadores());
    }

    @Test
    @DisplayName("Cuando intento agregar mas de tres entrenadores a la lista lanza una excepcion")
    public void testCuandoIntentoAgregarMasDeTresEntrenadores() throws AgregarEntrenadorException {
        usuario.sumarEntrenador(entrenador);
        usuario.sumarEntrenador(entrenador2);
        usuario.sumarEntrenador(entrenador3);

        assertThrows(AgregarEntrenadorException.class, () -> {usuario.sumarEntrenador(entrenador4);});
    }
}
