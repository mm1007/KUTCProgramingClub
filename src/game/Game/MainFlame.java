package game.Game;

import java.awt.Image;

import javax.swing.JFrame;

public class MainFlame extends JFrame {
	public static final int WIDTH = 960;
	public static final int HEIGHT = 640;

	public static Image buf;

	public static final String path = System.getProperty("user.dir");

	public MainCanvas canvas = new MainCanvas();

	public MainFlame() {
		System.out.println(path);
		setTitle("アクションゲーム");
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(canvas);
	}
}
