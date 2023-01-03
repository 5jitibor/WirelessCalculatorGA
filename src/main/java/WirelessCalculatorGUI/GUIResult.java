package WirelessCalculatorGUI;


import WirelessCalculator.TypeCalculationEnum;
import WirelessCalculatorGUI.CierzoTheme.CierzoTheme;
import WirelessCalculatorGUI.CierzoTheme.JPanelRound;
import WirelessCalculator.TypeScenarioEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIResult {
	JFrame window;
	
	public GUIResult(HashMap<TypeScenarioEnum,Double> resultsList, TypeCalculationEnum typeCalculation) {

		initializateComponents();

		InputStream file = getClass().getClassLoader().getResourceAsStream("icon.png");
		BufferedImage bImage;
		try {
			assert file != null;
			bImage = ImageIO.read(file);
			//set icon on JFrame menu bar, as in Windows system
			window.setIconImage(bImage);
			//set icon on system tray, as in Mac OS X system
			final Taskbar taskbar = Taskbar.getTaskbar();
			taskbar.setIconImage(bImage);
		} catch (IOException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		setLayout(resultsList,typeCalculation);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public void initializateComponents() {
		//To create the Frame
		window = new JFrame("Wireless Calculator Result");
	}

	public void setLayout(HashMap<TypeScenarioEnum,Double> resultsList, TypeCalculationEnum typeCalculation) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;


		panel.add(generateTitle(typeCalculation),c);

		c.gridy = 1;
		c.ipady = 0;
		c.fill = GridBagConstraints.BOTH;
		panel.add(setResultsList(resultsList,typeCalculation),c);

		window.setContentPane(panel);
		window.pack();
	}

	public JPanel generateTitle(TypeCalculationEnum typeCalculation) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel typeTitle = new JLabel(typeCalculation.toString());
		typeTitle.putClientProperty( "FlatLaf.styleClass", "h1" );

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 0, 0, 0);
		panel.add(typeTitle, c);

		c.gridy = 1;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(new JSeparator(), c);
		return panel;
	}
	
	public JPanel setResultsList(HashMap<TypeScenarioEnum,Double> resultsList, TypeCalculationEnum typeCalculation) {
		JPanel panel = new JPanel();

		GridLayout layout = new GridLayout(2, 2);
		layout.setHgap(20);
		layout.setVgap(20);
		panel.setLayout(layout);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		for(TypeScenarioEnum result : resultsList.keySet()) {
			double value = (double) Math.round(resultsList.get(result)*100d)/100d;
			if (value >= 0) {
				panel.add(generateCardPanel(result.toString(),value +" "+typeCalculation.toMetric()));
			} else {
				panel.add(generateCardPanel(result.toString(), "Out of range"));
			}
		}
		return panel;
	}


	private JPanelRound generateCardPanel(String title, String value) {
		JPanelRound cardPanel = new JPanelRound();
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		cardPanel.setBackground(CierzoTheme.primaryColorLight);
		cardPanel.setRound(CierzoTheme.roundedCorners);
		cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel cardTitle = new JLabel(title);
		cardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardPanel.add(cardTitle);

		JLabel cardValue = new JLabel(value);
		cardValue.putClientProperty( "FlatLaf.styleClass", "h2" );
		cardValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardPanel.add(cardValue);

		return cardPanel;
	}
}
