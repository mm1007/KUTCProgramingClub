package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import game.Bullet.Bullet;
import game.Bullet.NomalBullet;
import game.Game.MainCanvas;

public class Movement {
	boolean jump_c = false;
	int jump_f = 0;
	Timer time;
	Timer time2;
	public static int player_x = Frist.player_x;
	public static int player_y = Frist.player_y;
	static int[][] colision = new int[Frist.jf.getWidth() * 2][Frist.jf.getHeight() * 2];
	static int[] colision_k = new int[4];
	static int Move_check = 1;

	Movement() {
		time = new Timer(10, action_1);
		time2 = new Timer(10, action_2);
	}

	public ActionListener action_1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (move_c() == true) {
					if (Key.key[0] == 1 && jump_c == false && collision_check(1) == true) {
						jump_f = 30;
						jump_c = true;
						time2.start();
					}
					if (Key.key[2] == 1 && collision_check(2) == false) {
						move_obj(2, 10);
						player_x -= 10;
					}
					if (Key.key[3] == 1 && collision_check(3) == false) {
						move_obj(3, -10);
						player_x += 10;
					}
					if (collision_check(1) == false && jump_c == false) {
						move_obj(1, -15);
						player_y += 15;
					}
					if (collision_stack() == true) {
						x_y[0][1] = 1;
						move_obj(0, 1);
						player_y -= 1;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				System.out.println("マップ範囲外に移動しようとしている");
			}
			//System.out.println("do");
			Bullet_c();
			Frist.pt.repaint();
		}
	};

	int[][] x_y = {
			{
					2, 0
			}, {
					2, -15
			}, {
					1, +10
			}, {
					1, -10
			}
	};

	void move_obj(int key, int move) {
		int time = 0;
		try {
			for (int t = 0, i = Frist.object.length; t < i; t++) {
				Frist.object[t][x_y[key][0]] += move;
			}
			for (int t = 0, i = Frist.enemy_list; t < i; t++) {
				Frist.enemy[t][x_y[key][0]] += move;
			}
			for (Bullet b : MainCanvas.bullet) {
				if (key == 2 || key == 3) {
					MainCanvas.bullet.set(time,
							new NomalBullet(b.getPos()[0] + move, b.getPos()[1], b.getAngle()));
				}
				if (key == 0 || key == 1) {
					MainCanvas.bullet.set(time,
							new NomalBullet(b.getPos()[0], b.getPos()[1] + move, b.getAngle()));
				}
				time++;
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
		}
	}

	public ActionListener action_2 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				boolean i = collision_check(0);
				if (i == false) {
					x_y[0][1] = jump_f;
					move_obj(0,jump_f);
					player_y -= jump_f;
					jump_f -= 1;
				}
				if (jump_f <= 0) {
					jump_c = false;
					time2.stop();
				}
				if (i == true) {
					int jump_f_l = jump_last() / 2;
					if (jump_f_l > 0) {
						x_y[0][1] = jump_f_l;
						move_obj(0,jump_f_l);
						player_y -= jump_f_l;
					} else {
						jump_c = false;
						time2.stop();
					}
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				jump_c = false;
				time2.stop();
			}
		}
	};

	boolean collision_check(int key) {
		for (int t = 0, i = colision_k[key]; t < i; t++) {
			if (key == 0 && colision[player_x + t][player_y - jump_f] == 1) {
				return true;
			} else if (key == 1 && colision[player_x + t][player_y + colision_k[key]] == 1) {
				return true;
			} else if (key == 2 && colision[player_x - 10][player_y + t] == 1) {
				return true;
			} else if (key == 3 && colision[player_x + colision_k[key]][player_y + t] == 1) {
				return true;
			}
		}
		return false;
	}

	int jump_last() {
		int time = 0;
		while (true) {
			for (int t = 0, i = colision_k[0]; t < i; t++) {
				if (colision[player_x + t][player_y - time] == 1) {
					System.out.println("return:" + time);
					return time - 1;
				}
			}
			time++;
		}
	}

	boolean collision_stack() {
		for (int t = 0, i = colision_k[1]; t < i; t++) {
			if (colision[player_x + t][player_y + colision_k[1] - 1] == 1) {
				return true;
			}
		}
		return false;
	}

	static void menu(Graphics g) {
		try {
			if (Mouse.select[0] == 0) {
				g.drawImage(ImageIO.read(new File(Frist.file + "menu.png")), 0, 0, 100, 60, Frist.pt);
			} else {
				g.drawImage(ImageIO.read(new File(Frist.file + "menu_s.png")), 0, 0, 100, 60, Frist.pt);
			}
		} catch (IOException e) {
		}
	}

	boolean move_c() {
		if (Move_check == 1) {
			return true;
		} else {
			return false;
		}
	}

	void Bullet_c() {
		try {
			int time = 0;
			for (Bullet b : MainCanvas.bullet) {
				if (b.getPos()[0] > colision.length || b.getPos()[1] > colision[1].length
						|| b.getPos()[0] < -colision.length
						|| b.getPos()[1] < -colision[1].length) {
					System.out.println("ボールを削除:" + time + "番目");
					MainCanvas.bullet.remove(time);
				}
				time++;
			}
		} catch (ConcurrentModificationException e) {

		}
	}

	static void graple(Graphics g) {
		g.drawLine(0, 0, 100, 100);
	}
}
