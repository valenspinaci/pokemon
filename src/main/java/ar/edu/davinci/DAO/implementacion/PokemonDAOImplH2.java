package ar.edu.davinci.DAO.implementacion;

import ar.edu.davinci.DAO.interfaces.PokemonDAO;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.tipos.Tipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImplH2 implements PokemonDAO {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "pokemons";
    private Connection connection;

    public PokemonDAOImplH2() {
        try{
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, tipo VARCHAR(60), especie VARCHAR(60), poder FLOAT, energia FLOAT, vida FLOAT, danio FLOAT, id_entrenador int)";
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Pokemon create(Pokemon pokemon){
        String sql = "INSERT INTO " + TABLE_NAME + "(tipo, especie, poder, energia, vida, danio, id_entrenador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, pokemon.getTipo().getNombre());
            pstmt.setString(2, pokemon.getEspecie());
            pstmt.setFloat(3, pokemon.getPoder());
            pstmt.setFloat(4, pokemon.getEnergia());
            pstmt.setFloat(5, pokemon.getVida());
            pstmt.setFloat(6, pokemon.getDanio());
            pstmt.setInt(7, pokemon.getIdEntrenador());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                pokemon.setId(rs.getInt(1));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Pokemon agregado!");
        return pokemon;
    }


    public Pokemon getPokemonById(int id){
        Pokemon pokemon = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String tipoNombre = rs.getString("tipo");
                String especie = rs.getString("especie");
                Float poder = rs.getFloat("poder");
                Float energia = rs.getFloat("energia");
                Float danio = rs.getFloat("danio");
                int idEntrenador = rs.getInt("id_entrenador");
                Tipo tipo = Tipo.crearTipoPorNombre(tipoNombre);
                pokemon = new Pokemon(id, tipo, especie, poder, energia, danio, idEntrenador);
            }
            rs.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return pokemon;
    }


    public List<Pokemon> getAll(){
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        String sql = ("SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC");
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String tipoNombre = resultSet.getString("tipo");
                String especie = resultSet.getString("especie");
                Float poder = resultSet.getFloat("poder");
                Float energia = resultSet.getFloat("energia");
                Float danio = resultSet.getFloat("danio");
                int idEntrenador = resultSet.getInt("id_entrenador");
                Tipo tipo = Tipo.crearTipoPorNombre(tipoNombre);
                Pokemon pokemon = new Pokemon(id, tipo, especie, poder, energia, danio, idEntrenador);
                pokemons.add(pokemon);
            }
            resultSet.close();
            statement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
        return pokemons;
    };

    public List<Pokemon> getPokemonesByEntrenador(int idEntrenador) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_entrenador = ?";
        List<Pokemon> pokemones = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idEntrenador);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String tipoNombre = resultSet.getString("tipo");
                String especie = resultSet.getString("especie");
                Float poder = resultSet.getFloat("poder");
                Float energia = resultSet.getFloat("energia");
                Float danio = resultSet.getFloat("danio");
                Tipo tipo = Tipo.crearTipoPorNombre(tipoNombre);
                Pokemon pokemon = new Pokemon(tipo, especie, poder, energia, danio, idEntrenador);
                pokemon.setId(resultSet.getInt("id"));
                pokemones.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemones;
    }


    public Pokemon update(Pokemon pokemon, int id){
        String sql = ("UPDATE " + TABLE_NAME + " SET tipo = ?, especie = ?, poder = ?, energia = ?, vida = ?, danio = ?, id_entrenador = ? WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, pokemon.getTipo().getNombre());
            pstmt.setString(2, pokemon.getEspecie());
            pstmt.setFloat(3, pokemon.getPoder());
            pstmt.setFloat(4, pokemon.getEnergia());
            pstmt.setFloat(5, pokemon.getVida());
            pstmt.setFloat(6, pokemon.getDanio());
            pstmt.setInt(7, pokemon.getIdEntrenador());
            pstmt.setFloat(8, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Pokemon actualizado!");
        return pokemon;
    }

    public void delete(int id){
        String sql = ("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Pokemon eliminado!");
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}