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

	public Point add(Point add) {

		Point base = this;

		return new Point(this.x + add.x, this.y + add.y);

	}

	public Point sub(Point sub) {

		Point base = this;

		return new Point(this.x - base.x, this.y - base.y);

	}

}

class Vector2 {

	Point start, direction, end;

	Vector2(Point start, Point end) {

		this.start = start;
		this.direction = end.sub(start);
		this.end = end;

	}

	public Point intersection(Vector2 intersection) {

		Vector2 base = this;

		var t1 = base.direction.x / base.direction.y;
		var t2 = intersection.direction.x / intersection.direction.y;
		var x1 = base.start.x;
		var x2 = intersection.start.x;
		var y1 = base.start.y;
		var y2 = intersection.start.y;

		var solveX = (t1 * x1 - t2 * x2 - y1 + y2) / (t1 - t2);
		var solveY = t1 * (solveX - x1) + y1;

		if (solveX > Math.min(base.start.x, base.end.x) &&
				solveX < Math.max(base.start.x, base.end.x) &&
				solveX > Math.min(intersection.start.x, intersection.end.x) &&
				solveX < Math.max(intersection.start.x, intersection.end.x))
			return new Point(solveX, solveY);

		return null;

	}

}
