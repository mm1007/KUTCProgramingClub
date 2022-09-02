package game.Enemy;

import java.awt.image.BufferedImage;

import game.Frist;
import game.Log;

public class Cat extends Enemy {
	public Cat(int tx, int ty) {
		super(tx, ty);
		try {
			//System.out.println(Frist.enemy_path[Frist.enemy[0][0]]);
			img = (BufferedImage) Frist.enemy.get(0).img;
		} catch (Exception e) {
			Log.output_Log(1, null, "画像データの読み込みに失敗しました。");
			//System.out.println("ML:画像データの読み込みに失敗しました。");
		}
		reload_between = 30;
		sizeinit();
	}

	@Override
	public boolean attack() {
		reload++;

		if (reload > reload_between) {
			reload = 0;
			return true;
		} else
			return false;
	}

}
