package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.Game.MainFlame;

class Paint extends JPanel {
	//Thread th = new Thread(this);
	File fl = new File("");
	static Image im[] = new Image[Frist.number + 1];
	Graphics gv;
	long time_s;
	Menu Menu = new Menu();
	MainFlame mc;

	Paint() {
		Movement mv = new Movement();
		//System.out.println(Frist.jf.getWidth() + " " + Frist.jf.getHeight());
		//System.out.println(Frist.object_list);
		Movement.colision_k = new int[] {
				im[0].getHeight(this), im[0].getHeight(this), im[0].getWidth(this), im[0].getWidth(this)
		};
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
		Movement.menu(gv);
		Mouse.select_c();
		g.drawImage(Frist.offImage, 0, 0, this);
		//System.out.println(System.currentTimeMillis() - time_s);
	}

	void paint_all(Graphics g) {
		time_s = System.currentTimeMillis();
		for (int x = 0, i = Frist.object_list; x < i; x++) {
			g.drawImage(Frist.object.get(x).img, Frist.object.get(x).x, Frist.object.get(x).y, this);
		}
		try {
			g.drawImage(ImageIO.read(new File(Frist.object_path[0])), Frist.player_x, Frist.player_y, this);
		} catch (IOException e) {
		}
		if (Key.key[KeyEvent.VK_SPACE]) {
			Movement.graple(g);
		}
	}
}
