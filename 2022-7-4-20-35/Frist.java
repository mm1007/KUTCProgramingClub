package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Frist {
	public static int[][] object = new int[1000][3];
	public static String[] object_path = new String[1000];
	public static int object_list;
	public static int player_x = 500;
	public static int player_y = 300;
	public static int number;
	public static JFrame jf = new JFrame("test");
	public static Image offImage;

	public static void main(String[] args) throws IOException {
		String file = new File("src\\game").getAbsolutePath() + "\\";
		BufferedReader br_obj_path = new BufferedReader(
				new FileReader(file + "object_path.txt"));
		BufferedReader br_obj = new BufferedReader(
				new FileReader(file + "object.txt"));
		String text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		int time = 0;
		br_obj_path.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			number = Character.getNumericValue(text.charAt(0));
			object_path[number] = file + text.substring(2, text.length());
		}
		while ((text = br_obj.readLine()) != null) {
			int first_c = text.indexOf(",");
			int second_c = text.indexOf(",", first_c + 1);
			object[time][0] = Character.getNumericValue(text.charAt(0));
			System.out.println(object[time][0]);
			object[time][1] = Integer.parseInt(text.substring(first_c + 1, second_c));
			object[time][2] = Integer.parseInt(text.substring(second_c + 1, text.length()));
			time++;
		}
		jf.setBounds(0, 0, 1080, 720);
		Paint pt = new Paint();
		pt.setBounds(0, 0, 1080, 720);
		//pt.th.start();
		pt.setBackground(Color.white);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.addKeyListener(pt);
		jf.add(pt);
		jf.setVisible(true);
		br_obj_path.close();
		br_obj.close();
	}
}

class Paint extends Canvas implements KeyListener {
	int key[] = {
			0, 0, 0, 0
	};
	int key_list[] = {
			KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
	};
	//Thread th = new Thread(this);
	File fl = new File("");
	Image im[] = new Image[Frist.number + 1];
	Graphics gv;
	long time_s;
	int[][] colision = new int[Frist.jf.getWidth() * 2][Frist.jf.getHeight() * 2];
	int[] colision_k = new int[4];
	int player_x = Frist.player_x;
	int player_y = Frist.player_y;
	boolean jump_c = false;
	int jump_f = 0;
	Timer time2;

	Paint() {
		Timer time = new Timer(10, action_1);
		time2 = new Timer(10, action_2);
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
		for (int z = 0; z < Frist.object_list; z++) {
			for (int x = 0; x < im[Frist.object[z][0] - 1].getWidth(this); x++) {
				for (int y = 0; y < im[Frist.object[z][0] - 1].getHeight(this); y++) {
					colision[x + Frist.object[z][1]][y + Frist.object[z][2]] = 1;
				}
			}
			System.out.println("end");
		}
		colision_k = new int[] {
				im[0].getHeight(this), im[0].getHeight(this), im[0].getWidth(this), im[0].getWidth(this)
		};
		time.start();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		time_s = System.currentTimeMillis();
		Frist.offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		gv = Frist.offImage.getGraphics();
		for (int x = 0; x < Frist.object_list; x++) {
			gv.drawImage(im[Frist.object[x][0] - 1], Frist.object[x][1], Frist.object[x][2], this);
		}
		try {
			gv.drawImage(ImageIO.read(new File(Frist.object_path[0])), Frist.player_x, Frist.player_y, this);
		} catch (IOException e) {
		}
		g.drawImage(Frist.offImage, 0, 0, this);
		//dSystem.out.println(System.currentTimeMillis() - time_s);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		for (int x = 0; x <= 3; x++) {
			if (e.getKeyCode() == key_list[x]) {
				key[x] = 1;
				System.out.println(key_list[x]);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		for (int x = 0; x <= 3; x++) {
			if (e.getKeyCode() == key_list[x]) {
				key[x] = 0;
			}
		}
	}

	public ActionListener action_1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (key[0] == 1 && jump_c == false && collision_check(1) == true) {
					jump_f = 30;
					jump_c = true;
					time2.start();
				}
				/*if (key[0] == 1 && collision_check(0) == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][2] = Frist.object[t][2] + 20;
					}
					player_y = player_y - 20;
				}
				/*if (key[1] == 1 && collision_check(1) == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][2] = Frist.object[t][2] - 10;
					}
					player_y = player_y + 10;
				}*/
				if (key[2] == 1 && collision_check(2) == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][1] = Frist.object[t][1] + 10;
					}
					player_x = player_x - 10;
				}
				if (key[3] == 1 && collision_check(3) == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][1] = Frist.object[t][1] - 10;
					}
					player_x = player_x + 10;
				}
				if (collision_check(1) == false && jump_c == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][2] = Frist.object[t][2] - 10;
					}
					player_y = player_y + 10;
				}
				if (collision_stack() == true) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][2] = Frist.object[t][2] + 1;
					}
					player_y = player_y - 1;
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				System.out.println("マップ範囲外に移動しようとしている");
			}
			//System.out.println("do");
			repaint();
		}
	};

	public ActionListener action_2 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (collision_check(0) == false) {
					for (int t = 0; t < Frist.object.length; t++) {
						Frist.object[t][2] = Frist.object[t][2] + jump_f;
					}
					player_y = player_y - jump_f;
					jump_f = jump_f - 1;
				}
				if (collision_check(0) == true || jump_f <= 0) {
					jump_c = false;
					time2.stop();
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				jump_c = false;
				time2.stop();
			}
		}
	};

	boolean collision_check(int key) {
		for (int t = 0; t < colision_k[key]; t++) {
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

	boolean collision_stack() {
		for (int t = 0; t < colision_k[1]; t++) {
			if (colision[player_x + t][player_y + colision_k[1] - 1] == 1) {
				return true;
			}
		}
		return false;
	}
}
