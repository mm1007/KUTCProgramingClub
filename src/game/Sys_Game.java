/**
 *
 */
package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

import game.Paint.paintListener;
import game.Loader.Loader;

/**
 * @author mm1007
 *
 */
public class Sys_Game extends Frist implements keyListener, mouseListener, paintListener {

	/**
	 * ゲームが動作しているかどうかを格納しています。
	 */
	public static boolean Start_Game;

	protected static EventListenerList ELL = new EventListenerList();

	/**
	 * ゲームを開始します。
	 */
	public static void start() {
		Frist.pt.mv.time.start();
		Start_Game = true;
		for (ChangeStatus cs : ELL.getListeners(ChangeStatus.class)) {
			cs.start();
		}
	}

	/**
	 * ゲームを一時中断します。
	 */
	public static void pause() {
		Frist.pt.mv.time.stop();
		Start_Game = false;
		for (ChangeStatus cs : ELL.getListeners(ChangeStatus.class)) {
			cs.pause();
		}
		Frist.pt.repaint();
	}

	/**
	 * ステージを選択しロードします。
	 * @param stage_index ステージ番号
	 * @throws IOException
	 */
	public static void select_stage(int stage_index) throws Exception {
		Loader.Load_Map(stage.get(stage_index).StagePath);
		Loader.Load_Enemy(stage.get(stage_index).StagePath);
		Loader.Load_Player(stage.get(stage_index).StagePath);
	}

	/**
	 * チェンジリスナーを追加します。
	 * @param add チェンジリスナー
	 */
	public void addChangeListener(ChangeStatus add) {
		ELL.add(ChangeStatus.class, add);
	}

	/**
	 * チェンジリスナーを削除します。
	 * @param remove チェンジリスナー
	 */
	public void removeChangeListener(ChangeStatus remove) {
		ELL.remove(ChangeStatus.class, remove);
	}

	@Override
	public void keyPressed() {
		// TODO 自動生成されたメソッド・スタブ
		if (Key.key[KeyEvent.VK_ESCAPE]) {
			if (Start_Game) {
				pause();
				Start_Game = false;
			} else {
				start();
				Start_Game = true;
			}
		}
	}

	@Override
	public void keyReleased() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void repainted(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouse_Pressed() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouse_Released() {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * システムに変更があった場合などに呼び出されるメソッドなどがあります。
	 * @author mm1007
	 *
	 */
	interface ChangeStatus extends EventListener {

		/**
		 * ゲームが開始された時に呼び出されます。
		 */
		public void start();

		/**
		 * ゲームが知事停止されたときに呼び出されます。
		 */
		public void pause();

	}

}
