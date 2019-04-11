
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import TetrisDisplayAndUtils.ButtonPresses;
public class TetrisController implements InputController, ButtonController{
	public enum keyPresses {
		leftPressed, rightPressed, upPressed, downPressed, xPressed, zPressed, holdPressed, nonePressed
	}
    
	private ActionListener taskPerformer;
	private GamePiece piece;
	private GameBoard gameBoard;
	private GameGrid gridGui;
	private ScoreAndControlsDisplay scoreGui;
	private TetrisConfiguration configHandler;
	private AdvancedSettingsGui settingsGui;
	private StatisticsWindow statisticsWindow;
	private TetrisStatistics statManager;
	private static Timer timer = new Timer();
	private int xLocation = 5;
	private int yLocation = 0;
	private int lockDelayCount = 0;
	private int ARECount = 0;
	private int loopCount = 0;
	private int totalFrames = 0;
	private boolean atBottom = false;
	private boolean keysDisabled = true;
	private HashMap<ButtonPresses, Integer> pressedKeyMap = new HashMap<ButtonPresses, Integer>();
	private HashMap<ButtonPresses, Runnable> commands = new HashMap<>();
	private boolean paused = false;

	TetrisController(TetrisConfiguration configHandler, GameGrid gridGui,TetrisStatistics statManager,ScoreAndControlsDisplay scoreGui) {
		this.configHandler = configHandler;
		this.scoreGui = scoreGui;
		this.gridGui = gridGui;
		this.statManager = statManager;
		piece = new GamePiece();
		gameBoard = new GameBoard();
		settingsGui = new AdvancedSettingsGui(configHandler);
		statisticsWindow = new StatisticsWindow(statManager);
		updateGameInfoDisplay();
		createMap();
	}

	public void setTimerTask(Timer timer) {
		TimerTask task;
		task = new TimerTask() {
			@Override
			public void run() {
				tetrisLoop();
			}
		};
		timer.schedule(task, 0, 16);
	}

	private void startTimer() {
		timer = new Timer();
		setTimerTask(timer);
	}

	private void stopTimer() {
		timer.cancel();
	}

	/*
	 * tetrisLoop
	 * Main loop of game - Steps taken every loop (every 16 ms)
	 * - Check if there are pending key press events, and if so, execute them
	 * -Check if piece is at bottom of grid, if yes, execute method to handle that
	 * -Check if the loop count has reached the current level loop count threshold
	 * 		If so, reset the loop count, and move piece down 1 row
	 * -Increment the loop count, and render the piece and game board
	 */
	private void tetrisLoop() {
		handleKeyEvents();

		if (atBottom) {
			handlePieceAtBottom();
		} else if (loopCount >= getLevelSpeed(statManager.getCurrentLevel())) {
			loopCount = 0;
			tryMovePieceDown();
		}
		loopCount++;
		renderPiece();
		renderGameBoard();
		totalFrames++;
	}

	private void handlePieceAtBottom() {
		if (lockDelayCount == configHandler.getLockDelay()) {
			lockPieceDownAndSpawnNew();
		} else {
			lockDelayCount++;
			tryMovePieceDown();
		}
	}

	private void lockPieceDownAndSpawnNew() { // don't spawn new piece until ARE is hit
		if (ARECount < configHandler.getARE()) {
			ARECount++;
		} else {
			gameBoard.addPieceToBoard(piece.getGamePiece(), xLocation, yLocation);
			newPiece();
			atBottom = false;
			lockDelayCount = 0;
			ARECount = 0;
			loopCount = 0;
			tryMovePieceDown();
		}
	}

	private int getLevelSpeed(int level) {
		if (level <= configHandler.getFrameLevelSpeed().length) {
			return configHandler.getFrameLevelSpeed()[level];
		} else {
			return configHandler.getFrameLevelSpeed()[configHandler.getFrameLevelSpeed().length - 1];
		}
	}
	
	private void pieceMoveLockDelayReset()
	{
		lockDelayCount = 0;
	}
	
	private void addPieceAndSpawnNew() {
		gameBoard.addPieceToBoard(piece.getGamePiece(), xLocation, yLocation);
		newPiece();
	}

	private boolean canPieceMoveDown() {
		return gameBoard.canPieceFit(piece.getGamePiece(), xLocation, yLocation + 1);
	}

	private void createMap() {
		commands.put(ButtonPresses.leftPressed, this::movePieceLeft);
		commands.put(ButtonPresses.leftPressed, this::movePieceLeft);
		commands.put(ButtonPresses.rightPressed, this::movePieceRight);
		commands.put(ButtonPresses.downPressed, this::tryMovePieceDown);
		commands.put(ButtonPresses.upPressed, this::fastDrop);
		commands.put(ButtonPresses.zPressed, this::rotatePieceCounterClockwise);
		commands.put(ButtonPresses.xPressed, this::rotatePieceClockwise);
		commands.put(ButtonPresses.holdPressed, this::holdPiece);
	}

	private void fastDrop() {
		while (canPieceMoveDown()) {
			tryMovePieceDown();
		}
		if (yLocation == 0) {
			gameEnd();
		} else {
			addPieceAndSpawnNew();
		}
	}

	private int findPieceOffset() {
		int mockYLocation = yLocation + 1;
		while (gameBoard.canPieceFit(piece.getGamePiece(), xLocation, mockYLocation)) {
			mockYLocation++;
		}
		return mockYLocation-yLocation-1;
	}

	private void gameEnd() {
		stopTimer();
		scoreGui.setStatus("Game Over");
		statManager.addGameScore();
		keysDisabled = true;
	}

	private void handleKeyEvents() {
		if (!keysDisabled) {
			HashMap<ButtonPresses, Integer> mapCopy = pressedKeyMap;
			for (Map.Entry<ButtonPresses, Integer> keyEntry : mapCopy.entrySet())
			{
				pieceMoveLockDelayReset();
				if (keyEntry.getValue() == configHandler.getDAS()) {
					commands.get(keyEntry.getKey()).run();
				}
				if (keyEntry.getValue() == 0) {
					if (keyEntry.getKey() == ButtonPresses.leftPressed || keyEntry.getKey() == ButtonPresses.rightPressed 
							|| keyEntry.getKey() == ButtonPresses.downPressed)
					{
						commands.get(keyEntry.getKey()).run();
					}
					
					keyEntry.setValue(configHandler.getARR());
				}
				keyEntry.setValue(keyEntry.getValue() - 1);
			}
		}		
	}

	private void holdPiece() {
		piece.holdPiece();
		gridGui.setHoldPiece(piece.getHoldPiece());
		gridGui.setNextPiece(piece.getNextPiece());
		xLocation = 5;
		yLocation = 0;
	}

	public void keyPressed(ButtonPresses key) {
		if (key.equals(ButtonPresses.pausePressed)) {
			if(paused) {
				unPauseGame();
			} else {
				pauseGame();
			}
		}
		else if (!pressedKeyMap.containsKey(key))
		{
			pressedKeyMap.put(key, configHandler.getDAS());
		}
	}

	public void keyReleased(ButtonPresses key) {
		pressedKeyMap.remove(key);
	}

	// No bounds Checking, only use if you pre-check
	private void movePieceDown() {
		yLocation = yLocation + 1;
	}

	private void movePieceLeft() {
		if (gameBoard.canPieceFit(piece.getGamePiece(), xLocation - 1, yLocation)) {
			xLocation = xLocation - 1;
			pieceMoveLockDelayReset();
		}
	}

	private void movePieceRight() {
		if (gameBoard.canPieceFit(piece.getGamePiece(), xLocation + 1, yLocation)) {
			xLocation = xLocation + 1;
			pieceMoveLockDelayReset();
		}
	}

	private void newPiece() {
		xLocation = 5;
		yLocation = 0;
		piece.nextPiece();
		statManager.addLines(gameBoard.checkAndClearLinesGetNumCleared());
		gridGui.setNextPiece(piece.getNextPiece());
		updateGameInfoDisplay();
	}

	public void pauseGame() {
		stopTimer();
		gridGui.clearBoard();
		gridGui.clearShape();
		paused = true;
	}

	public void unPauseGame() {
		gridGui.setBoard(gameBoard.getBoard());
		startTimer();
		paused = false;
	}

	public void launchSettings() {
		settingsGui.enableDisplay();
	}

	public void launchStats() {
		statisticsWindow.enableDisplay();
	}

	private void renderGameBoard() {
		SwingUtilities.invokeLater(() -> gridGui.setBoard(gameBoard.getBoard()));
	}

	private void renderPiece() {
		if (configHandler.getShowGhostPiece()) {
			SwingUtilities.invokeLater(()-> gridGui.renderPieceWithGhost(piece.getGamePiece(), xLocation, yLocation, findPieceOffset()));		
		} else {
			SwingUtilities.invokeLater(() -> gridGui.renderPiece(piece.getGamePiece(), xLocation, yLocation));
		}
	}

	private void rotatePiece(GamePiece.rotation rotation) {
		int[][] newPiece = piece.pieceRotation(rotation);
		if (gameBoard.canPieceFit(newPiece, xLocation, yLocation)) {
			piece.rotatePiece(rotation);
			pieceMoveLockDelayReset();
		} else if (configHandler.getSuperRotation()) {
			if (gameBoard.canPieceFit(newPiece, xLocation, yLocation - 1)) {
				piece.rotatePiece(rotation);
				yLocation--;
				pieceMoveLockDelayReset();
			} else if (gameBoard.canPieceFit(newPiece, xLocation + 1, yLocation)) {
				piece.rotatePiece(rotation);
				xLocation++;
				pieceMoveLockDelayReset();
			} else if (gameBoard.canPieceFit(newPiece, xLocation - 1, yLocation)) {
				piece.rotatePiece(rotation);
				xLocation--;
				pieceMoveLockDelayReset();
			}
		}
	}

	private void rotatePieceClockwise() {
		rotatePiece(GamePiece.rotation.Clockwise);
	}

	private void rotatePieceCounterClockwise() {
		rotatePiece(GamePiece.rotation.CounterClockwise);
	}

	public void startGame() {
		statManager.setStartLevel(configHandler.getStartLevel());
		gridGui.removePieceFromDisplay();
		scoreGui.setStatus("");
		scoreGui.setHighScoreField(Integer.toString(statManager.getHighScore1()));
		gameBoard.clearGameBoard();
		renderGameBoard();
		newPiece();
		gridGui.clearHoldPiece();
		piece.clearHoldPiece();
		stopTimer();
		startTimer();
		keysDisabled = false;
		statManager.addTime(totalFrames / 60);
		totalFrames = 0;
	}

	private void tryMovePieceDown() {
		for (int i = 0; i < configHandler.getGravity(); i++) {
			if (canPieceMoveDown()) {
				movePieceDown();
			} else {
				if (yLocation == 0) {
					gameEnd();
					return;
				}
				atBottom = true;
				return;
			}
		}
	}

	private void updateGameInfoDisplay() {
		scoreGui.setScoreField(Integer.toString(statManager.getCurrentGameScore()));
		scoreGui.setTotalLinesField(Integer.toString(statManager.getCurrentTotalLines()));
		scoreGui.setLevelField(Integer.toString(statManager.getCurrentLevel()));
		scoreGui.setOneLineField(Integer.toString(statManager.getGameLineClearStats()[0]));
		scoreGui.setTwoLineField(Integer.toString(statManager.getGameLineClearStats()[1]));
		scoreGui.setThreeLineField(Integer.toString(statManager.getGameLineClearStats()[2]));
		scoreGui.setFourLineField(Integer.toString(statManager.getGameLineClearStats()[3]));
		scoreGui.setHighScoreField(Integer.toString(statManager.getHighScore1()));
	}
}