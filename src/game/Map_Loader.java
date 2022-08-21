package game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map_Loader extends Frist {
	static void Load_Map() throws IOException {
		BufferedReader br_enemy = new BufferedReader(new FileReader(file + "enemy_list.txt"));
		BufferedReader br_obj_path = new BufferedReader(new FileReader(file + "object_path.txt"));
		BufferedReader br_obj = new BufferedReader(new FileReader(file + "object.txt"));
		String text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		int time = 0;
		br_obj_path.readLine();
		//br_enemy.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			number = Integer.parseInt(text.substring(0, text.indexOf(",")));
			object_path[number] = file + text.substring(text.indexOf(",") + 1, text.length());
			System.out.println("ML:読み込んでいます -> " + file + text.substring(text.indexOf(",") + 1, text.length()));
		}
		for (int t = 0; t <= Frist.number; t++) {
			try {
				Paint.im[t] = ImageIO.read(new File(Frist.object_path[t]));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//offImage = createImage(Frist.jf.getWidth(), Frist.jf.getHeight());
		}
		while ((text = br_obj.readLine()) != null) {
			int first_c = text.indexOf(",");
			int second_c = text.indexOf(",", first_c + 1);
			int third_c = text.indexOf(",", second_c + 1);
			int fourth_c = text.indexOf(",", third_c + 1);
			int Object = Integer.parseInt(text.substring(0, first_c));
			int X = Integer.parseInt(text.substring(first_c + 1, second_c).trim());
			int Y = Integer.parseInt(text.substring(second_c + 1, third_c).trim());
			float Scale = Float.parseFloat(text.substring(third_c + 1, fourth_c).trim());
			object.add(new Object_data(Object, X, Y, Scale, Paint.im[Object]));
			time++;
		}
		time = 0;
		while (true) {
			if ((text = br_enemy.readLine()) != null) {
				if (text.equals("[ENEMY_PATH]")) {
					text = br_enemy.readLine();
					while (text.indexOf(",") != -1) {
						int t = Integer.parseInt(text.substring(0, text.indexOf(",")));
						enemy_path[t] = file + text.substring(text.indexOf(",") + 1);
						text = br_enemy.readLine();
						if (text == null)
							break;
					}
				}
				if (text.equals("[ENEMY_LIST]")) {
					text = br_enemy.readLine();
					while (text.indexOf(",") != -1) {
						time++;
						int first_c = text.indexOf(",");
						int second_c = text.indexOf(",", first_c + 1);
						enemy[time][0] = Integer.parseInt(text.substring(0, first_c));
						enemy[time][1] = Integer.parseInt(text.substring(first_c + 1, second_c));
						enemy[time][2] = Integer.parseInt(text.substring(second_c + 1));
						text = br_enemy.readLine();
						if (text == null)
							break;
					}
				}
			} else {
				enemy_list = time;
				break;
			}
		}
		boolean first = true;
		for (int z = 0, i = Frist.object_list; z < i; z++) {
			for (int x = 0; x < Frist.object.get(z).img.getWidth(null); x++) {
				for (int y = 0; y < Frist.object.get(z).img.getHeight(null); y++) {
					try {
						Movement.colision[x + Frist.object.get(z).x][y + Frist.object.get(z).y] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
						if (first) {
							System.out.println("ML:注意 オブジェクトが場外が場外にある、もしくははみ出しているので当たり判定の設定をスキップします");
							first = false;
						}
					}
				}
			}
		}
		br_obj_path.close();
		br_obj.close();
		br_enemy.close();
	}
}

class Object_data {
	public int Object, x, y;
	public float scale;
	public Image img;

	public Object_data(int Object, int x, int y, float scale, Image img) {
		this.Object = Object;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.img = img;
	}

	public int get_Object() {
		return this.Object;
	}

	public int get_X() {
		return this.x;
	}

	public int get_Y() {
		return this.y;
	}

	public float get_Scale() {
		return this.scale;
	}

	public Image get_Image() {
		return this.img;
	}

}

class Enemy_data {
	public int EObject, x, y;
	public Image img;

	public Enemy_data(int EObject, int x, int y, Image img) {
		this.EObject = EObject;
		this.x = x;
		this.y = y;
		this.img = img;
	}
}
