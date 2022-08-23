package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Menu {
	int[] mid_frame = {
			Frist.jf.getWidth() / 2, Frist.jf.getHeight() / 2
	};

	void O_Menu(Graphics g) {
		/*if (Movement.Move_check == 0) {
			try {
				g.setColor(Color.black);
				g.fillRect(0, 0, 1080, 720);
				Image im = ImageIO.read(new File(Frist.file + "Restart.png"));
				g.drawImage(im, mid_img(im, mid_frame, Frist.pt)[0],
						mid_img(im, mid_frame, Frist.pt)[1], Frist.pt);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}*/
	}

	int[] mid_img(Image img, int x_y[], ImageObserver io) {
		int mid_w;
		int mid_h;
		mid_w = x_y[0] - img.getWidth(io) / 2;
		mid_h = x_y[1] - img.getHeight(io) / 2;
		int[] w_h = {
				mid_w, mid_h
		};
		return w_h;
	}
}
