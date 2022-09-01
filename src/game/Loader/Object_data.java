/**
 * 
 */
package game.Loader;

import java.awt.Image;

/**
 * @author mm1007
 *
 */
public class Object_data {
	public int Object, x, y;
	public float scale;
	public Image img;

	/**
	 * オブジェクトデータを作成します。
	 * @param Object オブジェクト画像番号
	 * @param x x座標
	 * @param y y座標
	 * @param scale サイズ
	 * @param img 画像
	 */
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
