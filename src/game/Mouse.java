package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

	private static mouse call_interface;

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
		if (call_interface != null) {
			call_interface.mouse_Pressed();
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
		if (call_interface != null) {
			call_interface.mouse_Pressed();
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
	 * マウスリスナーをセットします。
	 * @param set mouseクラス
	 */
	public static void setMouseListener(mouse set) {
		call_interface = set;
	}

	public static void removeMouseListener() {
		call_interface = null;
	}

	interface mouse {
		public void mouse_Pressed();

		public void mouse_Released();
	}

}