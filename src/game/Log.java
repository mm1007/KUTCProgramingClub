/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author mm1007
 *
 */
public class Log {
	
	private static String[] default_output = {
			"システム", "ML"
	};

	static ArrayList<String> output = set_Default();

	/**
	 * outputリストの初期値定義
	 * @return default_outputに入っている物をoutputに入れます。
	 */
	private static ArrayList<String> set_Default() {

		ArrayList<String> return_Array = new ArrayList<String>();

		for (String temp : default_output) {
			return_Array.add(temp);
		}

		return return_Array;

	}

	/**
	 * 出力 システム
	 * @param index 出力元リストの番号
	 * @param notification 例:警告、注意 など nullを指定することもできます。
	 * @param text 内容
	 */
	public static void output_Log(int index, String notification, String text) {

		if (notification != null) {
			System.out.println(output.get(index) + ":" + notification + " -> " + text);
		}

		if (notification == null) {
			System.out.println(output.get(index) + ":" + text);
		}

	}

}
