package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private UsuarioDAOImplH2 usuarioDAO;

    public LoginWindow() {
        usuarioDAO = new UsuarioDAOImplH2();

        setTitle("Login");
        setSize(300, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nicknameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesion");
        registerButton = new JButton("Registro");

        setLayout(new GridLayout(6, 2, 10, 10));
        add(new JLabel("Nickname:"));
        add(nicknameField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> {
            validarUsuario();
        });

        registerButton.addActionListener(e -> {
            RegistroWindow registroWindow = new RegistroWindow();
            registroWindow.setVisible(true);

            dispose();
        });

        setVisible(true);
    }

    private void validarUsuario(){
        String nickname = nicknameField.getText();
        String password = new String(passwordField.getPassword());

        if (nickname.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.validateUser(nickname, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login exitoso! Bienvenido " + usuario.getNombre() + "!");
            int idUsuario = usuario.getId();
            String nombreUsuario = usuario.getNombre();
            EntrenadorWindow entrenadorWindow = new EntrenadorWindow(idUsuario);
            entrenadorWindow.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Nickname o contraseña incorrectos");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
        });
    }
}
