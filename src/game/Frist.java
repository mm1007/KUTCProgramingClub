package game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Game.MainCanvas;
import game.Loader.Enemy_data;
import game.Loader.Loader;
import game.Loader.Object_data;
import game.Loader.Player_data;
import game.Loader.Stage_data;

public class Frist {
	/**
	 * オブジェクトのパスを収納しています。
	 */
	public static ArrayList<String> object_path = new ArrayList<String>();
	/**
	 * 敵のパスを収納しています。
	 */
	public static ArrayList<String> enemy_path = new ArrayList<String>();
	/**
	 * プレイヤーのパスを収納しています。
	 */
	public static ArrayList<String> player_path = new ArrayList<String>();
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
	/**
	 * ステージの情報を収納しています。
	 */
	public static ArrayList<Stage_data> stage = new ArrayList<Stage_data>();

	public static MainCanvas canvas = new MainCanvas();
	public static JFrame jf = new JFrame("test");
	public static Image offImage;
	public static Paint pt;
	public static Mouse ms = new Mouse();
	public static Key ky = new Key();
	public static Developer dev;
	public static Sys_Game sg = new Sys_Game();
	public static Menu menu;

	/**
	 * プレイヤーの表示X座標
	 */
	public static int player_x = 500;
	/**
	 * プレイヤーの表示Y座標
	 */
	public static int player_y = 300;
	/**
	 * ウィンドウサイズ(横)
	 */
	public static int width = 1080;
	/**
	 * ウィンドウサイズ(縦)
	 */
	public static int height = 720;
	/**
	 * ステージサイズ(横)
	 */
	public static int stageW = 10000;
	/**
	 * ステージサイズ(縦)
	 */
	public static int stageH = 2160;
	/**
	 * データフォルダのパスがString型で収納されています。
	 */
	public static String file = new File("data").getAbsolutePath() + "\\";

	public static void main(String[] args) throws Exception {
		Loader.Load_StageList(new File(file + "stage"));
		Sys_Game.select_stage(5);
		jf.setBounds(0, 0, width, height);
		pt = new Paint();
		menu = new Menu();
		pt.setBounds(0, 0, width, height);
		pt.setBackground(Color.white);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.add(pt);
		jf.setVisible(true);
		dev = new Developer();
		addListener();
	}

	/**
	 * リスナーを追加する用のメソッドです。 新たなクラスに独自リスナーなどを実装するときはこのメソッドに追加してからお願いします。
	 */
	public static void addListener() {
		jf.addKeyListener(ky);
		pt.addMouseMotionListener(ms);
		pt.addMouseListener(ms);
		ky.addKeyListener(sg);
		ms.addMouseListener(sg);
		pt.addPaintListener(sg);
		pt.addPaintListener(menu);
		sg.addChangeListener(menu);
		pt.addPaintListener(new Collision());
	}

}
