package ar.edu.davinci;

import ar.edu.davinci.exceptions.AgregarEntrenadorException;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Usuario;
import ar.edu.davinci.models.tipos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {
    private Usuario usuario;
    private Usuario usuario2;
    private Tipo agua;
    private Tipo fuego;
    private Tipo electrico;
    private Tipo planta;
    private Entrenador entrenador;
    private Entrenador entrenador2;
    private Entrenador entrenador3;
    private Entrenador entrenador4;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("juanperez@gmail.com", "Juan", "Perez", "jp123","123", 1112345678);
        usuario2 = new Usuario("analopez@gmail.com", "Ana", "Lopez", "al.al.123","123", 1198765432);
        agua = new Agua();
        fuego = new Fuego();
        electrico = new Electrico();
        planta = new Planta();
        entrenador = new Entrenador("Ash", "Kanto", "Masculino", 10, 1);
        entrenador2 = new Entrenador("Misty", "Kanto", "Femenino", 10, 1);
        entrenador3 = new Entrenador("Brock", "Kanto", "Masculino", 15, 1);
        entrenador4 = new Entrenador("Serena", "Kalos", "Femenino", 12, 1);
    }

    @Test
    @DisplayName("Cuando un usuario suma un entrenador el largo de la lista pasa a ser 1")
    public void testCuandoUsuarioSumaUnEntrenadorElLargoDeLaListaPasaASer1() throws AgregarEntrenadorException {
        usuario.sumarEntrenador(entrenador);

        assertEquals(1, usuario.getEntrenadores().size());
    }

    @Test
    @DisplayName("Cuando agrego otro usuario el largo de la lista pasa a ser 2")
    public void testCuandoUnUsuarioAgregaDosEntrenadoresElLargoDeLaListaPasaASer2() throws AgregarEntrenadorException {
        usuario.sumarEntrenador(entrenador);
        usuario.sumarEntrenador(entrenador2);

        assertEquals(2, usuario.getEntrenadores().size());
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
