package game.Bullet;

import java.io.File;

import javax.imageio.ImageIO;

import game.Game.MainFlame;

public class NomalBullet extends Bullet {
	public NomalBullet(int tx, int ty, double tangle) {
		super(tx, ty, tangle);
		// TODO 自動生成されたコンストラクター・スタブ
		try {
			img = ImageIO.read(new File(MainFlame.path + "\\src\\game\\Image\\Shot.png"));
		} catch (Exception e) {
			System.out.println("ML:画像データの読み込みに失敗しました。");
		}
		sizeinit();
	}

	@Override
	public void move() {
		// TODO 自動生成されたメソッド・スタブ
		//double rad = Math.toRadians(angle);

		x += Math.cos(angle) * speed;
		y += Math.sin(angle) * speed;
	}

}
