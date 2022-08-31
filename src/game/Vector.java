/**
 * 
 */
package game;

/**
 * @author mm1007
 *
 */
public class Vector {

	/**
	 * 2点間の距離を計算します。
	 * @param a 一つ目の点
	 * @param b 二つ目の点
	 * @return 距離{x,y}を返します。
	 */
	public static int[] distance(Point a, Point b) {

		int distance[] = {
				Math.abs(a.x - b.x), Math.abs(a.y - b.y)
		};
		return distance;

	}

	/**
	 * 2点間の距離を計算します。
	 * @param x1 一つ目の点x
	 * @param y1 一つ目の点y
	 * @param x2 二つ目の点x
	 * @param y2 二つ目の点y
	 * @return 距離{x,y}を返します。
	 */
	public static int[] distance(int x1, int y1, int x2, int y2) {

		int distance[] = {
				Math.abs(x1 - x2), Math.abs(y1 - y2)
		};
		return distance;

	}

}

class Point {

	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
