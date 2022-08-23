package game.Enemy;

import java.io.File;

import javax.imageio.ImageIO;

import game.Frist;

public class Cat extends Enemy {
	public Cat(int tx, int ty) {
		super(tx, ty);
		try {
			//System.out.println(Frist.enemy_path[Frist.enemy[0][0]]);
			img = ImageIO.read(new File(Frist.enemy_path[Frist.enemy[0][0]]));
		} catch (Exception e) {
			System.out.println("ML:画像データの読み込みに失敗しました。");
		}
		reload_between = 20;
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
