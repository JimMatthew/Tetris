import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;

public class TetrisGui extends JFrame {
	
	public TetrisGui(JPanel grid, JPanel scorePanel)
	{
		setTitle("Tetris Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(grid);
		getContentPane().add(scorePanel);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		pack();
		revalidate();
		repaint();
	}
	
	public void launch()
	{
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
