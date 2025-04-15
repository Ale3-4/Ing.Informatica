package GUI;

import javax.swing.*;
import java.awt.*;
import Backend.GourmetRadarApp;

public class RestaurantPanel extends JPanel {
    private GourmetRadarApp parent;

    public RestaurantPanel(GourmetRadarApp parent) {
        this.parent = parent;
        setLayout(new GridLayout(3, 1, 10, 10));
        setBackground(new Color(240, 240, 240));

        // Sección 1: Bar Manolo - PATO
        JPanel section1 = new JPanel(new BorderLayout());
        section1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel label1 = new JLabel("Bar Manolo", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 18));
        section1.add(label1, BorderLayout.NORTH);

        JLabel patoLabel = new JLabel("PATO", SwingConstants.CENTER);
        patoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        section1.add(patoLabel, BorderLayout.CENTER);
        add(section1);

        // Sección 2: Known - Rating
        JPanel section2 = new JPanel(new BorderLayout());
        section2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel knownLabel = new JLabel("Known", SwingConstants.CENTER);
        knownLabel.setFont(new Font("Arial", Font.BOLD, 16));
        section2.add(knownLabel, BorderLayout.NORTH);

        JLabel ratingLabel1 = new JLabel("Rating", SwingConstants.CENTER);
        ratingLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
        section2.add(ratingLabel1, BorderLayout.CENTER);
        add(section2);

        // Sección 3: Bar Manolo - Rating
        JPanel section3 = new JPanel(new BorderLayout());
        section3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel label3 = new JLabel("Bar Manolo", SwingConstants.CENTER);
        label3.setFont(new Font("Arial", Font.BOLD, 16));
        section3.add(label3, BorderLayout.NORTH);

        JLabel ratingLabel2 = new JLabel("Rating", SwingConstants.CENTER);
        ratingLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        section3.add(ratingLabel2, BorderLayout.CENTER);
        add(section3);
    }
}
