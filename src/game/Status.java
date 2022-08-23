package game;

import java.awt.Image;
import java.io.File;

/**
 * 状態を取得もしくは変更します。
 * @author mm1007
 */
public class Status {

	/**
	 * 指定したキーが押されているかbooleanで返します。
	 * @param KeyCode　確認したいキーコード
	 * @return　押されている場合trueを返します。
	 */
	public static boolean get_key_press(int KeyCode) {
		notification("Key:" + KeyCode + " -> " + Key.key[KeyCode]);
		return Key.key[KeyCode];
	}

	/**
	 * 落下速度を返します。
	 * @return 落下速度
	 */
	public static int get_acceleration() {
		notification(Movement.acceleration);
		return Movement.acceleration;
	}

	/**
	 * 落下速度を変更します。(非推奨)
	 * @param acceleration 落下速度
	 * @deprecated
	 */
	public static void set_acceleration(int acceleration) {
		Movement.acceleration = acceleration;
	}

	/**
	 * 重力加速度を返します。
	 * @return 重力加速度
	 */
	public static int get_gravity_acceleration() {
		notification(Movement.gravity_acceleration);
		return Movement.gravity_acceleration;
	}

	/**
	 * 重力加速度を変更します。
	 * @param gravity_acceleration 重力加速度
	 */
	public static void set_gravity_acceleration(int gravity_acceleration) {
		Movement.gravity_acceleration = gravity_acceleration;
	}

	/**
	 * オブジェクトデータを返します。
	 * @param index オブジェクト番号
	 * @return オブジェクトデータ
	 */
	public static Object_data get_object_data(int index) {
		return Frist.object.get(index);
	}

	/**
	 * ゲームが読み込んでいる画像を返します。
	 * @param index 番号
	 * @return 画像データ
	 */
	public static Image get_Image(int index) {
		return Paint.im[index];
	}

	/**
	 * エネミー画像のパスを返します
	 * @param index 番号
	 * @return パス(File)
	 */
	public static File get_enemy_path(int index) {
		notification(Frist.enemy_path[index]);
		return new File(Frist.enemy_path[index]);
	}

	/**
	 * マウスが押されているか返します。
	 * @param MouseCode マウスボタンコード
	 * @return マウスが押されている場合trueを返します。
	 */
	public static boolean get_mouse_press(int MouseCode) {
		notification("Mouse:" + MouseCode + " -> " + Mouse.mousePress[MouseCode]);
		return Mouse.mousePress[MouseCode];
	}

	/**
	 * 指定したボタンの最後に押したもしくは離した位置を返します。
	 * @param MouseCode マウスボタンコード
	 * @return マウスの座標{X,Y}を返します。
	 */
	public static int[] get_mouse_press_locate(int MouseCode) {
		notification("Mouse:" + MouseCode + " -> " + "x:" + Mouse.mouse_locate[MouseCode][0] + " y:"
				+ Mouse.mouse_locate[MouseCode][1]);
		int locate[] = {
				Mouse.mouse_locate[MouseCode][0], Mouse.mouse_locate[MouseCode][1]
		};
		return locate;
	}

	/**
	 * ステータス出力用
	 * @param info
	 */
	static void notification(Object info) {
		System.out.println("システム:ステータス出力 -> " + info);
	}
}
