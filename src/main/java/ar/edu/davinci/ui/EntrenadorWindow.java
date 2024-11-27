package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.EntrenadorDAOImplH2;
import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.exceptions.AgregarEntrenadorException;
import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.SeleccionarEntrenadorException;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Partida;
import ar.edu.davinci.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class EntrenadorWindow extends JFrame {
    private EntrenadorDAOImplH2 entrenadorDAO;
    private UsuarioDAOImplH2 usuarioDAO;
    private int idUsuario;
    private JPanel panelEntrenadores;
    private JButton crearButton, eliminarButton, elegirButton, inicioButton;
    private JList<String> listaEntrenadores;
    private DefaultListModel<String> modeloLista;

    public EntrenadorWindow(int idUsuario){
        this.idUsuario = idUsuario;
        this.entrenadorDAO = new EntrenadorDAOImplH2();
        this.usuarioDAO = new UsuarioDAOImplH2();

        setTitle("Gestion de entrenadores");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelEntrenadores = new JPanel(new BorderLayout());
        modeloLista = new DefaultListModel<>();
        listaEntrenadores = new JList<>(modeloLista);
        panelEntrenadores.add(new JScrollPane(listaEntrenadores), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        crearButton = new JButton("Crear entrenador");
        eliminarButton = new JButton("Eliminar entrenador");
        elegirButton = new JButton("Elegir entrenador");
        inicioButton = new JButton("Inicio");

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

    private void actualizarListaEntrenadores(){
        modeloLista.clear();
        List<Entrenador> entrenadores = entrenadorDAO.getEntrenadoresByUsuario(idUsuario);

        if(entrenadores.isEmpty()){
            modeloLista.addElement("No hay entrenadores disponibles");
            listaEntrenadores.setEnabled(false);
            eliminarButton.setEnabled(false);
            elegirButton.setEnabled(false);
        }else{
            for(Entrenador entrenador : entrenadores){
                modeloLista.addElement(entrenador.getId() + " - " + entrenador.getNombre() + " - " + entrenador.getNacionalidad() + " - " + entrenador.getEdad() + " años - Pokemones: " + entrenador.cantPokemons());
            }
            listaEntrenadores.setEnabled(true);
            eliminarButton.setEnabled(true);
            elegirButton.setEnabled(true);
        }

        if(entrenadores.size() == 3){
            crearButton.setEnabled(false);
        }else{
            crearButton.setEnabled(true);
        }
    }

    private void configurarAcciones() {
        crearButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del entrenador:");
            String pais = JOptionPane.showInputDialog(this, "Ingrese el país del entrenador:");
            String sexo = JOptionPane.showInputDialog(this, "Ingrese el sexo del entrenador:");
            int edad;
            try {
                edad = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la edad del entrenador:"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nombre != null && !nombre.isEmpty()) {
                Entrenador nuevoEntrenador = new Entrenador(nombre, pais, sexo, edad, idUsuario);
                Usuario usuario = usuarioDAO.getUsuarioById(idUsuario);
                entrenadorDAO.create(nuevoEntrenador);
                usuario.setEntrenadores(entrenadorDAO.getEntrenadoresByUsuario(idUsuario));
                actualizarListaEntrenadores();
                System.out.println(usuario.contarEntrenadores());
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    mostrarResultadoBatalla(ganador);

                    this.dispose();
                } catch (SeleccionarEntrenadorException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (AtaqueException ex) {
                    JOptionPane.showMessageDialog(this, "Hubo un problema durante la batalla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un entrenador para la batalla.");
            }
        });
    }


    private Entrenador elegirRivalAleatorio() throws SeleccionarEntrenadorException {
        Partida partida = new Partida();
        Usuario usuarioRival = partida.buscarRival(idUsuario);

        if (entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).isEmpty()) {
            throw new SeleccionarEntrenadorException("No hay entrenadores disponibles");
        }

        Random random = new Random();
        int index = random.nextInt(entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).size());
        return entrenadorDAO.getEntrenadoresByUsuario(usuarioRival.getId()).get(index);
    }

    private void mostrarResultadoBatalla(Entrenador ganador) {
        String mensaje = ganador != null ? "¡El ganador es " + ganador.getNombre() + "!" : "¡La batalla terminó en empate!";

        JOptionPane.showMessageDialog(this, mensaje);
    }
}