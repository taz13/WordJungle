package word.jungle.com;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Animal extends Drawable {

	private String name;
	private char letter;
	private Bitmap image;
	private int distance;
	//private int posX;
	//private int posY;
	
	public Animal(String name,Bitmap image)
	{
		this.name = name;
		this.image = image;
		this.letter = name.toLowerCase().charAt(0);
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
		this.letter = name.toLowerCase().charAt(0); 
	}


	public char getLetter() {
		return letter;
	}



	public Bitmap getImage() {
		return image;
	}


	public void setImage(Bitmap image) {
		this.image = image; 
	}
	
	@Override
	protected void draw(Canvas c) {		
		Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
		Rect dst = new Rect(posX,posY,posX + (int)(0.4 * (double)image.getWidth()),posY + (int)(0.4 * (double)image.getHeight()));
		c.drawBitmap(image, src, dst, null);
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkLocation(int x,int y)
	{
		return x>posX && x<posX+((0.4*image.getWidth())) && y>posY && y<(posY+(0.4*image.getHeight())); 
	}

	public void setDistance(int val)
	{
		this.distance=val;
	}
}