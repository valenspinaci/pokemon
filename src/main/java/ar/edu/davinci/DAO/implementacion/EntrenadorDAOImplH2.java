package ar.edu.davinci.DAO.implementacion;

import ar.edu.davinci.DAO.interfaces.EntrenadorDAO;
import ar.edu.davinci.models.Entrenador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntrenadorDAOImplH2 implements EntrenadorDAO {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "entrenadores";
    private Connection connection;

    public EntrenadorDAOImplH2() {
        try{
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    " (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(60), nacionalidad VARCHAR(60), genero VARCHAR(60), edad int)";
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Entrenador create(Entrenador entrenador){
        String sql = ("INSERT INTO " + TABLE_NAME + " (nombre, nacionalidad, genero, edad) VALUES (?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getGenero());
            pstmt.setInt(4, entrenador.getEdad());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                entrenador.setId(rs.getInt(1));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Entrenador agregado!");
        return entrenador;
    };

    public Entrenador getEntrenadorById(int id){
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Entrenador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("genero"),
                        rs.getInt("edad")
                );
            }
            rs.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Entrenador> getAll(){
        List<Entrenador> entrenadores = new ArrayList<Entrenador>();
        String sql = ("SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC");
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String nacionalidad = resultSet.getString("nacionalidad");
                String genero = resultSet.getString("genero");
                int edad = resultSet.getInt("edad");
                Entrenador entrenador = new Entrenador(id, nombre, nacionalidad, genero, edad);
                entrenadores.add(entrenador);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    };

    public Entrenador update(Entrenador entrenador, int id){
        String sql = ("UPDATE " + TABLE_NAME + " SET nombre = ?, nacionalidad = ?, genero = ?, edad = ? WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getGenero());
            pstmt.setInt(4, entrenador.getEdad());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Entrenador actualizado!");
        return entrenador;
    }

    public void delete(int id){
        String sql = ("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Entrenador eliminado!");
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
