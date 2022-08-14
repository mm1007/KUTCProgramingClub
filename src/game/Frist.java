package game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class Frist {
	public static int[][] object = new int[1000][3];
	public static String[] object_path = new String[1000];
	public static int[][] enemy = new int[1000][3];
	public static int enemy_list;
	public static int object_list;
	public static int player_x = 500;
	public static int player_y = 300;
	public static int number;
	public static JFrame jf = new JFrame("test");
	public static Image offImage;
	public static int width = 1080;
	public static int height = 720;
	public static String file = new File("src\\game").getAbsolutePath() + "\\";
	public static Paint pt;

	public static void main(String[] args) throws IOException {
		Map_Loader.Load_Map();
		jf.setBounds(0, 0, width, height);
		pt = new Paint();
		Mouse ms = new Mouse();
		Key ky = new Key();
		pt.setBounds(0, 0, width, height);
		//pt.th.start();
		pt.setBackground(Color.white);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.addKeyListener(ky);
		jf.addMouseMotionListener(ms);
		jf.addMouseListener(ms);
		jf.add(pt);
		jf.setVisible(true);
	}
}
