package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.EntrenadorDAOImplH2;
import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.models.Entrenador;
import ar.edu.davinci.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private UsuarioDAOImplH2 usuarioDAO;
    private EntrenadorDAOImplH2 entrenadorDAO;

    public LoginWindow() {
        usuarioDAO = new UsuarioDAOImplH2();
        entrenadorDAO = new EntrenadorDAOImplH2();

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nicknameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Iniciar sesión");
        registerButton = new JButton("Registrarse");

        loginButton.setBackground(new Color(0, 123, 255));  // Color azul
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 40));

        registerButton.setBackground(new Color(108, 117, 125)); // Color gris
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 40));

        panel.add(createLabeledField("Nickname:", nicknameField));
        panel.add(createLabeledField("Contraseña:", passwordField));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        panel.add(buttonPanel);

        add(panel);

        loginButton.addActionListener(e -> validarUsuario());

        registerButton.addActionListener(e -> {
            RegistroWindow registroWindow = new RegistroWindow();
            registroWindow.setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private void validarUsuario() {
        String nickname = nicknameField.getText();
        String password = new String(passwordField.getPassword());

        if (nickname.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.validateUser(nickname, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login exitoso! Bienvenido " + usuario.getNombre().substring(0,1).toUpperCase() + usuario.getNombre().substring(1).toLowerCase() + "!");
            crearUsuariosGenericos();
            EntrenadorWindow entrenadorWindow = new EntrenadorWindow(usuario);
            entrenadorWindow.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Nickname o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearUsuariosGenericos(){
        if(usuarioDAO.getAll().size() == 1){
            Usuario usuario1 = new Usuario("user1@gmail.com", "user1", "user1", "user1", "user1", 1100000000);
            Usuario usuario2 = new Usuario("user2@gmail.com", "user2", "user2", "user2", "user2", 1100000000);
            usuario1.setId(2);
            usuario2.setId(3);
            usuarioDAO.create(usuario1);
            usuarioDAO.create(usuario2);

            Entrenador entrenadorUsuario1 = new Entrenador("Ash", "Kanto", "M", 13, 2);
            Entrenador entrenadorUsuario2 = new Entrenador("Misty", "Kanto", "F", 14, 3);
            entrenadorDAO.create(entrenadorUsuario1);
            entrenadorDAO.create(entrenadorUsuario2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginWindow();
        });
    }
}