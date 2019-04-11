import TetrisDisplayAndUtils.ButtonPresses;

public interface InputController {

	public enum ButtonPresses {
		leftPressed, rightPressed, upPressed, downPressed, xPressed, zPressed, holdPressed, pausePressed, nonePressed
	}
	
	void keyPressed(ButtonPresses p);
	
	void keyReleased(ButtonPresses p);
}
