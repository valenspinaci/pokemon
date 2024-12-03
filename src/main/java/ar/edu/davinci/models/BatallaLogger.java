package ar.edu.davinci.models;

import javax.swing.*;

public class BatallaLogger {
    private JTextArea textArea;

    public BatallaLogger(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void mostrarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
    }
}
