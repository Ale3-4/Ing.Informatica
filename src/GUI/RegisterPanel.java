package GUI;

import javax.swing.*;
import java.awt.*;
import Backend.GourmetRadarApp;

public class RegisterPanel extends JPanel {
    private GourmetRadarApp parent;

    public RegisterPanel(GourmetRadarApp parent) {
        this.parent = parent;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titleLabel = new JLabel("Gourmet Radar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("REGISTRO");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(subtitleLabel, gbc);

        // Campos de texto
        JLabel userLabel = new JLabel("Usuario:");
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        add(userField, gbc);

        JLabel passLabel = new JLabel("Contraseña:");
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passField, gbc);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> parent.showCard("WELCOME"));
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(backButton, gbc);

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(e -> {
            // Aquí iría la lógica de registro
            parent.showCard("SEARCH");
        });
        gbc.gridx = 1;
        add(registerButton, gbc);
    }
}