package ar.edu.davinci;

import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.exceptions.SeleccionarEntrenadorException;
import ar.edu.davinci.models.Partida;
import ar.edu.davinci.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartidaTest {
    Partida partida;
    Usuario usuario1, usuario2;
    UsuarioDAOImplH2 usuarioDAO = new UsuarioDAOImplH2();

    @BeforeEach
    public void setUp(){
        partida = new Partida();
        usuario1 = new Usuario("jp@gmail.com", "Juan", "Perez", "jp123", "123", 1112345678);
        usuario2 = new Usuario("ml@gmail.com", "Marcos", "Lopez", "ml123","123", 1112345678);
    }

    @Test
    @DisplayName("Cuando sumo dos usuarios la lista pasa a tener un largo de 2")
    public void testCuandoSumoDosUsuariosLaListaPasaATenerUnLargoDe2(){
        partida.sumarUsuario(usuario1);
        partida.sumarUsuario(usuario2);

        assertEquals(2, partida.getUsuarios().size());
    }

    @Test
    @DisplayName("Cuando cuento con un solo usuario y busco un rival el sistema lanza una excepcion")
    public void testCuandoHayUnSoloUsuarioElSistemaLanzaUnaExcepcion() throws SeleccionarEntrenadorException {
        partida.sumarUsuario(usuario1);

        assertThrows(SeleccionarEntrenadorException.class, () -> partida.buscarRival(usuario1.getId()));
    }

    @Test
    @DisplayName("Cuando cuento con dos usuarios y busco un rival el sistema me devuelve al otro usuario")
    public void testCuandoTengoDosUsuariosYBuscoUnRivalElSistemaMeDevuelveAlOtroUsuario() throws SeleccionarEntrenadorException {
        usuario1.setId(1);
        usuario2.setId(2);
        partida.sumarUsuario(usuario1);
        partida.sumarUsuario(usuario2);

        Usuario rival = partida.buscarRival(usuario1.getId());

        assertEquals(usuario2, rival);
    }
}
