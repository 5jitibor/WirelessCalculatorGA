package WirelessCalculatorGUI;


import WirelessCalculator.Calculator;
import WirelessCalculator.TypeCalculationEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
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

	private JTextField numtextf;
	private JTextField numtexthb;
	private JTextField numtexthm;
	private JTextField numtextd;
	private JTextField numtextptx;
	private JTextField numtextgtx;
	private JTextField numtextsrx;
	private JTextField numtextgrx;
	private JButton buttonResult;
	private JPanel panelLossPropagation;
	private JPanel panelCoverage;

	JToggleButton lossPropagation;
	JToggleButton coverage;


	public GUI() {
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
		setLayout();
		listeners();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public void initializateComponents() {
   		//To create the Frame
		window = new JFrame("Wireless Calculator");
		appTitle = new JLabel("Wireless Calculator");
		appTitle.putClientProperty( "FlatLaf.styleClass", "h00" );
		descriptionText = new JLabel("<html>Introduce the following data to calculate the coverage or the loss propagation</html>");
		descriptionText.setPreferredSize(new Dimension(400, 50));
		descriptionText.putClientProperty( "FlatLaf.styleClass", "large" );
		initializateTextFields();
		buttonResult = new JButton("Get results");
		lossPropagation = new JToggleButton("Loss Propagation");
		lossPropagation.setSelected(true);
		coverage = new JToggleButton("Coverage");
	 }

	 public void initializateTextFields(){
		 numtextf = new JTextField(10);
		 numtextf.setHorizontalAlignment(JTextField.RIGHT);
		 numtextf.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtextf.addKeyListener(new JTextFieldNumListener(numtextf));
		 numtexthb = new JTextField(10);
		 numtexthb.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthb.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthb.addKeyListener(new JTextFieldNumListener(numtexthb));
		 numtexthm = new JTextField(10);
		 numtexthm.setHorizontalAlignment(JTextField.RIGHT);
		 numtexthm.setFont(new Font("Poppins", Font.PLAIN, 15));
		 numtexthm.addKeyListener(new JTextFieldNumListener(numtexthm));
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
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		panel.add(generateTitleAndDescription(), c);

		c.gridy = 1;
		panel.add(generateOptionSelection(), c);

		c.gridy = 2;
		c.insets = new Insets(10, 0, 0, 0);
		panelLossPropagation = generateLossPropagationPanel();
		panel.add(panelLossPropagation, c);

		c.gridy = 3;
		panel.add(generateButtonRow(), c);
		
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
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.5;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextfTag = new JLabel("Frequency (Hz)");
		numtextfTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextfTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 30, 10);
		panel.add(numtextf, c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtexthbTag = new JLabel("Height base (m)");
		numtexthbTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthbTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 30, 10);
		panel.add(numtexthb, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtexthmTag = new JLabel("Height mobile (m)");
		numtexthmTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthmTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 10, 10);
		panel.add(numtexthm, c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextdTag = new JLabel("Distance (Km)");
		numtextdTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextdTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 10, 10);
		panel.add(numtextd, c);


		return panel;
	       
	}

	public JPanel generateCoveragePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.5;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextfTag = new JLabel("Frequency (Hz)");
		numtextfTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextfTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 30, 10);
		panel.add(numtextf, c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtexthbTag = new JLabel("Height base (m)");
		numtexthbTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthbTag, c);

		c.gridy = 1;
		c.insets = new Insets(0, 0, 30, 0);
		panel.add(numtexthb, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtexthmTag = new JLabel("Height mobile (m)");
		numtexthmTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtexthmTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 30, 10);
		panel.add(numtexthm, c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextptxTag = new JLabel("Power transmiser (dbm)");
		numtextptxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextptxTag, c);

		c.gridy = 3;
		c.insets = new Insets(0, 0, 30, 0);
		panel.add(numtextptx, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextgtxTag = new JLabel("Gain trasmiser (db)");
		numtextgtxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextgtxTag, c);

		c.gridy = 5;
		c.insets = new Insets(0, 0, 30, 10);
		panel.add(numtextgtx, c);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextgrxTag = new JLabel("Gain reciever (db)");
		numtextgrxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextgrxTag, c);


		c.gridy = 5;
		c.insets = new Insets(0, 0, 30, 0);
		panel.add(numtextsrx, c);

		c.weightx = 1;
		c.gridwidth = 2;

		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(0, 0, 10, 0);
		JLabel numtextsrxTag = new JLabel("Sensibility reciever (dbm)");
		numtextsrxTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(numtextsrxTag, c);

		c.gridy = 7;
		c.insets = new Insets(0, 0, 10, 0);
		panel.add(numtextgrx, c);


		return panel;

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
		c.gridwidth = 2;
		c.insets = new Insets(20, 0, 10, 0);
		JLabel paddingTag = new JLabel("What do you want to calculate?");
		paddingTag.putClientProperty( "FlatLaf.styleClass", "h2" );
		panel.add(paddingTag, c);

		ButtonGroup group = new ButtonGroup();
	    group.add(lossPropagation);
	    group.add(coverage);

		c.gridy = 1;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 10, 5);
		panel.add(coverage, c);

		c.gridx = 1;
		c.weightx = 0.5;
		c.insets = new Insets(0, 5, 10, 0);
		panel.add(lossPropagation, c);
	    return panel;
	}
	
	
	public void listeners() {
		buttonResult.addActionListener(e -> {
			String error ="";
			double f = getValue(numtextf);
			System.out.println(f);
			error += checkValue(f,150,2000,"f");
			double hb = getValue(numtexthb);
			error += checkValue(hb,30,200,"hb");
			double hm = getValue(numtexthm);
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
				double ptx = getValue(numtextptx);
				double gtx = getValue(numtextgtx);
				double srx = getValue(numtextsrx);
				double grx = getValue(numtextgrx);
				if(error.isEmpty()){
					new GUIResult(Calculator.calculateCoverage(f, hb, hm, ptx, gtx, grx, srx), TypeCalculationEnum.COVERAGE);

				}
				else{
					JOptionPane.showMessageDialog(window, 	"ERROR:\n"+error, "ERROR",JOptionPane.ERROR_MESSAGE);
				}

			}



		});

		lossPropagation.addActionListener(e -> {
			if(panelCoverage != null){
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 2;
				c.insets = new Insets(10, 0, 0, 0);
				JPanel pane = (JPanel) window.getContentPane();
				pane.remove(panelCoverage);
				panelCoverage = null;
				panelLossPropagation = generateLossPropagationPanel();
				pane.add(panelLossPropagation,c);
				window.setContentPane(pane);
				window.pack();
			}

		});

		coverage.addActionListener(e -> {
			if(panelLossPropagation!= null)
			{
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 2;
				c.insets = new Insets(10, 0, 0, 0);
				JPanel pane = (JPanel) window.getContentPane();
				pane.remove(panelLossPropagation);
				panelLossPropagation = null;
				panelCoverage = generateCoveragePanel();
				pane.add(panelCoverage,c);
				window.setContentPane(pane);
				window.pack();
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

	public String checkValue(double value, double min, double max, String parameter){
		if(value<min || value>max){
			return "Value not valid: "+ min + " ≤ " + parameter +" ≤ " + max +"\n";
		}
		return "";
	}
	

}
