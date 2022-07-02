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
	public static int player_y = 500;
	public static int number;
	public static JFrame jf = new JFrame("test");
	public static Image offImage;

	public static void main(String[] args) throws IOException {
		BufferedReader br_obj_path = new BufferedReader(
				new FileReader("C:\\Users\\manao\\Desktop\\eclipse 2022\\workspace\\KUTC\\src\\game\\object_path.txt"));
		BufferedReader br_obj = new BufferedReader(
				new FileReader("C:\\Users\\manao\\Desktop\\eclipse 2022\\workspace\\KUTC\\src\\game\\object.txt"));
		String text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		int time = 0;
		br_obj_path.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			number = Character.getNumericValue(text.charAt(0));
			object_path[number] = text.substring(2, text.length());
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
		pt.time.start();
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.addKeyListener(pt);
		jf.add(pt);
		jf.setVisible(true);
		br_obj_path.close();
		br_obj.close();
	}
}

class Paint extends Canvas implements KeyListener, Runnable, ActionListener {
	int key[] = {
			0, 0, 0, 0
	};
	int key_list[] = {
			KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
	};
	//Thread th = new Thread(this);
	Timer time = new Timer(10, this);
	File fl = new File("");
	Image im[] = new Image[Frist.number + 1];
	Graphics gv;
	long time_s;
	int[][] colision = new int[Frist.jf.getWidth() - 100][Frist.jf.getHeight() - 100];
	int[] colision_k = new int[4];

	Paint() {
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
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		time_s = System.currentTimeMillis();
		Frist.offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		gv = Frist.offImage.getGraphics();
		for (int x = 0; x < Frist.object_list; x++) {
			//System.out.println(Frist.object[x][0] - 1 + " " + x + " " + Frist.object[x][0]);
			//im = ImageIO.read(new File(Frist.object_path[Frist.object[x][0] - 1]));
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

	@Override
	public void run() {
		/*while (true) {
			// TODO 自動生成されたメソッド・スタブ
			if (key[0] == 1) {
				Frist.player_y = Frist.player_y - 10;
			}
			if (key[1] == 1) {
				Frist.player_y = Frist.player_y + 10;
			}
			if (key[2] == 1) {
				Frist.player_x = Frist.player_x - 10;
			}
			if (key[3] == 1) {
				Frist.player_x = Frist.player_x + 10;
			}
			System.out.println("do");
			try {
				Thread.sleep(100);
				repaint();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		// TODO 自動生成されたメソッド・スタブ
		try {
			if (key[0] == 1 && collision_check(0) == false) {
				Frist.player_y = Frist.player_y - 10;
			}
			if (key[1] == 1 && collision_check(1) == false) {
				Frist.player_y = Frist.player_y + 10;
			}
			if (key[2] == 1 && collision_check(2) == false) {
				Frist.player_x = Frist.player_x - 10;
			}
			if (key[3] == 1 && collision_check(3) == false) {
				Frist.player_x = Frist.player_x + 10;
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
			System.out.println("マップ範囲外に移動しようとしている");
		}
		//System.out.println("do");
		repaint();
	}

	boolean collision_check(int key) {
		for (int t = 0; t < colision_k[key]; t++) {
			if (key == 0 && colision[Frist.player_x + t][Frist.player_y - 10] == 1) {
				return true;
			} else if (key == 1 && colision[Frist.player_x + t][Frist.player_y + colision_k[key]] == 1) {
				return true;
			} else if (key == 2 && colision[Frist.player_x - 10][Frist.player_y + t] == 1) {
				return true;
			} else if (key == 3 && colision[Frist.player_x + colision_k[key]][Frist.player_y + t] == 1) {
				return true;
			}
		}
		return false;
	}
}
