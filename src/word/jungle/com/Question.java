package word.jungle.com;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Question extends Drawable{

	private Bitmap image;
	private String text;
	private int width;
	private int height;
	private int[] distance;
	
	public Question(Bitmap image,String text)
	{
		this.image = image;
		this.text = text;
		distance = new int[text.length()];
		for(int i = 0; i < distance.length; i++ ) 
			distance[i] = 100;
	}
	
	public void imageChange(int imgId,ImageView imageView)
	{
		try
		{
			imageView.setImageResource(imgId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Bitmap getImage() {
		return image;
	}


	public void setImage(Bitmap image) {
		this.image = image;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
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
	
	public int[] getDistance()
	{
		return distance;
	}
	

	@Override
	protected void draw(Canvas c) {
		int height = (int)((double) image.getHeight() * 0.2);
		int width = (int)((double) image.getWidth() * 0.2);
		Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
		Rect dest = new Rect(posX,posY,posX + width,posY + height);
		c.drawBitmap(image, src, dest, null);
		// TODO Auto-generated method stub
		
	}

	

	

}
