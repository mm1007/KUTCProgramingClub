package game.Bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract public class Bullet {
	BufferedImage img;
	int x;
	int y;
	double angle;
	int W, H;
	int speed = 20;

	public Bullet(int tx, int ty, double tangle) {
		x = tx;
		y = ty;
		angle = tangle;
	}

	public int[] getPos() {
		int[] data = {
				this.x, this.y
		};
		return data;
	};

	public double getAngle() {
		return this.angle;
	}

	public void sizeinit() {
		W = img.getWidth() / 100;
		H = img.getHeight() / 100;
	}

	abstract public void move();

	public void draw(Graphics g) {
		move();

		g.drawImage(img, x, y, null);
	}
}
