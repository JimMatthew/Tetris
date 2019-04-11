import java.io.FileInputStream;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ConfigHandler implements TetrisConfiguration {

	private int[] frameLevelSpeed = { 53, 49, 45, 41, 37, 33, 28, 22, 17, 11, 10, 9, 8, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2 };
	private boolean showGhostPiece = true;
	private boolean superRotation = true;
	private int DAS = 10;
	private int ARR = 1;
	private int lockDelay = 25;
	private int ARE = 5;
	private int gravity = 1;
	private int startLevel = 0;
	private int blockSize = 30;
	private String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private String appConfigPath = rootPath + "app.properties";
	private Properties appProps = new Properties();

	public ConfigHandler() {
		loadConfigFile();
	}

	private void saveConfig() {
		try {
			appProps.setProperty("superRotation", Boolean.toString(superRotation));
			appProps.setProperty("lockDelay", Integer.toString(lockDelay));
			appProps.setProperty("DAS", Integer.toString(DAS));
			appProps.setProperty("speedAfterDAS", Integer.toString(ARR));
			appProps.setProperty("showGhostPiece", Boolean.toString(showGhostPiece));
			appProps.setProperty("frameLevelSpeed", Arrays.toString(frameLevelSpeed));
			appProps.setProperty("ARE", Integer.toString(ARE));
			appProps.setProperty("gravity", Integer.toString(gravity));
			appProps.setProperty("startLevel", Integer.toString(startLevel));
			appProps.setProperty("blockSize", Integer.toString(blockSize));
			appProps.store(new FileWriter(appConfigPath), "Tetris Configuration Data");
		} catch (IOException e) {

		}
	}

	private void loadConfigFile() {
		try {
			appProps.load(new FileInputStream(appConfigPath));
			parseConfigFile();
		} catch (IOException e) {
			saveConfig();
		}
	}

	private void parseConfigFile() {
		superRotation = Boolean.parseBoolean(appProps.getProperty("superRotation", "true"));
		DAS = Integer.parseInt(appProps.getProperty("DAS", Integer.toString(DAS)));
		lockDelay = Integer.parseInt(appProps.getProperty("lockDelay", Integer.toString(lockDelay)));
		showGhostPiece = Boolean.parseBoolean(appProps.getProperty("showGhostPiece", "true"));
		ARR = Integer.parseInt(appProps.getProperty("speedAfterDAS", "1"));
		ARE = Integer.parseInt(appProps.getProperty("ARE", Integer.toString(ARE)));
		gravity = Integer.parseInt(appProps.getProperty("gravity", Integer.toString(gravity)));
		startLevel = Integer.parseInt(appProps.getProperty("startLevel", Integer.toString(startLevel)));
		blockSize = Integer.parseInt(appProps.getProperty("blockSize", Integer.toString(blockSize)));
		String s = appProps.getProperty("frameLevelSpeed", Arrays.toString(frameLevelSpeed));
		frameLevelSpeed = Arrays.stream(s.substring(1, s.length() - 1).split(",")).map(String::trim)
				.mapToInt(Integer::parseInt).toArray();
	}

	@Override
	public void saveCurrentSettings() {
		saveConfig();
	}

	@Override
	public boolean getSuperRotation() {
		return superRotation;
	}

	@Override
	public int getLockDelay() {
		return lockDelay;
	}

	@Override
	public int getDAS() {
		return DAS;
	}

	@Override
	public int getARR() {
		return ARR;
	}

	@Override
	public int[] getFrameLevelSpeed() {
		return frameLevelSpeed;
	}

	@Override
	public boolean getShowGhostPiece() {
		return showGhostPiece;
	}

	@Override
	public int getARE() {
		return ARE;
	}

	@Override
	public int getGravity() {
		return gravity;
	}

	@Override
	public int getStartLevel() {
		return startLevel;
	}
	
	@Override
	public int getBlockSize()
	{
		return blockSize;
	}

	@Override
	public void setShowGhostPiece(boolean showGhostPiece) {
		this.showGhostPiece = showGhostPiece;
	}

	@Override
	public void setSuperRotation(boolean superRotation) {
		this.superRotation = superRotation;
	}

	@Override
	public void setDAS(int dAS) {
		DAS = dAS;
	}

	@Override
	public void setLockDelay(int lockDelay) {
		this.lockDelay = lockDelay;
	}

	@Override
	public void setARE(int aRE) {
		ARE = aRE;
	}

	@Override
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	@Override
	public void setARR(int ARR) {
		this.ARR = ARR;
	}

	@Override
	public void setStartLevel(int level) {
		this.startLevel = level;
	}

	@Override
	public void setBlockSize(int blockSize)
	{
		this.blockSize = blockSize;
	}
}
