package word.jungle.com;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite extends Drawable {

	private Bitmap spriteImage;
	private Speed speed;
	private int currentFrame;
	private final int total_row = 4;
	private final int total_column = 3;
	private Rect srcRect;
	private Rect dstRect;
	private int spriteWidth;
	private int spriteHeight;
	private final int FPS = 10;
	private int framePeriod = 1000 / FPS;
	private long lastUpdateTime = 01;
	private double dstx;
	private double dsty;
	private double srcx;
	private double srcy;
	private int frameWidth;
	private int frameHeight;
	private double direction;
	private boolean reachedX=false;
	private boolean reachedY=false;
	private double posX;
	private double posY;
	// direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 1, 3, 2, 0 };

	
	public Sprite()
	{
		currentFrame = -1;
		speed = new Speed();
	}
	
	public Sprite(Bitmap spriteImage,int posX,int posY)
	{
		this.spriteImage = spriteImage;
		currentFrame = 0;
		speed = new Speed();
		speed.setSpeedMagnitude(7.0);
		this.posX = posX;
		this.posY = posY;
		this.srcx=posX;
		this.srcy=posY;
		
		speed.setxDirection(0);
		speed.setyDirection(0);
				
		int box_height=spriteImage.getHeight()/2;
		int box_width=spriteImage.getWidth()/4;
		spriteHeight =box_height /total_row;
		spriteWidth = box_width / total_column;
		srcRect = new Rect(0, 0, spriteWidth, spriteHeight);
		//System.out.println(dstx+":"+dsty+":"+srcx+":"+srcy+":"+posX+":"+posY);
	}
	
	public Bitmap getSpriteImage() {
		return spriteImage;
	}



	public void setSpriteImage(Bitmap spriteImage) {
		this.spriteImage = spriteImage;
	}



	public Speed getSpeed() {
		return speed;
	}



	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public void setFrame(int width,int height)
	{
		this.frameWidth=width;
		this.frameHeight=height;
	}

	public int getFrameNo() {
		return currentFrame;
	}



	public void setFrameNo(int frameNo) {
		this.currentFrame = frameNo;
	}



	public int getTotalFrames() {
		return total_row;
	}


	public void updateFrame(long currentTime)
	{
		if( lastUpdateTime + framePeriod < currentTime )
		{
			lastUpdateTime = currentTime;
			currentFrame = (currentFrame + 1) % total_column;
			this.srcRect.left = currentFrame * spriteWidth;
			this.srcRect.right = this.srcRect.left + spriteWidth;
		}
		
		
		
	}
	
	public void updatePosition()
	{
		if((dstx-4) < posX && posX< (dstx+4))
		{
			srcx=posX;
			speed.setxDirection(0);
			//moving=0;
			
			
		}
				
		if((dsty-4) < posY && posY< (dsty+4))
		{
			srcy=posY;
			//moving=0;
			speed.setyDirection(0);
			
			
		}
	//	if((dstx-3) < posX && posX< (dstx+3) && (dsty-3) < posY && posY< (dsty+3) && !reachedX && !reachedY)
	//	{
	//		
	//		reachedX=true;
	//		reachedY=true;
			//System.out.println("reached true");
	//	}
			
		
		if ((posX > frameWidth - spriteWidth - speed.getxDirection())||(posX + speed.getxDirection() < 0)) 
		{
			speed.setxDirection(-speed.getxDirection());
	    }
	
	    if ((posY > frameHeight - spriteHeight - speed.getyDirection())||(posY + speed.getyDirection() < 0))
	    {
	    	speed.setyDirection(-speed.getyDirection());
	    }

		posX += (speed.getxDirection()*speed.getSpeedMagnitude());
		posY += (speed.getyDirection()*speed.getSpeedMagnitude());
		System.out.println(posX+":"+posY+":"+dstx+":"+dsty);
		System.out.println("Along X-axis:"+speed.getxDirection()*speed.getSpeedMagnitude());
		System.out.println("Along Y-axis:"+speed.getyDirection()*speed.getSpeedMagnitude());
	}
	
	

	@Override
	protected void draw(Canvas c) 
	{
		this.srcRect.top=getAnimationRow()*spriteHeight;
		this.srcRect.bottom=this.srcRect.top+spriteHeight;
		dstRect = new Rect((int)posX, (int)posY, (int)posX + spriteWidth+6, (int)posY + spriteHeight+6);
		c.drawBitmap(spriteImage, srcRect, dstRect, null);
	}

	
	protected void setDestination(double x,double y)
	{
		this.dstx=x;
		this.dsty=y;
		
		double up=(dsty-srcy);
		double dwn=(dstx-srcx);
		System.out.println(dstx+":"+dsty+":"+srcx+":"+srcy);
		direction=Math.atan2(up, dwn);
		System.out.println(up+":"+dwn+":"+direction);
		reachedX=false;
		reachedY=false;
	
		speed.setxDirection(Math.cos(direction));
		speed.setyDirection(Math.sin(direction));
	}
	protected double getxDestination()
	{
		return dstx;
	}
	protected double getyDestination()
	{
		return dsty;
	}
	protected void setSource(int x,int y)
	{
		this.srcx=x;
		this.srcy=y;
	}
	
	private int getAnimationRow()
	{
        double dirDouble = direction / (Math.PI / 2) + 2;
        int dir = (int) Math.round(dirDouble) % total_row;
        return DIRECTION_TO_ANIMATION_MAP[dir];
	}

	public boolean hasReached()
	{
		//System.out.println("inside hasReached:"+reachedX+":"+reachedY);
		return (dstx-4) < posX && posX< (dstx+4) && (dsty-4) < posY && posY< (dsty+4);
	}
	public void setReached()
	{
		reachedX=false;
		reachedY=false;
		System.out.println("Inside setReached");
	}
}
