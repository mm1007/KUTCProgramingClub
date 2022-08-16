package game;

/**
 * 状態を取得もしくは変更します。
 * @author mm1007
 */
abstract public class Status {

	/**
	 * 指定したキーが押されているかbooleanで返します。
	 * @param KeyCode　確認したいキーコード
	 * @return　押されている場合trueを返します。
	 */
	public static boolean get_key_press(int KeyCode) {
		return Key.key[KeyCode];
	}

	/**
	 * 落下速度を返します。
	 * @return 落下速度
	 */
	public static int get_acceleration() {
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
		return Movement.gravity_acceleration;
	}

	/**
	 * 重力加速度を変更します。
	 * @param gravity_acceleration 重力加速度
	 */
	public static void set_gravity_acceleration(int gravity_acceleration) {
		Movement.gravity_acceleration = gravity_acceleration;
	}
}
