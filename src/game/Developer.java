/**
 * 
 */
package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Paint.paintListener;

/**
 * @author mm1007
 *
 */
public class Developer implements keyListener, paintListener {

	boolean EnableDev = false;

	JFrame DevFrame;
	JLabel DevLabel = new JLabel();
	JPanel DevPanel = new JPanel();

	Developer() {
		Frist.ky.addKeyListener(this);
		Frist.pt.addPaintListener(this);
		DevFrame = new JFrame();
		DevFrame.setResizable(false);
		DevFrame.setLayout(null);
		DevPanel.setLayout(null);
		DevFrame.setBounds(0, 0, 400, 300);
		DevPanel.setBounds(0, 0, 400, 300);
		DevFrame.setTitle("Developer Tool");
		DevLabel.setBounds(20, 0, 400, 300);
		DevLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		DevPanel.add(DevLabel);
		DevFrame.add(DevPanel);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (Key.key[KeyEvent.VK_F3]) {
			EnableDev = EnableDev ? false : true;
			DevFrame.setVisible(EnableDev);
			Key.key[KeyEvent.VK_F3] = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void repainted(Graphics g) throws ArithmeticException {
		// TODO 自動生成されたメソッド・スタブ
		long function_long = System.currentTimeMillis() - Frist.pt.time_s;
		long recent_long = Frist.pt.time_s - Frist.pt.time_back;
		long error = recent_long - Frist.pt.mv.time.getDelay();
		long fps = recent_long != 0 ? 1000 / recent_long : 0;
		String output = "<html>";
		output += "再描画にかかった時間:" + function_long + "ミリ秒<br/>";
		output += "総時間:" + recent_long + "ミリ秒<br/>";
		output += "設定時間との誤差:" + error + "ミリ秒<br/>";
		output += "fps:" + fps + "f/s<br/>";
		output += "オブジェクト数:" + Frist.object.size() + "<br/>";
		output += "エネミー数:" + Frist.enemy.size() + "<br/>";
		output += "</html>";
		DevLabel.setText(output);
	}

}
