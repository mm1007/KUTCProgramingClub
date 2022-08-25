package game.Bullet;

import java.io.File;

import javax.imageio.ImageIO;

import game.Frist;
import game.Log;

public class NomalBullet extends Bullet {
	public NomalBullet(int tx, int ty, double tangle) {
		super(tx, ty, tangle);
		// TODO 自動生成されたコンストラクター・スタブ
		try {
			img = ImageIO.read(new File(Frist.file + "Shot.png"));
		} catch (Exception e) {
			Log.output_Log(1, null, "画像データの読み込みに失敗しました。");
			//System.out.println("ML:画像データの読み込みに失敗しました。");
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
