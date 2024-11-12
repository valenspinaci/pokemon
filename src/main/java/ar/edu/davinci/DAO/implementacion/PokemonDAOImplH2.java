package ar.edu.davinci.DAO.implementacion;

import ar.edu.davinci.DAO.PokemonDAO;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.Tipo;

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
                    "(id INT AUTO_INCREMENT PRIMARY KEY, tipo VARCHAR(60), especie VARCHAR(60), poder int, energia int, vida int, danio int)";
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Pokemon create(Pokemon pokemon){
        String sql = ("INSERT INTO " + TABLE_NAME + "(tipo, especie, poder, energia, vida, danio) VALUES (?, ?, ?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, pokemon.getTipo().getNombre());
            pstmt.setString(2, pokemon.getEspecie());
            pstmt.setInt(3, pokemon.getPoder());
            pstmt.setInt(4, pokemon.getEnergia());
            pstmt.setInt(5, pokemon.getVida());
            pstmt.setInt(6, pokemon.getDanio());

            pstmt.executeUpdate();

            //Obtengo la clave generada
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                pokemon.setId(rs.getInt(1));
            }else {
                System.err.println("No se pudo obtener el ID generado para el Pokémon.");
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Pokemon agregado!");
        return pokemon;
    };

    public Pokemon getPokemonById(int id){
        Pokemon pokemon = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                int identificador = rs.getInt("id");
                String tipoNombre = rs.getString("tipo");
                String especie = rs.getString("especie");
                int poder = rs.getInt("poder");
                int energia = rs.getInt("energia");
                int vida = rs.getInt("vida");
                int danio = rs.getInt("danio");
                Tipo tipo = Tipo.crearTipoPorNombre(tipoNombre);
                pokemon = new Pokemon(tipo, especie, poder, energia, danio);
                pokemon.setId(identificador);
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
            int poder = resultSet.getInt("poder");
            int energia = resultSet.getInt("energia");
            int vida = resultSet.getInt("vida");
            int danio = resultSet.getInt("danio");
            Tipo tipo = Tipo.crearTipoPorNombre(tipoNombre);
            Pokemon pokemon = new Pokemon(tipo, especie, poder, energia, danio);
            pokemon.setId(id);
            pokemons.add(pokemon);
        }
        resultSet.close();
        statement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
        return pokemons;
    };

    public Pokemon update(Pokemon pokemon, int id){
        String sql = ("UPDATE " + TABLE_NAME + " SET tipo = ?, especie = ?, poder = ?, energia = ?, vida = ?, danio = ? WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, pokemon.getTipo().getNombre());
            pstmt.setString(2, pokemon.getEspecie());
            pstmt.setInt(3, pokemon.getPoder());
            pstmt.setInt(4, pokemon.getEnergia());
            pstmt.setInt(5, pokemon.getVida());
            pstmt.setInt(6, pokemon.getDanio());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Pokemon actualizado!");
        return pokemon;
    }

    public void delete(Pokemon pokemon){
        String sql = ("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pokemon.getId());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
