package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
	static int key[] = {
			0, 0, 0, 0, 0
	};
	static int key_list[] = {
			KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE
	};

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		for (int x = 0; x <= 3; x++) {
			if (e.getKeyCode() == key_list[x]) {
				key[x] = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		for (int x = 0; x <= 3; x++) {
			if (e.getKeyCode() == key_list[x]) {
				key[x] = 0;
			}
		}
	}
}
