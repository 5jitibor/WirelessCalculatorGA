package WirelessCalculatorGUI;


import WirelessCalculator.Calculator;
import WirelessCalculator.TypeCalculationEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI {
	
	JFrame window;

	private JLabel appTitle;
	private JLabel descriptionText;

	private JTextField numtextfCov;
	private JTextField numtextfLProp;
	private double numtextfValue;
	private JTextField numtexthbCov;
	private JTextField numtexthbLProp;
	private double numtexthbValue;
	private JTextField numtexthmCov;
	private JTextField numtexthmLProp;
	private double numtexthmValue;
	private JTextField numtextd;
	private JTextField numtextptx;
	private JTextField numtextgtx;
	private JTextField numtextsrx;
	private JTextField numtextgrx;
	private JButton buttonResult;
	private JPanel tabbedPanel;
	private JPanel panelLossPropagation;
	private JPanel panelCoverage;
	JToggleButton lossPropagation;
	JToggleButton coverage;
	final static String COVERAGEPANEL = "Coverage";
	final static String LOSSPROPAGATIONPANEL = "Propagation Loss";

	public GUI() {
		initializeComponents();
		InputStream file = getClass().getClassLoader().getResourceAsStream("icon.png");
		BufferedImage bImage;
		try {
			assert file != null;
			bImage = ImageIO.read(file);
			//set icon on JFrame menu bar, as in Windows system
			window.setIconImage(bImage);
			//set icon on system tray, as in Mac OS X system
			//final Taskbar taskbar = Taskbar.getTaskbar();
			//taskbar.setIconImage(bImage);
		} catch (IOException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		setLayout();
		listeners();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public void initializeComponents() {
   		//To create the Frame
		window = new JFrame("Wireless Calculator");
		appTitle = new JLabel("Wireless Calculator");
		appTitle.putClientProperty( "FlatLaf.styleClass", "h00" );
		descriptionText = new JLabel("<html>Introduce the following data to calculate the coverage or the loss propagation</html>");
		descriptionText.setPreferredSize(new Dimension(500, 50));
		descriptionText.putClientProperty( "FlatLaf.styleClass", "large" );
		initializeTextFields();
		buttonResult = new JButton("Get results");
		lossPropagation = new JToggleButton(LOSSPROPAGATIONPANEL);
		coverage = new JToggleButton(COVERAGEPANEL);
		coverage.setSelected(true);
	 }

	 public void initializeTextFields(){
		 numtextfCov = new JTextField(10);
		 numtextfCov.setHorizontalAlignment(JTextField.RIGHT);
		 numtextfCov.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextfCov.addKeyListener(new JTextFieldNumListener(numtextfCov));

		 numtextfLProp = new JTextField(10);
		 numtextfLProp.setHorizontalAlignment(JTextField.RIGHT);
		 numtextfLProp.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextfLProp.addKeyListener(new JTextFieldNumListener(numtextfLProp));

		 numtexthbCov = new JTextField(10);
		 numtexthbCov.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthbCov.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthbCov.addKeyListener(new JTextFieldNumListener(numtexthbCov));

		 numtexthbLProp = new JTextField(10);
		 numtexthbLProp.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthbLProp.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthbLProp.addKeyListener(new JTextFieldNumListener(numtexthbLProp));

		 numtexthmCov = new JTextField(10);
		 numtexthmCov.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthmCov.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthmCov.addKeyListener(new JTextFieldNumListener(numtexthmCov));

		 numtexthmLProp = new JTextField(10);
		 numtexthmLProp.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthmLProp.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthmLProp.addKeyListener(new JTextFieldNumListener(numtexthmLProp));

		 numtextd = new JTextField(10);
		 numtextd.setHorizontalAlignment(JTextField.RIGHT);
		 numtextd.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextd.addKeyListener(new JTextFieldNumListener(numtextd));

		 numtextptx = new JTextField(10);
		 numtextptx.setHorizontalAlignment(JTextField.RIGHT);
		 numtextptx.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextptx.addKeyListener(new JTextFieldNumListener(numtextptx));

		 numtextgtx = new JTextField(10);
		 numtextgtx.setHorizontalAlignment(JTextField.RIGHT);
		 numtextgtx.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextgtx.addKeyListener(new JTextFieldNumListener(numtextgtx));

		 numtextsrx = new JTextField(10);
		 numtextsrx.setHorizontalAlignment(JTextField.RIGHT);
		 numtextsrx.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextsrx.addKeyListener(new JTextFieldNumListener(numtextsrx));

		 numtextgrx = new JTextField(10);
		 numtextgrx.setHorizontalAlignment(JTextField.RIGHT);
		 numtextgrx.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextgrx.addKeyListener(new JTextFieldNumListener(numtextgrx));
	}
	
	public void  setLayout() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		topPanel.add(generateTitleAndDescription());
		topPanel.add(generateOptionSelection());
		panel.add(topPanel, BorderLayout.NORTH);

		tabbedPanel = generateTabbedPanel();
		panel.add(tabbedPanel, BorderLayout.CENTER);

		panel.add(generateButtonRow(), BorderLayout.SOUTH);
		
		window.setContentPane(panel);
		window.pack();
	}

	public JPanel generateTitleAndDescription() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 0, 0, 0);
		panel.add(appTitle, c);

		c.gridy = 1;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(descriptionText, c);

		c.gridy = 2;
		c.insets = new Insets(30, 0, 0, 0);
		panel.add(new JSeparator(), c);
		return panel;
	}

	public JPanel generateLossPropagationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.weightx = 0.5;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtextfTag = new JLabel("Frequency");
		numtextfTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextfTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtextfLProp, "MHz"), c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 20);
		JLabel numtexthbTag = new JLabel("Height base");
		numtexthbTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthbTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 20, 20);
		panel.add(generateFieldWithUnits(numtexthbLProp, "m"), c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtexthmTag = new JLabel("Height mobile");
		numtexthmTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthmTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtexthmLProp, "m"), c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtextdTag = new JLabel("Distance");
		numtextdTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextdTag, c);

		c.gridy = 5;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtextd, "Km"), c);

		return panel;
	}

	public JPanel generateCoveragePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.weightx = 0.5;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtextfTag = new JLabel("Frequency");
		numtextfTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextfTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtextfCov, "MHz"), c);

		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 20);
		JLabel numtexthbTag = new JLabel("Height base");
		numtexthbTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthbTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 20, 20);
		panel.add(generateFieldWithUnits(numtexthbCov, "m"), c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtexthmTag = new JLabel("Height mobile");
		numtexthmTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthmTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtexthmCov, "m"), c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 20);
		JLabel numtextptxTag = new JLabel("Power transmitter");
		numtextptxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextptxTag, c);

		c.gridy = 5;
		c.insets = new Insets(0, 0, 20, 20);
		panel.add(generateFieldWithUnits(numtextptx, "dBm"), c);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtextsrxTag = new JLabel("Sensitivity receiver");
		numtextsrxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextsrxTag, c);

		c.gridy = 5;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtextsrx, "dBm"), c);

		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(0, 0, 10, 20);
		JLabel numtextgtxTag = new JLabel("Gain transmitter");
		numtextgtxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextgtxTag, c);


		c.gridy = 7;
		c.insets = new Insets(0, 0, 20, 20);
		panel.add(generateFieldWithUnits(numtextgtx, "dB"), c);

		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(0, 0, 10, 10);
		JLabel numtextgrxTag = new JLabel("Gain receiver");
		numtextgrxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextgrxTag, c);

		c.gridy = 7;
		c.insets = new Insets(0, 0, 20, 10);
		panel.add(generateFieldWithUnits(numtextgrx, "dB"), c);

		return panel;
	}

	private JPanel generateFieldWithUnits(JTextField field, String units) {
		JPanel fieldRow = new JPanel(new BorderLayout(10, 0));
		fieldRow.add(field, BorderLayout.CENTER);
		JLabel unitsLabel = new JLabel(units);
		unitsLabel.putClientProperty("FlatLaf.styleClass", "h3");
		fieldRow.add(unitsLabel, BorderLayout.EAST);
		return fieldRow;
	}
	public JPanel generateTabbedPanel() {
		JPanel cards = new JPanel(new CardLayout());

		panelLossPropagation = generateLossPropagationPanel();
		panelCoverage = generateCoveragePanel();

		cards.add(panelCoverage, COVERAGEPANEL);
		cards.add(panelLossPropagation, LOSSPROPAGATIONPANEL);

		return cards;
	}
	
	public JPanel generateButtonRow() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		panel.add(buttonResult);
		return panel;
	}

	public JPanel generateOptionSelection() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(20, 0, 10, 0);
		JLabel paddingTag = new JLabel("What do you want to calculate?");
		paddingTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(paddingTag, c);

		ButtonGroup group = new ButtonGroup();
	    group.add(lossPropagation);
	    group.add(coverage);

		JPanel buttonsRow = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonsRow.add(coverage);
		buttonsRow.add(lossPropagation);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 10, 0);
		panel.add(buttonsRow, c);

	    return panel;
	}

	public void listeners() {
		generateFreqBinding(numtextfCov);
		generateFreqBinding(numtextfLProp);

		generateHBaseBinding(numtexthbCov);
		generateHBaseBinding(numtexthbLProp);

		generateHMobileBinding(numtexthmCov);
		generateHMobileBinding(numtexthmLProp);

		buttonResult.addActionListener(e -> {
			String error ="";
			double f = numtextfValue;
			System.out.println(f);
			error += checkValue(f,150,2000,"f");
			double hb = numtexthbValue;
			error += checkValue(hb,30,200,"hb");
			double hm = numtexthmValue;
			error += checkValue(hm,1,10,"hm");
			if(lossPropagation.isSelected()){
				double d = getValue(numtextd);
				error += checkValue(d,1,20,"d");

				if(error.isEmpty()){
					new GUIResult(Calculator.calculatePropagationLoss(f,hb,hm,d),TypeCalculationEnum.LOSSPROPAGATION);
				}
				else{
					JOptionPane.showMessageDialog(window, 	"ERROR:\n"+error, "ERROR",JOptionPane.ERROR_MESSAGE);
				}


			}
			else{
				double ptx = 0;
				try {
					ptx = getValueNotEmpty(numtextptx, "Ptx");
				} catch(Error err) {
					error += err.getMessage();
				}
				double gtx = 0;
				try {
					gtx = getValueNotEmpty(numtextgtx, "Gtx");
				} catch(Error err) {
					error += err.getMessage();
				}
				double srx = 0;
				try {
					srx = getValueNotEmpty(numtextsrx, "Srx");
				} catch(Error err) {
					error += err.getMessage();
				}
				double grx = 0;
				try {
					grx = getValueNotEmpty(numtextgrx, "Grx");
				} catch(Error err) {
					error += err.getMessage();
				}

				if(error.isEmpty()){
					new GUIResult(Calculator.calculateCoverage(f, hb, hm, ptx, gtx, grx, srx), TypeCalculationEnum.COVERAGE);

				}
				else{
					JOptionPane.showMessageDialog(window, 	"ERROR:\n"+error, "ERROR",JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		lossPropagation.addActionListener(e -> {
			updateLossPropagationPanel();

			CardLayout cl = (CardLayout) (tabbedPanel.getLayout());
			cl.show(tabbedPanel, LOSSPROPAGATIONPANEL);
			tabbedPanel.setPreferredSize(new Dimension(500, 312));
			((Window) tabbedPanel.getTopLevelAncestor()).pack();
		});

		coverage.addActionListener(e -> {
			updateCoveragePanel();

			CardLayout cl = (CardLayout) (tabbedPanel.getLayout());
			cl.show(tabbedPanel, COVERAGEPANEL);
			tabbedPanel.setPreferredSize(new Dimension(500, 416));
			((Window) tabbedPanel.getTopLevelAncestor()).pack();
		});
	}

	public static String fmt(double d) {
		if(d == (long) d && d != 0)
			return String.format("%d",(long)d);
		else if (d == 0)
			return "";
		else
			return String.format("%s",d);
	}
	private void updateLossPropagationPanel() {
		numtextfLProp.setText(fmt(numtextfValue));
		numtexthbLProp.setText(fmt(numtexthbValue));
		numtexthmLProp.setText(fmt(numtexthmValue));
	}

	private void updateCoveragePanel() {
		numtextfCov.setText(fmt(numtextfValue));
		numtexthbCov.setText(fmt(numtexthbValue));
		numtexthmCov.setText(fmt(numtexthmValue));
	}

	private void generateHMobileBinding(JTextField numtexthm) {
		numtexthm.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthm));
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthm));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthm));
			}
			public void updateValue(double newValue) {
				numtexthmValue = newValue;
			}
		});
	}

	private void generateHBaseBinding(JTextField numtexthb) {
		numtexthb.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthb));
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthb));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateValue(getValue(numtexthb));
			}
			public void updateValue(double newValue) {
				numtexthbValue = newValue;
			}
		});
	}

	private void generateFreqBinding(JTextField numtextf) {
		numtextf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateValue(getValue(numtextf));
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateValue(getValue(numtextf));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateValue(getValue(numtextf));
			}
			public void updateValue(double newValue) {
				numtextfValue = newValue;
			}
		});
	}

	public double getValue(JTextField textField){
		if(textField.getText().isEmpty()){
			return 0.0;
		}
		System.out.println(textField.getText());
		return Double.parseDouble(textField.getText());
	}

	public double getValueNotEmpty(JTextField textField, String parameter){
		if(textField.getText().isEmpty()){
			throw new Error("Value not valid: " + parameter +" is mandatory\n");
		}
		System.out.println(textField.getText());
		return Double.parseDouble(textField.getText());
	}

	public String checkValue(double value, double min, double max, String parameter){
		if(value<min || value>max){
			return "Value not valid: "+ min + " ≤ " + parameter +" ≤ " + max +"\n";
		}
		return "";
	}
}
