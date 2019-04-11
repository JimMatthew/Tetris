//import javax.swing.JFrame;
import javax.swing.JPanel;

public class TheTetrisGame {
	
	public static void main(String args[])
	{
		TetrisConfiguration configHandler = new ConfigHandler();
		GameGrid gameGridGui = new GameGridGui(configHandler.getBlockSize());
		ScorePanelGui scorePanelGui = new ScorePanelGui(configHandler.getBlockSize());
		TetrisStatistics stats = new StatManager();
		TetrisController controller = new TetrisController(configHandler, gameGridGui, stats, scorePanelGui);
		scorePanelGui.setController(controller);
		TetrisGui tetrisGui = new TetrisGui((JPanel) gameGridGui, (JPanel) scorePanelGui);
		tetrisGui.launch();
		KeyBoardListener keyBoard = new KeyBoardListener(controller);
	}
	
}
