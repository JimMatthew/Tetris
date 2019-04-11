
public interface TetrisConfiguration {

	void saveCurrentSettings();

	boolean getSuperRotation();

	int getLockDelay();

	int getDAS();

	int getARR();

	int[] getFrameLevelSpeed();

	boolean getShowGhostPiece();

	int getARE();

	int getGravity();

	int getStartLevel();

	int getBlockSize();

	void setShowGhostPiece(boolean showGhostPiece);

	void setSuperRotation(boolean superRotation);

	void setDAS(int dAS);

	void setLockDelay(int lockDelay);

	void setARE(int aRE);

	void setGravity(int gravity);

	void setARR(int ARR);

	void setStartLevel(int level);

	void setBlockSize(int blockSize);

}