import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import TetrisDisplayAndUtils.ButtonPresses;
import TetrisDisplayAndUtils.ButtonPresses;
public class KeyBoardListener {

	private boolean isPaused = false;
	private InputController controller;
	
	public KeyBoardListener(InputController controller) {
		this.controller = controller;
		keyBoardConfig();
	}
	
	private void keyBoardConfig()
	{
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (KeyEvent.KEY_PRESSED == e.getID()) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						controller.keyPressed(InputController.ButtonPresses.leftPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						controller.keyPressed(InputController.ButtonPresses.rightPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						controller.keyPressed(InputController.ButtonPresses.upPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						controller.keyPressed(InputController.ButtonPresses.downPressed);
					} else if (e.getKeyChar() == 'x') {
						controller.keyPressed(InputController.ButtonPresses.xPressed);
					} else if (e.getKeyChar() == 'z') {
						controller.keyPressed(InputController.ButtonPresses.zPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						controller.keyPressed(InputController.ButtonPresses.holdPressed);
					}else if (e.getKeyChar() == 'w') {
					
						if (isPaused){
							controller.keyPressed(InputController.ButtonPresses.pausePressed);
							isPaused = false;
						}else{
							controller.keyPressed(InputController.ButtonPresses.pausePressed);
							isPaused = true;
						}
					}
				}
				if (KeyEvent.KEY_RELEASED == e.getID()) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						controller.keyReleased(InputController.ButtonPresses.leftPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						controller.keyReleased(InputController.ButtonPresses.rightPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						controller.keyReleased(InputController.ButtonPresses.upPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						controller.keyReleased(InputController.ButtonPresses.downPressed);
					} else if (e.getKeyChar() == 'x') {
						controller.keyReleased(InputController.ButtonPresses.xPressed);
					} else if (e.getKeyChar() == 'z') {
						controller.keyReleased(InputController.ButtonPresses.zPressed);
					} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						controller.keyReleased(InputController.ButtonPresses.holdPressed);
					}
				}
				return false;
			}
		});
	}
}
