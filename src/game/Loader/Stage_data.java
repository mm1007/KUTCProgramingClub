package game.Loader;

import java.io.File;

public class Stage_data {

	public String StageName;
	public File StagePath;

	/**
	 * ステージデータを作成します。
	 * @param StageName ステージネーム
	 * @param StagePath ステージパス(絶対パス)
	 */
	public Stage_data(String StageName, File StagePath) {

		this.StageName = StageName;
		this.StagePath = StagePath;

	}

}
