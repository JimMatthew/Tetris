
public interface TetrisStatistics {

	void addGameScore(int score);

	void addGameScore();

	void addLines(int lines);

	void clearCurrentGameStats();

	int getHighScore1();

	int getHighScore2();

	int getHighScore3();

	int getCurrentLevel();

	int getCurrentGameScore();

	int getCurrentTotalLines();

	void setStartLevel(int level);

	int getLifeTimeLines();

	int[] getLifetimeLineClearStats();

	int[] getGameLineClearStats();

	int getTotalTimeInSeconds();

	void addTime(int seconds);

}