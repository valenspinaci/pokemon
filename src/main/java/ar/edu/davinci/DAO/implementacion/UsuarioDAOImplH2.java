package ar.edu.davinci.DAO.implementacion;

import ar.edu.davinci.DAO.interfaces.UsuarioDAO;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UsuarioDAOImplH2 implements UsuarioDAO {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "usuarios";
    private Connection connection;

    public UsuarioDAOImplH2() {
        try{
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(60), nombre VARCHAR(60), apellido VARCHAR(60), nickname VARCHAR(60) UNIQUE, contrasena VARCHAR(60), telefono INT)";
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Usuario create(Usuario usuario){
        String sql = ("INSERT INTO " + TABLE_NAME + " (email, nombre, apellido, nickname, contrasena, telefono) VALUES (?, ?, ?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getNickname());
            pstmt.setString(5, usuario.getContrasena());
            pstmt.setInt(6, usuario.getTelefono());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Usuario agregado!");
        return usuario;
    }

    public Usuario getUsuarioById(int id){
        Usuario usuario = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String nickname = rs.getString("nickname");
                String contrasena = rs.getString("contrasena");
                int telefono = rs.getInt("telefono");
                usuario = new Usuario(id, email, nombre, apellido, nickname, contrasena, telefono);
            }
            rs.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> getAll(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = ("SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC");
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String nickname = resultSet.getString("nickname");
                String contrasena = resultSet.getString("contrasena");
                int telefono = resultSet.getInt("telefono");
                Usuario usuario = new Usuario(id, email, nombre, apellido, nickname, contrasena, telefono);
                usuarios.add(usuario);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario validateUser(String nickname, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE nickname = ? AND contrasena = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nickname);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String nicknameDb = rs.getString("nickname");
                String contrasenaDb = rs.getString("contrasena");
                int telefono = rs.getInt("telefono");

                usuario = new Usuario(id, email, nombre, apellido, nicknameDb, contrasenaDb, telefono);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public Usuario update(Usuario usuario, int id){
        String sql = ("UPDATE " + TABLE_NAME + " SET email = ?, nombre = ?, apellido = ?, nickname = ?, telefono = ? WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getNickname());
            pstmt.setInt(5, usuario.getTelefono());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Usuario actualizado!");
        return usuario;
    }

    public void delete(int id){
        String sql = ("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
