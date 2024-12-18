package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.EntrenadorDAOImplH2;
import ar.edu.davinci.DAO.implementacion.PokemonDAOImplH2;
import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.exceptions.SeleccionarEntrenadorException;
import ar.edu.davinci.models.*;
import ar.edu.davinci.ui.utils.BatallaLogger;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;


public class EntrenadorWindow extends JFrame {
    private EntrenadorDAOImplH2 entrenadorDAO;
    private UsuarioDAOImplH2 usuarioDAO;
    private Pokemon pokemon;
    private Entrenador entrenador;
    private PokemonDAOImplH2 pokemonDAO;
    private Usuario usuario;
    private JPanel panelEntrenadores;
    private JButton crearButton, eliminarButton, elegirButton, inicioButton;
    private JList<String> listaEntrenadores;
    private DefaultListModel<String> modeloLista;
    private JTextArea areaMensajes;
    private BatallaLogger batallaLogger;

    public EntrenadorWindow(Usuario usuario) {
        this.usuario = usuario;
        this.entrenadorDAO = new EntrenadorDAOImplH2();
        this.usuarioDAO = new UsuarioDAOImplH2();
        this.pokemonDAO = new PokemonDAOImplH2();

        setTitle("Gestion de entrenadores");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaMensajes = new JTextArea(5, 40);
        areaMensajes.setEditable(false);
        areaMensajes.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(areaMensajes), BorderLayout.NORTH);

        batallaLogger = new BatallaLogger(areaMensajes);

        pokemon = new Pokemon();
        pokemon.setBatallaLogger(batallaLogger);

        entrenador = new Entrenador();
        entrenador.setBatallaLogger(batallaLogger);

        panelEntrenadores = new JPanel(new BorderLayout());
        modeloLista = new DefaultListModel<>();
        listaEntrenadores = new JList<>(modeloLista);
        panelEntrenadores.add(new JScrollPane(listaEntrenadores), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        crearButton = new JButton("Crear entrenador");
        eliminarButton = new JButton("Eliminar entrenador");
        elegirButton = new JButton("Elegir entrenador");
        inicioButton = new JButton("Inicio");

        crearButton.setBackground(new Color(0, 123, 255));
        crearButton.setForeground(Color.WHITE);
        crearButton.setFocusPainted(false);
        crearButton.setPreferredSize(new Dimension(150, 40));

        eliminarButton.setBackground(new Color(0, 123, 255));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setPreferredSize(new Dimension(150, 40));

        elegirButton.setBackground(new Color(0, 123, 255));
        elegirButton.setForeground(Color.WHITE);
        elegirButton.setFocusPainted(false);
        elegirButton.setPreferredSize(new Dimension(150, 40));

        inicioButton.setBackground(new Color(108, 117, 125));
        inicioButton.setForeground(Color.WHITE);
        inicioButton.setFocusPainted(false);
        inicioButton.setPreferredSize(new Dimension(150, 40));

        panelBotones.add(inicioButton);
        panelBotones.add(crearButton);
        panelBotones.add(eliminarButton);
        panelBotones.add(elegirButton);

        add(panelEntrenadores, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        configurarAcciones();
        actualizarListaEntrenadores();

        setVisible(true);
    }

    private void actualizarListaEntrenadores() {
        modeloLista.clear();
        List<Entrenador> entrenadores = entrenadorDAO.getEntrenadoresByUsuario(usuario.getId());

        if (entrenadores.isEmpty()) {
            modeloLista.addElement("No hay entrenadores disponibles");
            listaEntrenadores.setEnabled(false);
            eliminarButton.setEnabled(false);
            elegirButton.setEnabled(false);
        } else {
            for (Entrenador entrenador : entrenadores) {
                modeloLista.addElement(entrenador.getId() + " - " + entrenador.getNombre().substring(0, 1).toUpperCase() + entrenador.getNombre().substring(1).toLowerCase() + " - " + entrenador.getNacionalidad().substring(0, 1).toUpperCase() + entrenador.getNacionalidad().substring(1).toLowerCase() + " - " + entrenador.getEdad() + " años - Pokemones: " + pokemonDAO.getPokemonesByEntrenador(entrenador.getId()).size());
            }
            listaEntrenadores.setEnabled(true);
            eliminarButton.setEnabled(true);
            elegirButton.setEnabled(true);
        }

        if (entrenadores.size() == 3) {
            crearButton.setEnabled(false);
        } else {
            crearButton.setEnabled(true);
        }
    }

    private void configurarAcciones() {
        crearButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del entrenador:");
            String pais = JOptionPane.showInputDialog(this, "Ingrese el país del entrenador:");

            // Crear un JComboBox con las opciones de sexo
            String[] opcionesSexo = {"M", "F", "Otro"};
            JComboBox<String> comboBoxSexo = new JComboBox<>(opcionesSexo);
            int opcionSeleccionada = JOptionPane.showConfirmDialog(
                    this,
                    comboBoxSexo,
                    "Seleccione el sexo del entrenador:",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            String sexo = null;
            if (opcionSeleccionada == JOptionPane.OK_OPTION) {
                sexo = (String) comboBoxSexo.getSelectedItem();
            }

            int edad;
            try {
                edad = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la edad del entrenador:"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nombre != null && !nombre.isEmpty() && sexo != null) {
                Entrenador nuevoEntrenador = new Entrenador(nombre, pais, sexo, edad, usuario.getId());
                Usuario usuariox = usuarioDAO.getUsuarioById(usuario.getId());
                entrenadorDAO.create(nuevoEntrenador);
                usuariox.setEntrenadores(entrenadorDAO.getEntrenadoresByUsuario(usuario.getId()));
                actualizarListaEntrenadores();
            } else {
                JOptionPane.showMessageDialog(this, "El nombre o el sexo no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        eliminarButton.addActionListener(e -> {
            int seleccion = listaEntrenadores.getSelectedIndex();
            if (seleccion >= 0) {
                String seleccionado = modeloLista.get(seleccion);
                int idEntrenador = Integer.parseInt(seleccionado.split(" - ")[0]);
                Entrenador entrenadorAEliminar = entrenadorDAO.getEntrenadorById(idEntrenador);
                entrenadorDAO.delete(entrenadorAEliminar.getId());
                actualizarListaEntrenadores();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un entrenador para eliminar.");
            }
        });

        inicioButton.addActionListener(e -> {
            this.dispose();
            new LoginWindow();
        });

        elegirButton.addActionListener(e -> {
            int seleccion = listaEntrenadores.getSelectedIndex();

            if (seleccion >= 0) {
                String seleccionado = modeloLista.get(seleccion);
                int idEntrenador = Integer.parseInt(seleccionado.split(" - ")[0]);
                Entrenador entrenadorElegido = entrenadorDAO.getEntrenadorById(idEntrenador);

                try {
                    Entrenador entrenadorRival = elegirRivalAleatorio();
                    Entrenador ganador = entrenadorElegido.enfrentarseA(entrenadorRival);
                    String mensaje = ganador != null ? "¡El ganador es " + ganador.getNombre().substring(0, 1).toUpperCase() + ganador.getNombre().substring(1).toLowerCase() + "!" : "¡La batalla terminó en empate!";
                    System.out.println(mensaje);

                    //panelEntrenadores.setEnabled(false);

                    //Timer timer = new Timer(3000, event -> this.dispose());
                    //timer.setRepeats(false);
                    //timer.start();

                } catch (SeleccionarEntrenadorException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un entrenador para la batalla.");
            }
        });
    }

    private Entrenador elegirRivalAleatorio() throws SeleccionarEntrenadorException {
        Partida partida = new Partida();
        Usuario usuarioRival = partida.buscarRival(usuario);

        if (entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).isEmpty()) {
            throw new SeleccionarEntrenadorException("No hay entrenadores disponibles");
        }

        Random random = new Random();
        int index = random.nextInt(entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).size());
        return entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).get(index);
    }
}