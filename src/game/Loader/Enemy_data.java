/**
 * 
 */
package game.Loader;

import java.awt.Image;

/**
 * @author mm1007
 *
 */
public class Enemy_data {
	public int EObject, x, y;
	public Image img;

	public Enemy_data(int EObject, int x, int y, Image img) {
		this.EObject = EObject;
		this.x = x;
		this.y = y;
		this.img = img;
	}

	public int get_Object() {
		return this.EObject;
	}

	public int get_X() {
		return this.x;
	}

	public int get_Y() {
		return this.y;
	}

	/*public float get_Scale() {
		return this.scale;
	}*/

	public Image get_Image() {
		return this.img;
	}
	
	
}
