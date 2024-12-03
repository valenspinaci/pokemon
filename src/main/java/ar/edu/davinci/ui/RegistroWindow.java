package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistroWindow extends JFrame {
    private JTextField nameField, lastnameField, emailField, nicknameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;
    private UsuarioDAOImplH2 usuarioDAO;

    public RegistroWindow() {
        usuarioDAO = new UsuarioDAOImplH2();

        setTitle("Registro de usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField(20);
        lastnameField = new JTextField(20);
        emailField = new JTextField(20);
        nicknameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        phoneField = new JTextField(20);

        registerButton = new JButton("Registrarse");
        loginButton = new JButton("Login");

        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 40));

        loginButton.setBackground(new Color(108, 117, 125));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 40));

        panel.add(createLabeledField("Nombre:", nameField));
        panel.add(createLabeledField("Apellido:", lastnameField));
        panel.add(createLabeledField("Email:", emailField));
        panel.add(createLabeledField("Nickname:", nicknameField));
        panel.add(createLabeledField("Contraseña:", passwordField));
        panel.add(createLabeledField("Telefono:", phoneField));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);
        panel.add(buttonPanel);

        add(panel);

        registerButton.addActionListener(e -> registrarUsuario());

        loginButton.addActionListener(e -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
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

    private void registrarUsuario() {
        String nombre = nameField.getText();
        String apellido = lastnameField.getText();
        String email = emailField.getText();
        String telefono = phoneField.getText();
        String contrasena = new String(passwordField.getPassword());
        String nickname = nicknameField.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || nickname.isEmpty() || contrasena.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int telefonoInt;
        try {
            telefonoInt = Integer.parseInt(telefono);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El telefono debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(email, nombre, apellido, nickname, contrasena, telefonoInt);
        Usuario usuarioCreado = usuarioDAO.create(nuevoUsuario);

        if (usuarioCreado != null) {
            JOptionPane.showMessageDialog(this, "Usuario registrado!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistroWindow();
        });
    }
}