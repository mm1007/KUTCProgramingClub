package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	static int x;
	static int y;
	int start = 0;
	MouseEvent e1;

	public static int[] select = {
			0
	};

	public static int[] mouse_check = {
			0, 0
	};

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int x = e.getX() - 10;
		int y = e.getY() - 30;
		System.out.println(x + " " + y);
		if (e.getButton() == 1) {
			mouse_check[0] = 1;
			if (x <= 100 && y <= 60) {
				Movement.Move_check = Movement.Move_check == 1 ? 0 : 1;
			}
		} else if (e.getButton() == 3) {
			mouse_check[1] = 1;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (e.getButton() == 1) {
			mouse_check[0] = 0;
		} else if (e.getButton() == 3) {
			mouse_check[1] = 0;
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

	public static void select_c() {
		// TODO 自動生成されたメソッド・スタブ
		if (x <= 100 && y <= 60) {
			select[0] = 1;
		} else {
			select[0] = 0;
		}
	}

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

}
