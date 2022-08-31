package game.Bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Nissan
 *
 */
abstract public class Bullet {
	public BufferedImage img;
	public int x;
	public int y;
	double angle;
	int W, H;
	int speed = 5;

	/**
	 * 玉を作成します。通常はこのクラスを継承して作成します。
	 * @param tx X座標
	 * @param ty Y座標
	 * @param tangle 角度(度)
	 */
	public Bullet(int tx, int ty, double tangle) {
		x = tx;
		y = ty;
		angle = tangle;
	}

	/**
	 * 玉の座標(配列)を返します。
	 * @return 座標X,Y
	 */
	public int[] getPos() {
		int[] data = {
				this.x, this.y
		};
		return data;
	};

	/**
	 * 角度を返します。
	 * @return 角度(度)
	 */
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
