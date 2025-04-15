package Backend;

import javax.swing.*;
import java.awt.*;
import GUI.LoginPanel;
import GUI.FiltersPanel;
import GUI.RestaurantPanel;
import GUI.WelcomePanel;
import GUI.RegisterPanel;
import GUI.SearchPanel;

public class GourmetRadarApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public GourmetRadarApp() {
        setTitle("Gourmet Radar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500); // Aumenté un poco el tamaño para mejor visualización
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear y añadir los panels
        cardPanel.add(new WelcomePanel(this), "WELCOME");
        cardPanel.add(new LoginPanel(this), "LOGIN");
        cardPanel.add(new RegisterPanel(this), "REGISTER");
        cardPanel.add(new SearchPanel(this), "SEARCH");
        cardPanel.add(new FiltersPanel(this), "FILTERS");
        cardPanel.add(new RestaurantPanel(this), "RESTAURANT");

        add(cardPanel);
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

}


