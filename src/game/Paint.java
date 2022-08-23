package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;

import game.Game.MainFlame;

public class Paint extends JPanel {
	//Thread th = new Thread(this);
	File fl = new File("");
	public static Image im[] = new Image[Frist.number + 1];
	Graphics gv;
	long time_s;
	Menu Menu = new Menu();
	MainFlame mc;

	Paint() {
		Movement mv = new Movement();
		mc = new MainFlame();
		mv.time.start();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paintComponent(Graphics g) {
		Frist.offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		gv = Frist.offImage.getGraphics();
		paint_all(gv);
		Menu.O_Menu(gv);
		mc.canvas.paint_2(gv);
		//Movement.menu(gv);
		//Mouse.select_c();
		g.drawImage(Frist.offImage, 0, 0, this);
		//System.out.println(System.currentTimeMillis() - time_s);
	}

	void paint_all(Graphics g) {
		time_s = System.currentTimeMillis();
		for (int x = 0, i = Frist.object_list; x < i; x++) {
			g.drawImage(Frist.object.get(x).img, Frist.object.get(x).x, Frist.object.get(x).y, this);
		}
		g.drawImage(Frist.object.get(0).img, Frist.player_x, Frist.player_y, this);
		if (Key.key[KeyEvent.VK_SPACE]) {
			Movement.graple(g);
		}
	}
}
