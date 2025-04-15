package GUI;

import javax.swing.*;
import java.awt.*;
import Backend.GourmetRadarApp;

public class FiltersPanel extends JPanel {
    private GourmetRadarApp parent;

    public FiltersPanel(GourmetRadarApp parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Panel superior con título
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("FILTROS AVANZADOS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);

        // Panel central con los filtros
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Filtro de Ubicación
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locationPanel.setBackground(new Color(240, 240, 240));
        JButton locationButton = new JButton("Ubicación");
        locationButton.addActionListener(e -> mostrarUbicaciones());
        locationPanel.add(locationButton);
        centerPanel.add(locationPanel);

        // Filtro de Tipo de Comida
        JPanel foodTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        foodTypePanel.setBackground(new Color(240, 240, 240));
        JButton foodTypeButton = new JButton("Tipo de Comida");
        foodTypeButton.addActionListener(e -> mostrarTiposComida());
        foodTypePanel.add(foodTypeButton);
        centerPanel.add(foodTypePanel);

        // Filtro de Precio
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePanel.setBackground(new Color(240, 240, 240));
        JButton priceButton = new JButton("Precio");
        priceButton.addActionListener(e -> mostrarRangosPrecio());
        pricePanel.add(priceButton);
        centerPanel.add(pricePanel);

        // Filtro de Valoración
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.setBackground(new Color(240, 240, 240));
        JButton ratingButton = new JButton("Valoración");
        ratingButton.addActionListener(e -> mostrarValoraciones());
        ratingPanel.add(ratingButton);
        centerPanel.add(ratingPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Panel inferior con botones de acción
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> parent.showCard("SEARCH"));
        bottomPanel.add(backButton);

        JButton applyButton = new JButton("Aplicar Filtros");
        applyButton.addActionListener(e -> aplicarFiltros());
        bottomPanel.add(applyButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void mostrarUbicaciones() {
        String[] barriosMadrid = {
                "Centro", "Salamanca", "Chamberí", "Retiro",
                "Latina", "Moncloa", "Chamartín", "Tetuán",
                "Usera", "Carabanchel", "Villaverde", "Hortaleza"
        };

        JComboBox<String> comboBox = new JComboBox<>(barriosMadrid);
        JOptionPane.showMessageDialog(this, comboBox, "Selecciona un barrio", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarTiposComida() {
        String[] tiposComida = {
                "Española", "China", "Mexicana", "Japonesa",
                "Peruana", "Italiana", "India", "Americana",
                "Mediterránea", "Vegetariana", "Vegana", "Fusión"
        };

        JComboBox<String> comboBox = new JComboBox<>(tiposComida);
        JOptionPane.showMessageDialog(this, comboBox, "Selecciona tipo de comida", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarRangosPrecio() {
        String[] rangosPrecio = {
                "€ (10-20)", "€€ (20-40)",
                "€€€ (50-100)", "€€€€ (100+)"
        };

        JComboBox<String> comboBox = new JComboBox<>(rangosPrecio);
        JOptionPane.showMessageDialog(this, comboBox, "Selecciona rango de precio", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarValoraciones() {
        String[] valoraciones = {
                "★☆☆☆ (1 estrella)", "★★☆☆ (2 estrellas)",
                "★★★☆ (3 estrellas)", "★★★★ (4 estrellas)"
        };

        JComboBox<String> comboBox = new JComboBox<>(valoraciones);
        JOptionPane.showMessageDialog(this, comboBox, "Selecciona valoración mínima", JOptionPane.PLAIN_MESSAGE);
    }

    private void aplicarFiltros() {
        JOptionPane.showMessageDialog(this, "Filtros aplicados correctamente");
        parent.showCard("SEARCH");
    }
}