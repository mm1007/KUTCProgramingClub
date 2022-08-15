package game;

public class get_status {

	/**
	 * 指定したキーが押されているかbooleanで返します。
	 * @param KeyCode　確認したいキーコード
	 * @return　押されている場合trueを返します。
	 */
	public boolean get_key_press(int KeyCode) {
		return Key.key[KeyCode];
	}
	
	
}
