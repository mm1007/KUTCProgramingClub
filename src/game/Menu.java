package game;

import java.awt.Graphics;

import game.Paint.paintListener;
import game.Sys_Game.ChangeStatus;

public class Menu implements paintListener, ChangeStatus {

	int[] mid_frame = {
			Frist.jf.getWidth() / 2, Frist.jf.getHeight() / 2
	};

	@Override
	public void repainted(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		if (!Sys_Game.Start_Game) {
			g.fillRect(0, 0, Frist.jf.getWidth(), Frist.jf.getHeight());
			
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

}
