package game.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Movement;

abstract public class Enemy {
	BufferedImage img;
	int x, y;
	int W, H;
	static double targetAngle;
	int reload;
	int reload_between;
	char dir = 'l';

	public Enemy(int tx, int ty) {
		x = tx;
		y = ty;
		reload = 0;
	}

	public void sizeinit() {
		W = img.getWidth();
		H = img.getHeight();
	}

	public void set_angle() {
		targetAngle = Math.atan2(Movement.player_y - this.y, Movement.player_x - this.x);
	}

	abstract public boolean attack();

	public int[] getPos() {
		int[] data = {
				this.x, this.y
		};
		return data;
	};

	public double getAngle() {
		return this.targetAngle;
	}

	public void draw(Graphics g, int enemy_pos[]) {
		g.drawImage(img, enemy_pos[0], enemy_pos[1], null);
	}
}

//参考https://uguisu.skr.jp/html/java4.html