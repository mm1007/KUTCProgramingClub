package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map_Loader extends Frist {
	static void Load_Map() throws IOException {
		BufferedReader br_enemy = new BufferedReader(new FileReader(file + "enemy_list.txt"));
		BufferedReader br_obj_path = new BufferedReader(
				new FileReader(file + "object_path.txt"));
		BufferedReader br_obj = new BufferedReader(
				new FileReader(file + "object.txt"));
		String text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		int time = 0;
		br_obj_path.readLine();
		br_enemy.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			number = Character.getNumericValue(text.charAt(0));
			object_path[number] = file + text.substring(2, text.length());
		}
		while ((text = br_obj.readLine()) != null) {
			int first_c = text.indexOf(",");
			int second_c = text.indexOf(",", first_c + 1);
			object[time][0] = Character.getNumericValue(text.charAt(0));
			System.out.println(object[time][0]);
			object[time][1] = Integer.parseInt(text.substring(first_c + 1, second_c));
			object[time][2] = Integer.parseInt(text.substring(second_c + 1, text.length()));
			time++;
		}
		time = 0;
		while (true) {
			if ((text = br_enemy.readLine()) != null) {
				System.out.println(text);
				int first_c = text.indexOf(",");
				int second_c = text.indexOf(",", first_c + 1);
				enemy[time][0] = Character.getNumericValue(text.charAt(0));
				enemy[time][1] = Integer.parseInt(text.substring(first_c + 1, second_c));
				enemy[time][2] = Integer.parseInt(text.substring(second_c + 1, text.length()));
				time++;
			} else {
				enemy_list = time;
				break;
			}
		}
		br_obj_path.close();
		br_obj.close();
	}
}
