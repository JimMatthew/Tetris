import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Color;

public class StatisticsWindow extends JFrame{

	private JPanel statPanel;
	private TetrisStatistics statManager;
	private JTextField textFieldTopScore1;
	private JTextField textFieldTopScore2;
	private JTextField textFieldTopScore3;
	private JTextField textFieldTotalLines;
	private JTextField textFieldLines1;
	private JTextField textFieldLines2;
	private JTextField textFieldLines3;
	private JTextField textFieldLines4;
	private JTextField textFieldTimePlayed;
	private JButton btnClose;
	private JSeparator separator;
	
	public StatisticsWindow(TetrisStatistics manager)
	{
		getContentPane().setForeground(UIManager.getColor("Button.shadow"));
		statManager = manager;
		setSize(new Dimension(450, 550));
		getContentPane().setLayout(null);
		buildComponents();
		setListener();
	}
	
	public void enableDisplay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setSize(450, 550);
		setLocationRelativeTo(null);
		getStatsForDisplay();
		setVisible(true);
	}
	
	private void getStatsForDisplay()
	{
		int[] lines = statManager.getLifetimeLineClearStats();
		textFieldTopScore1.setText("1st: "+Integer.toString(statManager.getHighScore1()));
		textFieldTopScore2.setText("2nd: "+Integer.toString(statManager.getHighScore2()));
		textFieldTopScore3.setText("3rd: "+Integer.toString(statManager.getHighScore3()));
		textFieldTotalLines.setText(Integer.toString(statManager.getLifeTimeLines()));
		textFieldLines1.setText(Integer.toString(lines[0]));
		textFieldLines2.setText(Integer.toString(lines[1]));
		textFieldLines3.setText(Integer.toString(lines[2]));
		textFieldLines4.setText(Integer.toString(lines[3]));
		int totalSecs = statManager.getTotalTimeInSeconds();
		int hours = totalSecs / 3600;
		int minutes = (totalSecs % 3600) / 60;
		int seconds = totalSecs % 60;
		String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		textFieldTimePlayed.setText(timeString);
	}
	
	private void setListener()
	{
		btnClose.addActionListener(event -> setVisible(false));
	}
	
	private void buildComponents()
	{
		add(ItemBuilder.makeLabel("Statistics", 163, 11, 177, 34, new Font("Tahoma", Font.BOLD, 18)));
		add(ItemBuilder.makeLabel("Lines", 10, 47, 133, 24, 17));
		add(ItemBuilder.makeLabel("Total:", 10, 82, 86, 14));
		add(ItemBuilder.makeLabel("1 Line:", 10, 112, 60, 14));
		add(ItemBuilder.makeLabel("2 Line:", 10, 142, 60, 14));
		add(ItemBuilder.makeLabel("3 Line:", 10, 172, 60, 14));
		add(ItemBuilder.makeLabel("4 Line:", 10, 203, 60, 14));
		add(ItemBuilder.makeLabel("Top Scores", 10, 228, 133, 24, 17));
		add(ItemBuilder.makeLabel("Total Time Played: ", 10, 388, 199, 14, 11));
		add(ItemBuilder.makeLabel("2018 James Lindstrom", 283, 466, 151, 20, 12));
		
		textFieldTopScore1 = ItemBuilder.makeField(10, 263, 86, 20);
		add(textFieldTopScore1);
		
		textFieldTopScore2 = ItemBuilder.makeField(10, 293, 86, 20);
		add(textFieldTopScore2);
		
		textFieldTopScore3 = ItemBuilder.makeField(10, 324, 86, 20);
		add(textFieldTopScore3);
	
		textFieldTotalLines = ItemBuilder.makeField(75, 79, 86, 20);
		add(textFieldTotalLines);
		
		textFieldLines1 = ItemBuilder.makeField(75, 110, 86, 20);
		add(textFieldLines1);
		
		textFieldLines2 = ItemBuilder.makeField(75, 140, 86, 20);
		add(textFieldLines2);
		
		textFieldLines3 = ItemBuilder.makeField(75, 170, 86, 20);
		add(textFieldLines3);
	
		textFieldLines4 = ItemBuilder.makeField(75, 200, 86, 20);
		add(textFieldLines4);
		
		textFieldTimePlayed = ItemBuilder.makeField(119, 385, 86, 20);
		add(textFieldTimePlayed);
		
		btnClose = ItemBuilder.makeButton("Close", 163, 465, 89, 23);
		add(btnClose);
	
		separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.light"));
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(10, 228, 400, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlHighlight);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(10, 43, 400, 2);
		getContentPane().add(separator_1);
	}
}
