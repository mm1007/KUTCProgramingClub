package game.Loader;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Frist;
import game.Log;
import game.Movement;
import game.Paint;

public class Loader extends Frist {

	static int time;
	static String text;

	/**
	 * マップをロードします。
	 * @throws IOException
	 */
	public static void Load_Map() throws IOException {
		BufferedReader br_obj_path = new BufferedReader(new FileReader(file + "object_path.txt"));
		BufferedReader br_obj = new BufferedReader(new FileReader(file + "object.txt"));
		text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		time = 0;
		br_obj_path.readLine();
		//br_enemy.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			number = Integer.parseInt(text.substring(0, text.indexOf(",")));
			object_path.add(number, file + text.substring(text.indexOf(",") + 1, text.length()));
			Log.output_Log(1, "読み込んでいます", file + text.substring(text.indexOf(",") + 1, text.length()));
			//System.out.println("ML:読み込んでいます -> " + file + text.substring(text.indexOf(",") + 1, text.length()));
		}
		for (int t = 0; t <= Frist.number; t++) {
			try {
				Paint.im[t] = ImageIO.read(new File(Frist.object_path.get(t)));
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
		boolean first = true;
		for (int z = 0, i = Frist.object_list; z < i; z++) {
			for (int x = 0; x < Frist.object.get(z).img.getWidth(null); x++) {
				for (int y = 0; y < Frist.object.get(z).img.getHeight(null); y++) {
					try {
						Movement.colision[x + Frist.object.get(z).x][y + Frist.object.get(z).y] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
						if (first) {
							Log.output_Log(1, "注意", "オブジェクトが場外が場外にある、もしくははみ出しているので当たり判定の設定をスキップします");
							//System.out.println("ML:注意 -> オブジェクトが場外が場外にある、もしくははみ出しているので当たり判定の設定をスキップします");
							first = false;
						}
					}
				}
			}
		}

		br_obj_path.close();
		br_obj.close();

	}

	/**
	 * 敵をロードします。
	 * @throws IOException
	 */
	public static void Load_Enemy() throws IOException {
		BufferedReader br_enemy = new BufferedReader(new FileReader(file + "enemy_list.txt"));

		time = 0;
		while (true) {
			if ((text = br_enemy.readLine()) != null) {
				if (text.equals("[ENEMY_PATH]")) {
					text = br_enemy.readLine();
					while (text.indexOf(",") != -1) {
						int t = Integer.parseInt(text.substring(0, text.indexOf(",")));
						enemy_path.add(t, file + text.substring(text.indexOf(",") + 1));
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
						int EObject = Integer.parseInt(text.substring(0, first_c));
						int X = Integer.parseInt(text.substring(first_c + 1, second_c));
						int Y = Integer.parseInt(text.substring(second_c + 1));
						enemy.add(new Enemy_data(EObject, X, Y, ImageIO.read(new File(enemy_path.get(EObject)))));
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
		br_enemy.close();
	}

	/**
	 * プレイヤーをロードします。
	 * @throws IOException
	 */
	public static void Load_Player() throws IOException {

		BufferedReader br_player = new BufferedReader(new FileReader(file + "player.txt"));

		while (true) {
			if ((text = br_player.readLine()) != null) {
				if (text.equals("[PLAYER_PATH]")) {
					text = br_player.readLine();
					while (text.indexOf(",") != -1) {
						int t = Integer.parseInt(text.substring(0, text.indexOf(",")));
						Frist.player_path.add(t, file + text.substring(text.indexOf(",") + 1));
						Frist.player.add(new Player_data(t, ImageIO.read(new File(Frist.player_path.get(t)))));
						text = br_player.readLine();
						if (text == null)
							break;
					}
				} else
					break;
			} else
				break;
		}

		Image player_img = Frist.player.get(0).img;

		Movement.colision_k = new int[] {
				player_img.getHeight(null), player_img.getHeight(null), player_img.getWidth(null),
				player_img.getWidth(null)
		};

		br_player.close();
	}
}