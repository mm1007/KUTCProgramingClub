package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * 
 * @author mm1007
 *
 */
public class Mouse implements MouseListener, MouseMotionListener {

	static int x;
	static int y;

	protected static boolean mousePress[] = new boolean[10];
	protected static int[][] mouse_locate = new int[10][2];

	protected EventListenerList ELL = new EventListenerList();

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int x = e.getX();
		int y = e.getY();
		Log.output_Log(0, "マウスプレス", "ボタン:" + e.getButton() + " x:" + x + " y:" + y);
		//System.out.println("システム:マウスプレス -> ボタン:" + e.getButton() + " x:" + x + " y:" + y);
		mousePress[e.getButton()] = true;
		mouse_locate[e.getButton()][0] = x;
		mouse_locate[e.getButton()][1] = y;
		for (mouseListener mL : ELL.getListeners(mouseListener.class)) {
			mL.mouse_Pressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int x = e.getX();
		int y = e.getY();
		Log.output_Log(0, "マウスリリース", "ボタン:" + e.getButton() + " x:" + x + " y:" + y);
		//System.out.println("システム:マウスリリース -> ボタン:" + e.getButton() + " x:" + x + " y:" + y);
		mousePress[e.getButton()] = false;
		mouse_locate[e.getButton()][0] = x;
		mouse_locate[e.getButton()][1] = y;
		for (mouseListener mL : ELL.getListeners(mouseListener.class)) {
			mL.mouse_Released(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	/*public static void select_c() {
		// TODO 自動生成されたメソッド・スタブ
		if (x <= 100 && y <= 60) {
			select[0] = 1;
		} else {
			select[0] = 0;
		}
	}*/

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		x = e.getX() - 10;
		y = e.getY() - 30;
	}

	/**
	 * マウスリスナー追加
	 * @param set 追加するクラス
	 */
	public void addMouseListener(mouseListener add) {
		this.ELL.add(mouseListener.class, add);
	}

	/**
	 * マウスリスナー削除
	 * @param remove　削除するクラス
	 */
	public void removeMouseListener(mouseListener remove) {
		this.ELL.remove(mouseListener.class, remove);
	}

}

interface mouseListener extends EventListener {
	public void mouse_Pressed(MouseEvent e);

	public void mouse_Released(MouseEvent e);
}