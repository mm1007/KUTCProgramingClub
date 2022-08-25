package game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Game.MainCanvas;
import game.Loader.Enemy_data;
import game.Loader.Loader;
import game.Loader.Object_data;
import game.Loader.Player_data;

public class Frist {
	//public static float[] object_scale = new float[1000];
	//public static int[][] object = new int[1000][3];
	public static ArrayList<String> object_path = new ArrayList<String>();
	public static ArrayList<String> enemy_path = new ArrayList<String>();
	public static ArrayList<String> player_path = new ArrayList<String>();
	//public static String[] object_path = new String[1000];
	//public static String[] enemy_path = new String[1000];
	//public static int[][] enemy = new int[1000][3];
	public static int enemy_list;
	public static int object_list;
	public static int player_x = 500;
	public static int player_y = 300;
	public static int number;
	public static MainCanvas canvas = new MainCanvas();
	public static JFrame jf = new JFrame("test");
	public static Image offImage;
	public static int width = 1080;
	public static int height = 720;
	public static int stageW = 5760;
	public static int stageH = 2160;
	public static String file = new File("data").getAbsolutePath() + "\\";
	public static Paint pt;
	/**
	 * オブジェクトの情報を収納しています。
	 */
	public static ArrayList<Object_data> object = new ArrayList<Object_data>();
	/**
	 * 敵の情報を収納しています。
	 */
	public static ArrayList<Enemy_data> enemy = new ArrayList<Enemy_data>();
	/**
	 * プレイヤーの情報を収納しています。
	 */
	public static ArrayList<Player_data> player = new ArrayList<Player_data>();

	public static void main(String[] args) throws IOException {
		Loader.Load_Map();
		Loader.Load_Enemy();
		Loader.Load_Player();
		jf.setBounds(0, 0, width, height);
		pt = new Paint();
		Mouse ms = new Mouse();
		Key ky = new Key();
		pt.setBounds(0, 0, width, height);
		//pt.th.start();
		pt.setBackground(Color.white);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.addKeyListener(ky);
		pt.addMouseMotionListener(ms);
		pt.addMouseListener(ms);
		jf.add(pt);
		jf.setVisible(true);
	}
}
