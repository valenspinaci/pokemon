package ar.edu.davinci.DAO.implementacion;

import ar.edu.davinci.DAO.interfaces.EntrenadorDAO;
import ar.edu.davinci.exceptions.CapturarPokemonException;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.Usuario;
import ar.edu.davinci.models.tipos.Tipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                    " (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(60), nacionalidad VARCHAR(60), genero VARCHAR(60), edad int, id_usuario int)";
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Entrenador create(Entrenador entrenador){
        String sql = ("INSERT INTO " + TABLE_NAME + " (nombre, nacionalidad, genero, edad, id_usuario) VALUES (?, ?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getGenero());
            pstmt.setInt(4, entrenador.getEdad());
            pstmt.setInt(5, entrenador.getIdUsuario());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                entrenador.setId(rs.getInt(1));
            }

            String[] tipos = {"fuego","agua","planta","electrico","piedra"};
            String[] especies = {"Charizard", "Squirtle", "Pikachu", "Bulbasaur", "Eevee", "Snorlax"};
            Random random = new Random();

            String tipoStr = tipos[random.nextInt(tipos.length)];
            Tipo tipo = Tipo.crearTipoPorNombre(tipoStr);
            String especie = especies[random.nextInt(especies.length)];
            Float poder = random.nextFloat(100F);
            Float energia = random.nextFloat(100F);
            Float danio = random.nextFloat(100F);
            Pokemon pokemon = new Pokemon(tipo, especie, poder, energia, danio, entrenador.getId());

            PokemonDAOImplH2 pokemonDAO = new PokemonDAOImplH2();
            pokemonDAO.create(pokemon);
            entrenador.capturarPokemon(pokemon);

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CapturarPokemonException e) {
            e.printStackTrace();
        }
        System.out.println("Entrenador agregado!");
        return entrenador;
    };

    public Entrenador getEntrenadorById(int id){
        Entrenador entrenador = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String nombre = rs.getString("nombre");
                String nacionalidad = rs.getString("nacionalidad");
                String genero = rs.getString("genero");
                int edad = rs.getInt("edad");
                int id_usuario = rs.getInt("id_usuario");
                entrenador = new Entrenador(id, nombre, nacionalidad, genero, edad, id_usuario);
                PokemonDAOImplH2 pokemonDAO = new PokemonDAOImplH2();
                List<Pokemon> pokemons = pokemonDAO.getPokemonesByEntrenador(id);
                entrenador.setPokemons(pokemons);
            }
            rs.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return entrenador;
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
                int idUsuario = resultSet.getInt("id_usuario");
                Entrenador entrenador = new Entrenador(id, nombre, nacionalidad, genero, edad, idUsuario);
                entrenadores.add(entrenador);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    };

    public List<Entrenador> getEntrenadoresByUsuario(int idUsuario) {
        List<Entrenador> entrenadores = new ArrayList<>();
        Entrenador entrenador = null;

        String sql = ("SELECT * FROM " + TABLE_NAME + " WHERE id_usuario = ?");

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                entrenador = new Entrenador(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("nacionalidad"),
                        resultSet.getString("genero"),
                        resultSet.getInt("edad"),
                        resultSet.getInt("id_usuario")
                );
                PokemonDAOImplH2 pokemonDAO = new PokemonDAOImplH2();
                entrenador.setPokemons(pokemonDAO.getPokemonesByEntrenador(entrenador.getId()));
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }


    public Entrenador update(Entrenador entrenador, int id){
        String sql = ("UPDATE " + TABLE_NAME + " SET nombre = ?, nacionalidad = ?, genero = ?, edad = ?, id_usuario = ? WHERE id = ?");

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getGenero());
            pstmt.setInt(4, entrenador.getEdad());
            pstmt.setInt(5, entrenador.getIdUsuario());
            pstmt.setInt(6, id);
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
