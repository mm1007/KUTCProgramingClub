package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class Key implements KeyListener {

	static boolean key[] = new boolean[KeyEvent.KEY_LAST];

	protected EventListenerList ELL = new EventListenerList();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
		Log.output_Log(0, "キープレス", "キー:" + e.getKeyCode());
		for (keyListener kL : ELL.getListeners(keyListener.class)) {
			kL.keyPressed();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
		Log.output_Log(0, "キーリリース", "キー:" + e.getKeyCode());
		for (keyListener kL : ELL.getListeners(keyListener.class)) {
			kL.keyReleased();
		}
	}
	
	/**
	 * キーリスナー追加
	 * @param add 追加するクラス
	 */
	public void addKeyListener(keyListener add) {
		ELL.add(keyListener.class, add);
	}

	/**
	 * キーリスナー削除
	 * @param add 削除するクラス
	 */
	public void removeKeyListener(keyListener remove) {
		ELL.remove(keyListener.class, remove);
	}

}

interface keyListener extends EventListener {

	public void keyPressed();

	public void keyReleased();

}