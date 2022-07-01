import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Frist {
	public static int[][] object = new int[1000][3];
	public static String[] object_path = new String[1000];
	public static int object_list;

	public static void main(String[] args) throws IOException {
		String path = new File("").getAbsolutePath();
		BufferedReader br_obj_path = new BufferedReader(
				new FileReader(path + "\\object_path.txt"));
		BufferedReader br_obj = new BufferedReader(
				new FileReader(path + "\\object.txt"));
		System.out.println(path + "\\object.txt");
		String text = br_obj.readLine();
		object_list = Integer.parseInt(text.substring(text.indexOf("=") + 2));
		int time = 0;
		br_obj_path.readLine();
		while ((text = br_obj_path.readLine()) != null) {
			object_path[Character.getNumericValue(text.charAt(0))] = (path + "\\" + text.substring(2, text.length()));
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
		JFrame jf = new JFrame("test");
		Paint pt = new Paint();
		jf.setBounds(0, 0, 1080, 720);
		pt.setBounds(0, 0, 1080, 720);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.add(pt);
		jf.setVisible(true);
		br_obj_path.close();
		br_obj.close();
	}
}

class Paint extends Canvas {
	Image im;

	public void paint(Graphics g) {
		for (int x = 0; x < Frist.object_list; x++) {
			try {
				System.out.println(Frist.object[x][0] - 1 + " " + x + " " + Frist.object[x][0]);
				im = ImageIO.read(new File(Frist.object_path[Frist.object[x][0] - 1]));
				g.drawImage(im, Frist.object[x][1], Frist.object[x][2], this);
			} catch (IOException e) {
			}
		}
	}
}
