package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

import game.Game.MainFlame;
import game.Loader.Object_data;

public class Paint extends JPanel {
	File fl = new File("");
	//public static Image im[] = new Image[Frist.object_path.size()];
	Graphics gv;
	long time_s, time_back;
	MainFlame mc;
	Movement mv;

	protected EventListenerList ELL = new EventListenerList();

	Paint() {
		mv = new Movement();
		mc = new MainFlame();
		Frist.jf.add(Frist.canvas);
		mv.time = new Timer(10, mv.action_1);
		//mv.time2 = new Timer(1, mv.action_2);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paintComponent(Graphics g) {
		time_s = System.currentTimeMillis();
		Frist.offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		gv = Frist.offImage.getGraphics();
		paint_all(gv);
		Frist.canvas.paint_2(gv);
		for (paintListener pL : ELL.getListeners(paintListener.class)) {
			pL.repainted(gv);
		}
		g.drawImage(Frist.offImage, 0, 0, this);
		time_back = System.currentTimeMillis();
	}

	void paint_all(Graphics g) {

		for (Object_data draw : Frist.object) {
			g.drawImage(draw.img, draw.x, draw.y, draw.sWidth, draw.sHight, this);
		}
		g.drawImage(Frist.player.get(0).img, Frist.player_x, Frist.player_y, this);
		if (Key.key[KeyEvent.VK_SPACE]) {
			Movement.graple(g);
		}

	}

	/**
	 * ペイントリスナーの追加
	 * @param add 追加するクラス
	 */
	public void addPaintListener(paintListener add) {
		ELL.add(paintListener.class, add);
	}

	/**
	 * ペイントリスナーの削除
	 * @param remove 削除するクラス
	 */
	public void removePaintListener(paintListener remove) {
		ELL.add(paintListener.class, remove);
	}

	/**
	 * 
	 * @author mm1007
	 *
	 */
	interface paintListener extends EventListener {

		/**
		 * 再描画された際に呼び出されます。引数gを使用して追加で描画を行うことができます。
		 * @param g 
		 */
		public void repainted(Graphics g);

	}
}
