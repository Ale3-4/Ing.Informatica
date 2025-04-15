package GourmetRadarGUI;

import javax.swing.*;
import Backend.GourmetRadarApp;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GourmetRadarApp app = new GourmetRadarApp();
            app.setVisible(true);
        });
    }
}