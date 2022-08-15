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
	Image im[] = new Image[Frist.number + 1];
	Graphics gv;
	long time_s;
	Menu Menu = new Menu();
	MainFlame mc;

	Paint() {
		Movement mv = new Movement();
		System.out.println(Frist.jf.getWidth() + " " + Frist.jf.getHeight());
		for (int t = 0; t <= Frist.number; t++) {
			try {
				im[t] = ImageIO.read(new File(Frist.object_path[t]));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			System.out.println(t + ":" + Frist.object_path[t]);
			//offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		}
		System.out.println(Frist.object_list);
		for (int z = 0, i = Frist.object_list; z < i; z++) {
			for (int x = 0; x < im[Frist.object[z][0] - 1].getWidth(this); x++) {
				for (int y = 0; y < im[Frist.object[z][0] - 1].getHeight(this); y++) {
					Movement.colision[x + Frist.object[z][1]][y + Frist.object[z][2]] = 1;
				}
			}
			System.out.println("end");
		}
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
		//dSystem.out.println(System.currentTimeMillis() - time_s);
	}

	void paint_all(Graphics g) {
		time_s = System.currentTimeMillis();
		for (int x = 0, i = Frist.object_list; x < i; x++) {
			g.drawImage(im[Frist.object[x][0] - 1], Frist.object[x][1], Frist.object[x][2], this);
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
