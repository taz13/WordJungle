package word.jungle.com;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class StatusBar extends Drawable {

	private final double windowPercentage = 0.08;
	private int width;
	private int height;
	private int color;
	
	public double getWindowPercentage() {
		return windowPercentage;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	protected void draw(Canvas c) {
		height = (int)((double) c.getHeight() * windowPercentage);
		width = c.getWidth();
		Paint paint = new Paint();
		paint.setColor(color);
		c.drawRect(posX, posY, posX + width, posY + height, paint);
	}

}
