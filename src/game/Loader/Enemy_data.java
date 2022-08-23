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
}
