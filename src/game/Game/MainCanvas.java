package game.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.Frist;
import game.Bullet.Bullet;
import game.Bullet.NomalBullet;
import game.Enemy.Cat;
import game.Enemy.Enemy;

public class MainCanvas extends JPanel {
	private Graphics ct;
	private Image buf;

	int x = 10;
	int playerX, playerY;
	int enemyX, enemtY;

	public static List<Enemy> enemy = new ArrayList<Enemy>();
	public static List<Bullet> bullet = new ArrayList<Bullet>();

	public MainCanvas() {
		//System.out.println(Frist.enemy_list);
		setBackground(Color.white);
		for (int t = 0, i = Frist.enemy_list; t < i; t++) {
			enemy.add(new Cat(Frist.enemy[t][1], Frist.enemy[t][2]));
		}
	}

	public void init() {

	}

	public void paint_2(Graphics g) {
		int time = 0;
		int[] player_pos = {
				playerX, playerY
		};
		for (Enemy e : enemy) {
			e.move();
			int[] enemy_pos = {
					Frist.enemy[time][1], Frist.enemy[time][2]
			};
			e.draw(g, player_pos, enemy_pos);
			if (e.attack())
				bullet.add(new NomalBullet(Frist.enemy[time][1], Frist.enemy[time][2], e.getAngle()));
			time++;
		}
		for (Bullet b : bullet) {
			b.draw(g);
		}
	}
}
