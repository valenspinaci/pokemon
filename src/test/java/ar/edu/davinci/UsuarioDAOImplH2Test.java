package ar.edu.davinci;

import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Usuario;
import ar.edu.davinci.models.tipos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioDAOImplH2Test {
    UsuarioDAOImplH2 conexion;
    Usuario usuario;
    Usuario usuario2;
    Entrenador entrenador;

    @BeforeEach
    public void setUp() {
        conexion = new UsuarioDAOImplH2();
        usuario = new Usuario("juanperez@gmail.com", "Juan", "Perez", "jp123","123", 1112345678);
        usuario2 = new Usuario("analopez@gmail.com", "Ana", "Lopez", "al.al.123","123", 1198765432);
        entrenador = new Entrenador("Ash", "Kanto", "Masculino", 10);
    }

    @Test
    @DisplayName("Test utilizado para verificar si la conexion se logra de forma exitosa")
    public void testParaVerificarSiLaConexionSeHaceDeFormaExitosa(){
        try{
            new UsuarioDAOImplH2Test();
        }catch(Exception e){
            System.out.println("Error al establecer la conexion: " + e.getMessage());
            assert false: "No se pudo establecer la conexion";
        }
    };

    @Test
    @DisplayName("Cuando uso el metodo para guardar un usuario en memoria lo hace de forma exitosa")
    public void testParaVerificarQueSePuedeAgregarUnUsuario(){
        Usuario resultado = conexion.create(usuario);

        assertEquals(usuario, resultado);
    };

    @Test
    @DisplayName("Verifico si el método para devolver un usuario por el ID lo hace de forma correcta")
    public void testParaDevolverQueSePuedeDevolverUnUsuarioPorId() {
        Usuario creado = conexion.create(usuario);

        Usuario resultado = conexion.getUsuarioById(creado.getId());

        assertEquals(creado.getNombre(), resultado.getNombre());
        assertEquals(creado.getApellido(), resultado.getApellido());
        assertEquals(creado.getNickname(), resultado.getNickname());
        assertEquals(creado.getEmail(), resultado.getEmail());
        assertEquals(creado.getTelefono(), resultado.getTelefono());
        assertEquals(creado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Cuando actualizo un usuario se guarda de forma exitosa con los datos que le pase")
    public void testParaVerificarQueSePuedenActualizarLosUsuariosDeFormaExitosa() {
        Usuario usuarioCreado = conexion.create(usuario);

        Usuario actualizacion = usuario2;
        Usuario usuarioActualizado = conexion.update(actualizacion, usuarioCreado.getId());

        assertEquals(actualizacion.getNombre(), usuarioActualizado.getNombre());
        assertEquals(actualizacion.getApellido(), usuarioActualizado.getApellido());
        assertEquals(actualizacion.getNickname(), usuarioActualizado.getNickname());
        assertEquals(actualizacion.getEmail(), usuarioActualizado.getEmail());
        assertEquals(actualizacion.getTelefono(), usuarioActualizado.getTelefono());
        assertEquals(actualizacion.getId(), usuarioActualizado.getId());
    }

    @Test
    @DisplayName("Cuando agrego un único usuario a la lista y luego lo elimino, el largo de la misma es 0")
    public void testParaVerificarQueSePuedenEliminarUsuarios() {
        conexion.create(usuario);

        conexion.delete(1);

        assertEquals(0, conexion.getAll().size());
    }
}
