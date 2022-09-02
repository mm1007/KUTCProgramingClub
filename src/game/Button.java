package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

public class Button implements mouseListener {

	EventListenerList ELL = new EventListenerList();

	int BaseX, BaseY, Width, Hight;
	float Scale;
	boolean Enable;
	Image img;

	Button(int BaseX, int BaseY, float Scale, Image img) {
		this.BaseX = BaseX;
		this.BaseY = BaseY;
		this.Scale = Scale;
		this.img = img;
		this.Width = (int) (img.getWidth(null) * Scale);
		this.Hight = (int) (img.getHeight(null) * Scale);
		this.Enable = true;
		Frist.ms.addMouseListener(this);
	}

	private boolean Button_Push(MouseEvent e) {
		if (!Enable)
			return false;
		if (BaseX > e.getX())
			return false;
		if (BaseY > e.getY())
			return false;
		if (BaseX + Width < e.getX())
			return false;
		if (BaseY + Hight < e.getY())
			return false;
		return true;
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, BaseX, BaseY, Width, Hight, null);
	}

	public int IndexOf(List<Button> search) {
		return search.indexOf(this);
	}

	public void addActionListener(buttonListener add) {
		ELL.add(buttonListener.class, add);
	}

	public void removeActionListener(buttonListener remove) {
		ELL.add(buttonListener.class, remove);
	}

	@Override
	public void mouse_Pressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (!Button_Push(e)) {
			return;
		}
		for (buttonListener select : ELL.getListeners(buttonListener.class)) {
			select.push(this);
		}
	}

	@Override
	public void mouse_Released(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}

interface buttonListener extends EventListener {

	public void push(Button button);

}