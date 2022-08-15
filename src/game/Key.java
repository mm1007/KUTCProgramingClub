package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
	
	static boolean key[] = new boolean[KeyEvent.KEY_LAST];
	
	static int key_list[] = {
			KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE
	};

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
	}
	
}