package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;

import javax.swing.Timer;

import game.Bullet.Bullet;
import game.Game.MainCanvas;

/**
 * 移動制御
 * @author mm1007
 *
 */
public class Movement {
	boolean jump_c = false;
	int jump_f = 0;
	static int gravity_acceleration = 1;
	static int acceleration = 0;
	Timer time;
	Timer time2;
	public static int player_x = Frist.player_x;
	public static int player_y = Frist.player_y;
	static int[][] colision = new int[Frist.stageH][Frist.stageW];
	static int[] colision_k = new int[4];

	/**
	 * 
	 */
	Movement() {
		time = new Timer(1, action_1);
		time2 = new Timer(1, action_2);
	}

	/**
	 * 
	 */
	ActionListener action_1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (Key.key[KeyEvent.VK_W] && jump_c == false && collision_check(1, 1) == true) {
					System.out.println("システム:ジャンプ開始");
					jump_f = 30;
					jump_c = true;
					time2.start();
				}
				if (Key.key[KeyEvent.VK_A] && collision_check(2, 10) == false) {
					move_obj(10, 0);
				}
				if (Key.key[KeyEvent.VK_D] && collision_check(3, 10) == false) {
					move_obj(-10, 0);
				}
				if (collision_check_full(1, acceleration + 1) == false && jump_c == false) {
					move_obj(0, -acceleration);
					acceleration += gravity_acceleration;
				} else if (collision_check_full(1, acceleration) == true) {
					if (collision_check(1, 1) != true) {
						int remain = 2;
						while (true) {
							if (collision_check(1, remain) == true) {
								move_obj(0, -remain + 1);
								break;
							}
							remain++;
						}
					}
					acceleration = 1;
				}
				if (collision_stack() == true) {
					acceleration = 0;
					move_obj(0, 1);
					//player_y -= 1;
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				System.out.println("マップ範囲外に移動しようとしている");
			}
			//System.out.println("do");
			Bullet_c();
			Frist.pt.repaint();
		}
	};

	/**
	 * 
	 */
	int[] x_y = {

			2,

			2,

			1,

			1

	};

	/**
	 * プレイヤーを移動させるためのメソッドです。x,yでどのくらい動かすか指定します。また当たり判定の確認は行いませんので、collision_checkを別途使用してください。
	 * @param x　移動させたいX値
	 * @param y 移動させたいY値
	 */
	public void move_obj(int x, int y) {
		int time = 0;
		try {
			player_x -= x;
			player_y -= y;
			for (int t = 0, i = Frist.object.size(); t < i; t++) {
				Frist.object.get(t).x += x;
				Frist.object.get(t).y += y;
			}
			for (int t = 0, i = Frist.enemy_list; t < i; t++) {
				Frist.enemy[t][1] += x;
				Frist.enemy[t][2] += y;
			}
			for (Bullet b : MainCanvas.bullet) {
				//MainCanvas.bullet.set(time,
				//new NomalBullet(b.getPos()[0] + move, b.getPos()[1], b.getAngle()));
				MainCanvas.bullet.get(time).x += x;
				MainCanvas.bullet.get(time).y += y;

				time++;
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
		}
	}

	ActionListener action_2 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				boolean i = collision_check(0, jump_f);
				if (i == false) {
					move_obj(0, jump_f);
					//player_y -= jump_f;
					jump_f -= gravity_acceleration;
				}
				if (jump_f <= 0) {
					//System.out.println("落下");
					jump_c = false;
					time2.stop();
				}
				if (i == true) {
					int jump_f_l = jump_last() / 2;
					if (jump_f_l > 0) {
						move_obj(0, jump_f_l);
						//player_y -= jump_f_l;
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

	/**
	 * 指定した方向、距離に当たり判定があるか判断します。(途中に当たり判定があるかは確認しません)
	 * @param key 方向
	 * @param move 距離
	 * @return 当たり判定がある場合trueを返します。
	 */
	public boolean collision_check(int key, int move) {
		for (int t = 0, i = colision_k[key]; t < i; t++) {
			if (key == 0 && colision[player_x + t][player_y - move] == 1) {
				return true;
			} else if (key == 1 && colision[player_x + t][player_y + colision_k[key] + move] == 1) {
				return true;
			} else if (key == 2 && colision[player_x - move][player_y + t] == 1) {
				return true;
			} else if (key == 3 && colision[player_x + colision_k[key] + move - 1][player_y + t] == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定した方向、距離に当たり判定があるか判断します。(途中に当たり判定があった場合もtrueを返します。)
	 * @param key 方向
	 * @param move 距離
	 * @return 当たり判定がある場合trueを返します。
	 */
	public boolean collision_check_full(int key, int move) {
		for (int k = 1; k < move; k++) {
			for (int t = 0, i = colision_k[key]; t < i; t++) {
				if (key == 0 && colision[player_x + t][player_y - k] == 1) {
					return true;
				} else if (key == 1 && colision[player_x + t][player_y + colision_k[key] + k] == 1) {
					return true;
				} else if (key == 2 && colision[player_x - k][player_y + t] == 1) {
					return true;
				} else if (key == 3 && colision[player_x + colision_k[key] + k - 1][player_y + t] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	int jump_last() {
		int time = 0;
		while (true) {
			for (int t = 0, i = colision_k[0]; t < i; t++) {
				if (colision[player_x + t][player_y - time] == 1) {
					//System.out.println("return:" + time);
					return time - 1;
				}
			}
			time++;
		}
	}

	/**
	 * プレイヤーが埋まっている(スタック)しているか確認します。
	 * @return　埋まっている場合はtrueを返します。
	 */
	public boolean collision_stack() {
		for (int t = 0, i = colision_k[1]; t < i; t++) {
			if (colision[player_x + t][player_y + colision_k[1] - 1] == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param g
	 */
	/*static void menu(Graphics g) {
		try {
			if (Mouse.select[0] == 0) {
				g.drawImage(ImageIO.read(new File(Frist.file + "menu.png")), 0, 0, 100, 60, Frist.pt);
			} else {
				g.drawImage(ImageIO.read(new File(Frist.file + "menu_s.png")), 0, 0, 100, 60, Frist.pt);
			}
		} catch (IOException e) {
		}
	}*/

	/**
	 * 
	 */
	void Bullet_c() {
		try {
			int time = 0;
			for (Bullet b : MainCanvas.bullet) {
				if (b.getPos()[0] > colision.length || b.getPos()[1] > colision[1].length
						|| b.getPos()[0] < -colision.length
						|| b.getPos()[1] < -colision[1].length) {
					System.out.println("システム:ボールを削除 -> " + time + "番目");
					MainCanvas.bullet.remove(time);
				}
				time++;
			}
		} catch (ConcurrentModificationException e) {

		}
	}

	/**
	 * 
	 * @param g
	 */
	static void graple(Graphics g) {
		g.drawLine(0, 0, 100, 100);
	}

}
