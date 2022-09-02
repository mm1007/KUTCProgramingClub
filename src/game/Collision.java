package game;

import java.awt.Graphics;
import java.util.List;

import game.Paint.paintListener;
import game.Bullet.Bullet;
import game.Game.MainCanvas;
import game.Loader.Object_data;

public class Collision implements paintListener {

	public static int[][] colision = new int[Frist.stageH][Frist.stageW];
	public static int[] colision_k = new int[4];

	/**
	 * 指定した方向、距離に当たり判定があるか判断します。(途中に当たり判定があるかは確認しません)
	 *
	 * @param key  方向
	 * @param move 距離
	 * @return 当たり判定がある場合trueを返します。
	 */
	public boolean collision_check(int key, int move) {
		int[] convert = { 2, 3, 0, 1 };
		for (int t = 0, i = colision_k[convert[key]]; t < i; t++) {
			if (key == 0 && colision[Movement.player_x + t][Movement.player_y - move] == 1) {
				return true;
			} else if (key == 1 && colision[Movement.player_x + t][Movement.player_y + colision_k[key] + move] == 1) {
				return true;
			} else if (key == 2 && colision[Movement.player_x - move][Movement.player_y + t] == 1) {
				return true;
			} else if (key == 3
					&& colision[Movement.player_x + colision_k[key] + move - 1][Movement.player_y + t] == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定した方向、距離に当たり判定があるか判断します。(途中に当たり判定があった場合もtrueを返します。)
	 *
	 * @param key  方向
	 * @param move 距離
	 * @return 当たり判定がある場合trueを返します。
	 */
	public boolean collision_check_full(int key, int move) {
		int[] convert = { 2, 3, 0, 1 };
		for (int k = 1; k < move; k++) {
			for (int t = 0, i = colision_k[convert[key]]; t < i; t++) {
				if (key == 0 && colision[Movement.player_x + t][Movement.player_y - k] == 1) {
					return true;
				} else if (key == 1 && colision[Movement.player_x + t][Movement.player_y + colision_k[key] + k] == 1) {
					return true;
				} else if (key == 2 && colision[Movement.player_x - k][Movement.player_y + t] == 1) {
					return true;
				} else if (key == 3
						&& colision[Movement.player_x + colision_k[key] + k - 1][Movement.player_y + t] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * プレイヤーから指定した距離に移動した場合、衝突が起こるか判断します。
	 *
	 * @param X X方向
	 * @param Y Y方向
	 * @return
	 */
	public boolean collision_check_data(int X, int Y) {
		for (Object_data obj : Frist.object) {
			int[] pos = { obj.x, obj.y };
			int[] radius = { obj.img.getWidth(null) / 2, obj.img.getHeight(null) / 2 };
			int[] mid = { pos[0] + radius[0], pos[1] + radius[1] };
			int[] minDis = { radius[0] + colision_k[2] / 2, radius[1] + colision_k[2] / 2 };
			int[] Dis = Vector.distance(mid[0], mid[1], Frist.player_x + colision_k[2], Frist.player_y + colision_k[0]);
			if (Dis[0] >= minDis[0])
				continue;
			if (Dis[1] >= minDis[1])
				continue;
			return true;
		}
		return false;
	}

	/**
	 * プレイヤーから指定した距離に移動した場合、衝突が起こるか判断します。
	 *
	 * @param X X方向
	 * @param Y Y方向
	 * @return
	 */
	public boolean collision_check_data_full(int X, int Y) {
		for (Object_data obj : Frist.object) {
			int[] radius = { obj.img.getWidth(null) / 2, obj.img.getHeight(null) / 2 };
			int[] minDis = { radius[0] + colision_k[2] / 2, radius[1] + colision_k[2] / 2 };
			for (int x = 1; x < X; x++) {
				for (int y = 1; y < Y; y++) {
					int[] pos = { obj.x - x, obj.y - y };
					int[] mid = { pos[0] + radius[0], pos[1] + radius[1] };
					int[] Dis = Vector.distance(mid[0], mid[1], Frist.player_x + colision_k[2],
							Frist.player_y + colision_k[0]);
					if (Dis[0] >= minDis[0])
						continue;
					if (Dis[1] >= minDis[1])
						continue;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 弾丸との当たり判定を行います。当たっている場合はtrueを返し、当たっていない場合はfalseを返します。
	 *
	 * @param Bullet_list 弾丸リスト
	 * @return 判定結果(boolean)
	 */
	public boolean collision_bullet(List<Bullet> Bullet_list) {
		for (Bullet check : Bullet_list) {
			int[] pos = check.getPos();
			int[] radius = { check.img.getWidth() / 2, check.img.getHeight() / 2 };
			pos[0] += radius[0];
			pos[1] += radius[1];
			int minDis[] = { radius[0] + colision_k[2] / 2, radius[1] + colision_k[0] / 2 };
			int Distance[] = Vector.distance(pos[0], pos[1], Frist.player_x + colision_k[2] / 2,
					Frist.player_y + colision_k[0] / 2);

			if (Distance[0] >= minDis[0]) {
				continue;
			}
			if (Distance[1] >= minDis[1]) {
				continue;
			}
			return true;

		}
		return false;
	}

	@Override
	public void repainted(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		if (collision_bullet(MainCanvas.bullet)) {
			System.out.println("true");
		}
	}
}
