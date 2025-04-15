package GUI;

import javax.swing.*;
import java.awt.*;
import Backend.GourmetRadarApp;

public class SearchPanel extends JPanel {
    private GourmetRadarApp parent;

    public SearchPanel(GourmetRadarApp parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Panel superior con título
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("BUSCAR RESTAURANTES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);

        // Panel central con información del usuario
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));

        // Panel de información del usuario
        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setBorder(BorderFactory.createTitledBorder("Mi perfil"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Nombre: UsuarioEjemplo");
        gbc.gridx = 0;
        gbc.gridy = 0;
        userPanel.add(nameLabel, gbc);

        JLabel typeLabel = new JLabel("Tipo: Gourmet");
        gbc.gridy = 1;
        userPanel.add(typeLabel, gbc);

        JLabel loginLabel = new JLabel("Último acceso: hoy");
        gbc.gridy = 2;
        userPanel.add(loginLabel, gbc);

        JLabel ratingsLabel = new JLabel("Mis valoraciones: 15");
        gbc.gridy = 3;
        userPanel.add(ratingsLabel, gbc);

        centerPanel.add(userPanel);

        // Panel de filtros
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtrar restaurantes"));

        // Botón para abrir panel de filtros avanzados
        JButton advancedFiltersButton = new JButton("Filtros Avanzados");
        advancedFiltersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        advancedFiltersButton.addActionListener(e -> parent.showCard("FILTERS"));

        filterPanel.add(Box.createVerticalStrut(20));
        filterPanel.add(advancedFiltersButton);
        filterPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(filterPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Panel inferior con botones de filtros rápidos
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));

        JButton bestButton = new JButton("Los Mejores");
        bestButton.addActionListener(e -> aplicarFiltro("mejores"));
        bottomPanel.add(bestButton);

        JButton ratedButton = new JButton("Más Valorados");
        ratedButton.addActionListener(e -> aplicarFiltro("valorados"));
        bottomPanel.add(ratedButton);

        JButton nearbyButton = new JButton("Cercanos");
        nearbyButton.addActionListener(e -> aplicarFiltro("cercanos"));
        bottomPanel.add(nearbyButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void aplicarFiltro(String filtro) {
        // Lógica para aplicar filtros rápidos
        JOptionPane.showMessageDialog(this, "Filtrando por: " + filtro);
    }
}