import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.Color;

public class AdvancedSettingsGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private TetrisConfiguration configHandler;
	private JPanel configPanel = new JPanel();
	private JTextField textFieldDAS;
	private JTextField textFieldARE;
	private JTextField textFieldGravity;
	private JTextField textFieldLockDelay;
	private JTextField textFieldARR;
	private JTextField textFieldStartLevel;
	private JButton setThisGameSettings;
	private JButton setAsDefaultButton;
	private JButton cancelButton;
	private JLabel lblDAS;
	private JLabel lblStartLevel;
	private JCheckBox chckbxGhostPiece;
	private JCheckBox chckbxSuperRotation;
	private JLabel lblBlockSize;
	private JTextField textFieldBlockSize;

	public AdvancedSettingsGui(TetrisConfiguration configHandler) {
		this.configHandler = configHandler;
		//setSize(new Dimension(450, 550));
		getContentPane().setLayout(null);
		getContentPane().add(configPanel);
		configPanel.setBounds(0, 0, 434, 511);
		configPanel.setLayout(null);
		setTitle("Advanced Settings");
		buildComponents();
		getCurrentSettings();
		setButtonListeners();
	}

	public void enableDisplay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setSize(450, 550);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void getCurrentSettings() {
		chckbxGhostPiece.setSelected(configHandler.getShowGhostPiece());
		chckbxSuperRotation.setSelected(configHandler.getSuperRotation());
		textFieldDAS.setText(Integer.toString(configHandler.getDAS()));
		textFieldARE.setText(Integer.toString(configHandler.getARE()));
		textFieldGravity.setText(Integer.toString(configHandler.getGravity()));
		textFieldLockDelay.setText(Integer.toString(configHandler.getLockDelay()));
		textFieldARR.setText(Integer.toString(configHandler.getARR()));
		textFieldStartLevel.setText(Integer.toString(configHandler.getStartLevel()));
		textFieldBlockSize.setText(Integer.toString(configHandler.getBlockSize()));
	}

	private void setButtonListeners() {
		cancelButton.addActionListener(event -> setVisible(false));
		setThisGameSettings.addActionListener(event -> {setConfigToCurrent(); setVisible(false);});
		setAsDefaultButton.addActionListener(event -> {setConfigToCurrent(); configHandler.saveCurrentSettings(); setVisible(false);});
	}

	private void setConfigToCurrent() {
		configHandler.setARE(Integer.parseInt(textFieldARE.getText()));
		configHandler.setDAS(Integer.parseInt(textFieldDAS.getText()));
		configHandler.setGravity(Integer.parseInt(textFieldGravity.getText()));
		configHandler.setLockDelay(Integer.parseInt(textFieldLockDelay.getText()));
		configHandler.setShowGhostPiece(chckbxGhostPiece.isSelected());
		configHandler.setSuperRotation(chckbxSuperRotation.isSelected());
		configHandler.setARR(Integer.parseInt(textFieldARR.getText()));
		configHandler.setStartLevel(Integer.parseInt(textFieldStartLevel.getText()));
		configHandler.setBlockSize(Integer.parseInt(textFieldBlockSize.getText()));
	}

	private void buildComponents() {
		JLabel mainLabel = new JLabel("Advanced Settings");
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		mainLabel.setBounds(144, 25, 161, 23);
		configPanel.add(mainLabel);

		setThisGameSettings = new JButton("Set Until Close");
		setThisGameSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setThisGameSettings.setBackground(SystemColor.controlHighlight);
		setThisGameSettings.setBounds(10, 450, 127, 23);
		configPanel.add(setThisGameSettings);

		setAsDefaultButton = new JButton("Save as new Default");
		setAsDefaultButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setAsDefaultButton.setBackground(SystemColor.controlHighlight);
		setAsDefaultButton.setBounds(150, 450, 165, 23);
		configPanel.add(setAsDefaultButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBackground(SystemColor.controlHighlight);
		cancelButton.setBounds(330, 450, 89, 23);
		configPanel.add(cancelButton);

		chckbxGhostPiece = new JCheckBox("Show Ghost Piece");
		chckbxGhostPiece.setBounds(10, 67, 171, 23);
		configPanel.add(chckbxGhostPiece);

		chckbxSuperRotation = new JCheckBox("Super Rotation");
		chckbxSuperRotation.setBounds(10, 93, 113, 23);
		configPanel.add(chckbxSuperRotation);

		lblDAS = new JLabel("DAS");
		lblDAS.setBounds(10, 168, 46, 14);
		configPanel.add(lblDAS);

		textFieldDAS = new JTextField();
		textFieldDAS.setBounds(81, 165, 59, 20);
		configPanel.add(textFieldDAS);
		textFieldDAS.setColumns(10);

		JLabel lblAre = new JLabel("ARE");
		lblAre.setBounds(10, 198, 46, 14);
		configPanel.add(lblAre);

		textFieldARE = new JTextField();
		textFieldARE.setBounds(81, 195, 59, 20);
		configPanel.add(textFieldARE);
		textFieldARE.setColumns(10);

		JLabel lblGravity = new JLabel("Gravity");
		lblGravity.setBounds(10, 288, 46, 14);
		configPanel.add(lblGravity);

		textFieldGravity = new JTextField();
		textFieldGravity.setBounds(81, 285, 59, 20);
		configPanel.add(textFieldGravity);
		textFieldGravity.setColumns(10);

		JLabel lblLockDelay = new JLabel("Lock Delay");
		lblLockDelay.setBounds(10, 258, 71, 14);
		configPanel.add(lblLockDelay);

		textFieldLockDelay = new JTextField();
		textFieldLockDelay.setBounds(81, 255, 59, 20);
		configPanel.add(textFieldLockDelay);
		textFieldLockDelay.setColumns(10);

		JLabel lblValuesAreSpecified = new JLabel("Values are specified in number of frames");
		lblValuesAreSpecified.setBounds(10, 137, 275, 14);
		configPanel.add(lblValuesAreSpecified);

		JLabel lblArr = new JLabel("ARR");
		lblArr.setBounds(10, 228, 46, 14);
		configPanel.add(lblArr);

		textFieldARR = new JTextField();
		textFieldARR.setBounds(81, 225, 59, 20);
		configPanel.add(textFieldARR);
		textFieldARR.setColumns(10);

		JLabel lbldelayAutoShift = new JLabel("(Delay Auto Shift)");
		lbldelayAutoShift.setBounds(150, 168, 108, 14);
		configPanel.add(lbldelayAutoShift);

		JLabel lblautoRepeatRate = new JLabel("(Auto Repeat Rate)");
		lblautoRepeatRate.setBounds(150, 228, 108, 14);
		configPanel.add(lblautoRepeatRate);

		JLabel lblentryDelay = new JLabel("(Entry Delay)");
		lblentryDelay.setBounds(150, 198, 71, 14);
		configPanel.add(lblentryDelay);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(10, 155, 417, 2);
		configPanel.add(separator);

		lblStartLevel = new JLabel("Start Level");
		lblStartLevel.setBounds(10, 318, 71, 14);
		configPanel.add(lblStartLevel);

		textFieldStartLevel = new JTextField();
		textFieldStartLevel.setBounds(81, 315, 59, 20);
		configPanel.add(textFieldStartLevel);
		textFieldStartLevel.setColumns(10);
		
		lblBlockSize = new JLabel("Block Size");
		lblBlockSize.setBounds(10, 348, 71, 14);
		configPanel.add(lblBlockSize);
		
		textFieldBlockSize = new JTextField();
		textFieldBlockSize.setText("0");
		textFieldBlockSize.setColumns(10);
		textFieldBlockSize.setBounds(81, 345, 59, 20);
		configPanel.add(textFieldBlockSize);
		
		JLabel lbltakesAffectOn = new JLabel("(Takes Affect on Next Launch)");
		lbltakesAffectOn.setBounds(150, 348, 208, 14);
		configPanel.add(lbltakesAffectOn);
	}
}
