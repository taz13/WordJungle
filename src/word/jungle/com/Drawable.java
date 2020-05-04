package word.jungle.com;

import android.graphics.Canvas;

public abstract class Drawable {
	
	protected int posX;
	protected int posY;
	
	
	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	abstract protected void draw(Canvas c);

}
