package game.Enemy;

import java.io.File;

import javax.imageio.ImageIO;

import game.Frist;

public class Cat extends Enemy {
	public Cat(int tx, int ty) {
		super(tx, ty);
		try {
			System.out.println(Frist.object_path[Frist.enemy[0][0]]);
			img = ImageIO.read(new File(Frist.object_path[Frist.enemy[0][0]]));
		} catch (Exception e) {
			System.out.println("Error");
		}
		reload_between = 50;
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
