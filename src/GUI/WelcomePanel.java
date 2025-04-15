package GUI;

import javax.swing.*;
import java.awt.*;
import Backend.GourmetRadarApp;

public class WelcomePanel extends JPanel {
    private GourmetRadarApp parent;

    public WelcomePanel(GourmetRadarApp parent) {
        this.parent = parent;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Título
        JLabel titleLabel = new JLabel("Gourmet Radar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Espacio
        gbc.gridy = 1;
        add(Box.createVerticalStrut(40), gbc);

        // Botón de Iniciar Sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(e -> parent.showCard("LOGIN"));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        // Espacio entre botones
        gbc.gridy = 3;
        add(Box.createVerticalStrut(20), gbc);

        // Botón de Registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.addActionListener(e -> parent.showCard("REGISTER"));
        gbc.gridy = 4;
        add(registerButton, gbc);
    }
}fctgfutfi