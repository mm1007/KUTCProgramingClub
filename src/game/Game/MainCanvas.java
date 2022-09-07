package game.Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.Frist;
import game.Bullet.Bullet;
import game.Bullet.NomalBullet;
import game.Enemy.Cat;
import game.Enemy.Enemy;
import game.Loader.Enemy_data;

public class MainCanvas extends JPanel {

	int x = 10;
	int playerX, playerY;
	int enemyX, enemtY;

	public static List<Enemy> enemy = new ArrayList<Enemy>();
	public static List<Bullet> bullet = new ArrayList<Bullet>();

	public void init() {
		enemy.clear();
		for (Enemy_data data : Frist.enemy) {
			enemy.add(new Cat(data.x, data.y));
		}
	}

	public void paint_2(Graphics g) {
		int time = 0;
		int[] player_pos = {
				playerX, playerY
		};
		for (Enemy e : enemy) {
			e.set_angle();
			int[] enemy_pos = {
					Frist.enemy.get(time).x, Frist.enemy.get(time).y
			};
			e.draw(g, enemy_pos);
			if (e.attack())
				bullet.add(new NomalBullet(Frist.enemy.get(time).x, Frist.enemy.get(time).y, e.getAngle()));
			time++;
		}
		for (Bullet b : bullet) {
			b.draw(g);
		}
	}
}
