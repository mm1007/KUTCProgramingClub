package game.Loader;

import java.awt.Image;

/**
 * 
 * @author mm1007
 *
 */
public class Player_data {

	public int PObject;
	public Image img;

	public Player_data(int PObject, Image img) {
		
		this.PObject = PObject;
		this.img = img;
		
	}
	
	public int get_Object() {
		return this.PObject;
	}

	public Image get_Image() {
		return this.img;
	}

}
