import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StatManager implements TetrisStatistics {

	private StatObject stat;
	private int gameOneLines = 0;
	private int gameTwoLines = 0;
	private int gameThreeLines = 0;
	private int gameFourLines = 0;
	private int gameTotalLines = 0;
	private int gameLevel = 0;
	private int gameScore = 0;
	private int startLevelOffset = 0;

	public StatManager() {
		loadStats();
	}

	private StatObject loadStats() {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("c:\\temp\\tetrisdatafile.dat"));
			this.stat = (StatObject) (input.readObject());
			input.close();
		} catch (Exception e) {
			stat = new StatObject();
			System.out.println("error loading stat file");
		}
		return stat;
	}

	private void saveStats() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream("c:\\temp\\tetrisdatafile.dat", false));
			output.writeObject(stat);
			output.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void addGameScore(int score) {
		stat.lifeTimeScore = stat.lifeTimeScore + score;
		if (stat.highScore1 < score) {
			stat.highScore1 = score;
		} else if (stat.highScore2 < score) {
			stat.highScore2 = score;
		} else if (stat.highScore3 < score) {
			stat.highScore3 = score;
		}
		saveStats();
		clearCurrentGameStats();
	}

	@Override
	public void addGameScore() {
		addGameScore(gameScore);
	}

	@Override
	public void addLines(int lines) {
		stat.lifetimeLines = stat.lifetimeLines + lines;
		int multiplier = 0;
		switch (lines) {
		case 1:
			multiplier = 40;
			stat.oneLines++;
			gameOneLines++;
			break;
		case 2:
			multiplier = 100;
			stat.twoLines++;
			gameTwoLines++;
			break;
		case 3:
			multiplier = 300;
			stat.threeLines++;
			gameThreeLines++;
			break;
		case 4:
			multiplier = 1200;
			stat.fourLines++;
			gameFourLines++;
			break;
		case 0:
			break;
		}
		gameTotalLines = gameTotalLines + lines;
		gameScore = gameScore + ((gameLevel + 1) * multiplier);
		levelChecker();
		saveStats();
	}

	private void levelChecker() {

		if (gameTotalLines % 10 == 0 && gameTotalLines != 0) {
			gameLevel = ((gameTotalLines / 10) - 1) + startLevelOffset;
		}
	}

	@Override
	public void clearCurrentGameStats() {
		gameOneLines = 0;
		gameTwoLines = 0;
		gameThreeLines = 0;
		gameFourLines = 0;
		gameScore = 0;
		gameLevel = startLevelOffset;
		gameTotalLines = 0;
	}

	@Override
	public int getHighScore1() {
		return stat.highScore1;
	}

	@Override
	public int getHighScore2() {
		return stat.highScore2;
	}

	@Override
	public int getHighScore3() {
		return stat.highScore3;
	}

	@Override
	public int getCurrentLevel() {
		return gameLevel;
	}

	@Override
	public int getCurrentGameScore() {
		return gameScore;
	}

	@Override
	public int getCurrentTotalLines() {
		return gameTotalLines;
	}

	@Override
	public void setStartLevel(int level) {
		startLevelOffset = level;
		gameLevel = level;
	}

	@Override
	public int getLifeTimeLines() {
		return stat.lifetimeLines;
	}

	@Override
	public int[] getLifetimeLineClearStats() {
		int[] lineClears = new int[4];
		lineClears[0] = stat.oneLines;
		lineClears[1] = stat.twoLines;
		lineClears[2] = stat.threeLines;
		lineClears[3] = stat.fourLines;
		return lineClears;
	}

	@Override
	public int[] getGameLineClearStats() {
		int[] lineClears = new int[4];
		lineClears[0] = gameOneLines;
		lineClears[1] = gameTwoLines;
		lineClears[2] = gameThreeLines;
		lineClears[3] = gameFourLines;
		return lineClears;
	}

	@Override
	public int getTotalTimeInSeconds() {
		return stat.lifetimeTime;
	}

	@Override
	public void addTime(int seconds) {
		stat.lifetimeTime = stat.lifetimeTime + seconds;
		saveStats();
	}
}
