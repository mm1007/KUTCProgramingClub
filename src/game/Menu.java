package game;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Paint.paintListener;
import game.Sys_Game.ChangeStatus;
import game.Loader.Loader;

public class Menu implements paintListener, ChangeStatus, buttonListener {

	int[] mid_frame = {
			Frist.jf.getWidth() / 2, Frist.jf.getHeight() / 2
	};

	File[] button_tex = Loader.Load_Image(new File(Frist.file + "ButtonTex\\Button"));

	ArrayList<Button> Button = new ArrayList<Button>();

	Menu() throws IOException {
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 3; y++) {
				Button.add(new Button(y * 300, x * 200, 0.5f, ImageIO.read(button_tex[y + 3 * x])));
				Button.get(y + 3 * x).addActionListener(this);
			}
		}
	}

	@Override
	public void repainted(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		if (!Sys_Game.Start_Game) {
			for (Button draw : Button) {
				draw.draw(g);
			}
		}
	}

	@Override
	public void start() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void pause() {
		// TODO 自動生成されたメソッド・スタ

	}

	@Override
	public void push(Button button) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Sys_Game.select_stage(button.IndexOf(Button));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
