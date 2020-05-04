package word.jungle.com;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BackButton extends Drawable
{
	private Bitmap image;
	private String text;
	private int width;
	private int height;
	
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
	
	public void setImage(Bitmap image) {
		this.image = image;
	}

	
	@Override
	protected void draw(Canvas c) 
	{
		Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
		Rect dest = new Rect(posX,posY,(int) (posX + image.getWidth()*.5),(int) (posY + image.getHeight()));
		c.drawBitmap(image, src, dest, null);
		
	}

	public boolean isTouch(int x,int y)
	{
		return posX<x && x<(posX + image.getWidth()*.5) && posY<y && y<(posY + image.getHeight());
	}
}
