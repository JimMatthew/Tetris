import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Cursor;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class ScorePanelGui extends JPanel implements ScoreAndControlsDisplay {
	private JTextField totalLines;
	private JTextField scoreField;
	private JTextField gameOver;
	private JTextField oneLineField;
	private JTextField twoLineField;
	private JTextField threeLineField;
	private JTextField fourLineField;
	private JTextField highScoreField;
	private JTextField levelField;
	private JButton btnStart;
	private JButton btnPause;
	private JButton advancedSettings;
	private JButton btnStats;
	private boolean isPaused = false;
	private ButtonController controller;
	private int size = 20;

	public ScorePanelGui() {
		setPreferredSize(new Dimension(180, 510));
		this.setBorder(null);
		setLayout(null);
		configureItems();
		configureButtonListeners();
	}

	public ScorePanelGui(int size) {
		this.size = size;
		setPreferredSize(new Dimension(180, 510));
		this.setBorder(null);
		setLayout(null);
		configureItems();
		configureButtonListeners();
	}

	public void setController(ButtonController controller) {
		this.controller = controller;
	}

	@Override
	public void setTotalLinesField(String lines) {
		totalLines.setText(lines);
	}

	@Override
	public void setLevelField(String level) {
		levelField.setText(level);
	}

	@Override
	public void setScoreField(String score) {
		scoreField.setText(score);
	}

	@Override
	public void setStatus(String status) {
		gameOver.setText(status);
	}

	@Override
	public void setHighScoreField(String score) {
		highScoreField.setText(score);
	}

	@Override
	public void setOneLineField(String s) {
		oneLineField.setText(s);
	}

	@Override
	public void setTwoLineField(String s) {
		twoLineField.setText(s);
	}

	@Override
	public void setThreeLineField(String s) {
		threeLineField.setText(s);
	}

	@Override
	public void setFourLineField(String s) {
		fourLineField.setText(s);
	}

	private void configureButtonListeners() {
		btnPause.addActionListener(event -> pauseButtonPressed());
		btnStart.addActionListener(event -> controller.startGame());
		btnStats.addActionListener(event -> controller.launchStats());
		advancedSettings.addActionListener(event -> controller.launchSettings());
	}

	private void pauseButtonPressed() {
		if (isPaused) {
			controller.unPauseGame();
			btnPause.setText("Pause");
			isPaused = false;
			btnStart.setEnabled(true);
		} else {
			controller.pauseGame();
			btnPause.setText("Resume");
			isPaused = true;
			btnStart.setEnabled(false);
		}
	}

	private void configureItems() {
		JLabel lblTetris = new JLabel("Tetris");
		lblTetris.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblTetris.setBounds(64, 11, 103, 45);
		add(lblTetris);
		
		add(ItemBuilder.makeLabel("Lines:", 10, 140, 59, 17));
		totalLines = ItemBuilder.makeField(64, 140, 86, 20, 18);
		add(totalLines);
		
		add(ItemBuilder.makeLabel("Score", 10, 170, 55, 20));
		scoreField = ItemBuilder.makeField(64, 170, 86, 20, 18);
		add(scoreField);

		add(ItemBuilder.makeLabel("Level: ", 10, 200, 59, 20));
		levelField = ItemBuilder.makeField(64, 200, 86, 20, 18);
		add(levelField);

		gameOver = ItemBuilder.makeField(10, 50, 147, 45);
		gameOver.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(gameOver);

		add(ItemBuilder.makeLabel("1 Line:", 10, 250, 55, 20));
		oneLineField = ItemBuilder.makeField(70, 250, 86, 20, 18);
		add(oneLineField);

		add(ItemBuilder.makeLabel("2 Line:", 10, 280, 55, 20));
		twoLineField = ItemBuilder.makeField(70, 280, 86, 20, 18);
		add(twoLineField);

		add(ItemBuilder.makeLabel("3 Line:", 10, 310, 55, 20));
		threeLineField = ItemBuilder.makeField(70, 310, 86, 20, 18);
		add(threeLineField);

		add(ItemBuilder.makeLabel("4 Line:", 10, 340, 55, 20));
		fourLineField = ItemBuilder.makeField(70, 340, 86, 20, 18);
		add(fourLineField);

		btnPause = ItemBuilder.makeButton("Pause", 10, 385, 100, 30);
		add(btnPause);

		btnStart = ItemBuilder.makeButton("Start", 10, 430, 100, 30);
		add(btnStart);

		add(ItemBuilder.makeLabel("High Score:", 10, 472, 100, 20));
		highScoreField = ItemBuilder.makeField(10, 494, 100, 20, 18);
		add(highScoreField);

		advancedSettings = ItemBuilder.makeButton("Settings", 10, 542, 150, 30);
		add(advancedSettings);

		btnStats = ItemBuilder.makeButton("Stats", 10, 596, 150, 30);
		add(btnStats);
	}
}
