package WirelessCalculatorGA;

import WirelessCalculatorGUI.CierzoTheme.CierzoTheme;
import WirelessCalculatorGUI.GUI;

import javax.swing.*;

public class WirelessCalculator {
    public static void main(String[] args) {
        CierzoTheme.setup();

        SwingUtilities.invokeLater(() -> {
            try {
                @SuppressWarnings("unused")
                GUI a = new GUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
