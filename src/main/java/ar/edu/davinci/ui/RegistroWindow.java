package ar.edu.davinci.ui;

import ar.edu.davinci.DAO.implementacion.UsuarioDAOImplH2;
import ar.edu.davinci.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class RegistroWindow extends JFrame {
    private JTextField nameField, lastnameField, emailField, nicknameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;
    private UsuarioDAOImplH2 usuarioDAO;

    public RegistroWindow(){
        usuarioDAO = new UsuarioDAOImplH2();

        setTitle("Registro de usuario");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nameField = new JTextField(20);
        lastnameField = new JTextField(20);
        emailField = new JTextField(20);
        nicknameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        phoneField = new JTextField(20);
        registerButton = new JButton("Registrarse");
        loginButton = new JButton("Login");

        setLayout(new GridLayout(7, 2, 10, 10));
        add(new JLabel("Nombre:"));
        add(nameField);
        add(new JLabel("Apellido:"));
        add(lastnameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Nickname:"));
        add(nicknameField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(new JLabel("Telefono:"));
        add(phoneField);
        add(registerButton);
        add(loginButton);

        registerButton.addActionListener(e -> {
            registrarUsuario();
        });

        loginButton.addActionListener(e -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);

            dispose();
        });

        setVisible(true);
    }

    private void registrarUsuario(){
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
        try{
            telefonoInt = Integer.parseInt(telefono);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El telefono debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(email, nombre, apellido, nickname, contrasena,telefonoInt);
        Usuario usuarioCreado = usuarioDAO.create(nuevoUsuario);
        System.out.println(usuarioCreado.getNickname() + usuarioCreado.getContrasena() + usuarioCreado.getId());

        if(usuarioCreado != null){
            JOptionPane.showMessageDialog(this, "Usuario registrado!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Hubo un error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        RegistroWindow window = new RegistroWindow();
    }
}
