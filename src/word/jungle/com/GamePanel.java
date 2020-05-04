package word.jungle.com;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.location.Location;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

	private static final String TAG = GamePanel.class.getSimpleName();
	
	private MainGameThread gameThread;
	private StatusBar statusBar;
	private Score score;
	private ArrayList<Animal> animals;
	private LetterUpdate letterupdate;
	private Question question;
	private Sprite player;
	private boolean lock=false;
	String [] images=new String[26];
	int gameState;
	int gameCanvasHeight;
	int gameCanvasWidth;
	private final double statusBarWindowPercentage = 0.08;
	int gridPositionX[]=new int[9];;
	int gridPositionY[]=new int[9];;
	private BackButton back;
	int count=0;
	double distanceToCover;
	
	public GamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);

		//Creating the status bar
		statusBar = new StatusBar();
		statusBar.setPosX(0);
		statusBar.setPosY(0);
		statusBar.setColor(Color.rgb(100,200,100));
		
		//creating a score
		score = new Score();
		score.setFontSize(15);
		score.setColor(Color.WHITE);
		
		
		question=Start.question;
		
		this.setFocusableInTouchMode(false);
		
		
		letterupdate = new LetterUpdate();
		letterupdate.setFontSize(30);
		letterupdate.setColor(Color.WHITE);
		letterupdate.setLetter("---");
		letterupdate.setContext(getContext());
		letterupdate.setQuestion(question.getText());
		
		
		  animals = new ArrayList<Animal>();
		
		
		String name= question.getText();
		Log.d("tag", name);
		
		
			
		player = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.fireheart_actorsprites2), 30, 80);
		
		gameState = 0;
		
		back=new BackButton();
		back.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.menu));
		
		
		gameThread = new MainGameThread(this, getHolder());
		
		new AlertDialog.Builder(getContext()).setTitle("Welcome").setMessage("Please visit the animals to collect letters.").setPositiveButton("Start", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//player.setDestination(posX, posY);
	        }
	     }).show();
	
	}
	
	public MainGameThread getGameThread()
	{
		return this.gameThread;
	}

	void setGameThread(MainGameThread gameThread)
	{
		this.gameThread = gameThread;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// START THE THREADS
		gameThread.setRunning(true);
		gameThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// CLEAN UP CODE FOR THREADS
		boolean retry = true;
		while(retry)
		{
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
			// TODO: handle exception
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		if(count==0 && !lock)
		{
			System.out.println("Inside if");
			
			final int posX=(int) event.getX();
			final int posY=(int) event.getY();
			
	
			System.out.println("Lock is"+lock);
			for(int i=0;i<animals.size();i++)
			{
				final int index=i;
				System.out.println("Inside for");
				if(animals.get(i).checkLocation(posX, posY))
				{
					System.out.println("Inside inner if");
					distanceToCover= (( Math.sqrt(Math.pow(animals.get(index).posX+((animals.get(index).getImage().getWidth()*0.4)/2)-player.getPosX(),2.0) + Math.pow((animals.get(index).posY+(animals.get(index).getImage().getHeight()*0.4)/2)-player.getPosY(),2.0)))*.3);
					System.out.println("Dialog box open");
					lock=true;
					Builder dialog=new AlertDialog.Builder(getContext()).setTitle("Choose Animal");
							dialog.setMessage("The Animal is a "+animals.get(i).getName()+".\n Its distance is "+Math.ceil(distanceToCover)+"Meter.");
							dialog.setPositiveButton("Start", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) { 
				        	player.setDestination((double)(animals.get(index).posX+((animals.get(index).getImage().getWidth()*0.4)/2)), (double)((animals.get(index).posY+(animals.get(index).getImage().getHeight()*0.4)/2)));
				        	String character="";
				        	character=character+animals.get(index).getLetter();
				        	letterupdate.setAlphabet(character);
				        	//gameThread.setRunning(true);
				        	
				        }
				     });
				     dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				       public void onClick(DialogInterface dialog, int which) { 
				            // do nothing
				    	  // gameThread.setRunning(true);
				    	   count=0;
				        }
				    });
				    dialog.show();
				    lock=false;
				    // ((DialogInterface) dialog).dismiss();
					System.out.println("Dialog box close");
				//	gameThread.setRunning(true);
				}
			}
			if(back.isTouch(posX, posY))
			{
				System.out.println("Inside back");
				lock=true;
				Dialog dialog=new AlertDialog.Builder(getContext()).setTitle("Back").setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	gameThread.setRunning(false);
			        	//score.getLocationManager().removeUpdates(score.getLocationListener());
			        	Intent intent = new Intent();
			            intent.setClass(getContext(), WordJungleActivity.class);
			            ((Activity)getContext()).startActivity(intent);
			        }
			     })
			     .setNegativeButton("No", new DialogInterface.OnClickListener() {
			       public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			    	  // gameThread.setRunning(true);
			    	   count=0;
			        }
			    }).show();
				lock=false;
			}
			count+=10;
			System.out.println("outside back");
		}
		else
		{
			count--;
		}
		lock=false;
		System.out.println("Lock is"+lock);
	
		
		return true;
	}
	
	public void update()
	{
		Location current, prev;
		long curTime, prevTime;
		if(score.isLocationUpdated() && score.getPrevLocation() != null)
		{
			current = score.getLocation();
			prev = score.getPrevLocation();
			float dist = prev.distanceTo(current);
			//float[] dist = new float[2];
			//Location.distanceBetween(prev.getLatitude(), prev.getLongitude(), current.getLatitude(), current.getLongitude(), dist);
			float x = (int) (score.getDistanceCoveredInCurrentSegment() + dist);
			score.setDistanceCoveredInCurrentSegment(x);
			score.setTotalDistanceCovered(score.getTotalDistanceCovered() + dist);
			
		//if(question.getDistance()[gameState] <= score.getDistanceCoveredInCurrentSegment())
		//	{
		//		gameState++;
		//		score.setDistanceCoveredInCurrentSegment(0);
		//	}
			//score.setLocationUpdated(false);
			curTime = score.getCurrentLocationTime();
			prevTime = score.getLastLocationTime();
			
			long timeDiff = curTime - prevTime;
			float seconds = (float)timeDiff / 1000.0f;
			double avgSpeed = 3.6f * (dist / seconds);
			
			if( avgSpeed >= 0 && avgSpeed <= 6)
			{
				score.setCalories(score.getCalories() + 4 * (int)Math.ceil(seconds / 60.0f));
			}
			else
			{
				score.setCalories(score.getCalories() + 14 * (int)Math.ceil(seconds / 60.0f));
			}
			score.setLocationUpdated(false);                     
			player.updatePosition();
		}
		player.updateFrame(System.currentTimeMillis());
		//for(int i=0;i<20;i++)
		{
			//ei line ta ke uporer if er modhe rakhle gps er sathe sprite hatbe
			player.updateFrame(System.currentTimeMillis());
		}
		if(player.hasReached() && !lock)
		{
			//player.setReached();
			System.out.println(lock);
			lock=true;
			System.out.println("Player reached.");
			letterupdate.setChar();
			lock=false;
			System.out.println(lock);
			score.setDistanceCoveredInCurrentSegment(0);
			count=0;
		}
		if(letterupdate.isComplete()&&!lock)
		{
			lock=true;
			System.out.println("Inside if.");
			gameThread.setRunning(false);
			 //score.getLocationManager().removeUpdates(score.getLocationListener());
		    Intent intent = new Intent();
		    intent.setClass(getContext(), dialog.class);
		    intent.putExtra("score", score.getCalories());
		    ((Activity)getContext()).startActivity(intent);
		   
		    lock=false;
		}
	}
	
	public void render(Canvas canvas)
	{
		onDraw(canvas);
	}
	
	public void init(Canvas canvas)
	{
		images['a'-97]=new String("ant");
		images['b'-97]=new String("bat");
		images['c'-97]=new String("camel");
		images['d'-97]=new String("deer");
		images['e'-97]=new String("elephant");
		images['f'-97]=new String("frog");
		images['g'-97]=new String("giraff");
		images['h'-97]=new String("hedgehog");
		images['i'-97]=new String("iguana");
		images['j'-97]=new String("jellyfish");
		images['k'-97]=new String("kangaroo");
		images['l'-97]=new String("lion");
		images['m'-97]=new String("monkey");
		images['n'-97]=new String("narwhale");
		images['o'-97]=new String("ostrich");
		images['p'-97]=new String("penguin");
		images['q'-97]=new String("quail");
		images['r'-97]=new String("rhino");
		images['s'-97]=new String("shark");
		images['t'-97]=new String("turtle");
		images['u'-97]=new String("unicorn");
		images['v'-97]=new String("vicuna");
		images['w'-97]=new String("walrus");
		images['x'-97]=new String("xantus");
		images['y'-97]=new String("yak");
		images['z'-97]=new String("zebra");
		
		int SB_height = (int)(canvas.getHeight()-(double) (canvas.getHeight() *statusBarWindowPercentage-0.2*canvas.getHeight()));
		int SB_width = (int) (canvas.getWidth()-0.2*canvas.getWidth());
		 System.out.println(SB_height+":"+SB_width+":"+canvas.getWidth()+":"+canvas.getHeight()); 
		
		int initialPositionY=(int) (canvas.getHeight() *statusBarWindowPercentage+4);
		int initialPositionX=0;
		int gridHeightRatio=(int)(SB_height/3);
		int gridWidthRatio=SB_width/3;
		
		gridPositionX=new int[9];
		
		gridPositionX[1]=initialPositionX+gridWidthRatio;
		gridPositionY[1]=initialPositionY;
		gridPositionX[0]=initialPositionX;
		gridPositionY[0]=initialPositionY;
		gridPositionX[2]=initialPositionX+gridWidthRatio*2;
		gridPositionY[2]=initialPositionY;
		gridPositionX[3]=initialPositionX;
		gridPositionY[3]=initialPositionY+gridHeightRatio;
		gridPositionX[4]=initialPositionX+gridWidthRatio;
		gridPositionY[4]=initialPositionY+gridHeightRatio;
		gridPositionX[5]=initialPositionX+gridWidthRatio*2;
		gridPositionY[5]=initialPositionY+gridHeightRatio;
		gridPositionX[6]=initialPositionX;
		gridPositionY[6]=initialPositionY+gridHeightRatio*2;
		gridPositionX[7]=initialPositionX+gridWidthRatio;
		gridPositionY[7]=initialPositionY+gridHeightRatio*2;
	//	grid9PositionX=initialPositionX+gridWidthRatio*2;
	//	grid9PositionY=initialPositionY+gridHeightRatio*2;
		//animals = new ArrayList<Animal>();
		
		
		String name= question.getText();
		int LoopCounter=1;
		while(LoopCounter<=name.length())
		{
			if(LoopCounter==1)
			{
	
				
				Random rand=new Random();
				int val=rand.nextInt(75);
				String checkImages=images[name.charAt(LoopCounter-1)-97];
				System.out.println(checkImages); 
				Resources checkResource=getResources();
				int checkID=getResources().getIdentifier(checkImages,"drawable","word.jungle.com");
				Bitmap checkBitmap=BitmapFactory.decodeResource(checkResource, checkID);
			//	checkBitmap.recycle();
				Animal a=new Animal(checkImages,checkBitmap);
				
				//Animal a = new Animal(images[name.charAt(LoopCounter-1)-97], BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(images[name.charAt(LoopCounter-1)-97],"drawable","word.jungle.com")));
				a.setPosX(gridPositionX[1]+val);
				a.setPosY(gridPositionY[1]+val);
				animals.add(a);	
				System.out.println((gridPositionX[1]+val)+":"+(gridPositionY[1]+val)+":"+LoopCounter);
			}
			
			else if(LoopCounter==2)
			{
	
				Random rand=new Random();
				int val=rand.nextInt(75);
				String checkImages=images[name.charAt(LoopCounter-1)-97];
				System.out.println(checkImages); 
				Resources checkResource=getResources();
				int checkID=getResources().getIdentifier(checkImages,"drawable","word.jungle.com");
				Bitmap checkBitmap=BitmapFactory.decodeResource(checkResource, checkID);
			//	checkBitmap.recycle();
				Animal a=new Animal(checkImages,checkBitmap);
				
				//Animal a = new Animal(images[name.charAt(LoopCounter-1)-97], BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(images[name.charAt(LoopCounter-1)-97],"drawable","word.jungle.com")));
				a.setPosX(gridPositionX[5]+val);
				a.setPosY(gridPositionY[5]+val);
				animals.add(a);	
				System.out.println((gridPositionX[5]+val)+":"+(gridPositionY[5]+val)+":"+LoopCounter);
			}
			
			else if(LoopCounter==3)
			{
	
				
				Random rand=new Random();
				int val=rand.nextInt(75);
				String checkImages=images[name.charAt(LoopCounter-1)-97];
				System.out.println(checkImages); 
				Resources checkResource=getResources();
				int checkID=getResources().getIdentifier(checkImages,"drawable","word.jungle.com");
				Bitmap checkBitmap=BitmapFactory.decodeResource(checkResource, checkID);
			//	checkBitmap.recycle();
				Animal a=new Animal(checkImages,checkBitmap);
				
				//Animal a = new Animal(images[name.charAt(LoopCounter-1)-97], BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(images[name.charAt(LoopCounter-1)-97],"drawable","word.jungle.com")));
				a.setPosX(gridPositionX[3]+val);
				a.setPosY(gridPositionY[3]+val);
				animals.add(a);	
				System.out.println((gridPositionX[3]+val)+":"+(gridPositionY[3]+val)+":"+LoopCounter);
			}
		
			
			LoopCounter++;
		}
		player.setFrame(canvas.getWidth(),canvas.getHeight());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.images1);
		Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
		Rect src = new Rect(0,0,background.getWidth(),background.getHeight());
		canvas.drawBitmap(background, src, dst, null);
		statusBar.draw(canvas);
		score.setPosX(canvas.getWidth());
		score.setPosY(0 + (int)(score.getFontSize()));
		score.draw(canvas);
		
		letterupdate.setPosX((int) (question.getImage().getWidth()*.2+2));
		letterupdate.setPosY(30);
		letterupdate.draw(canvas);
		
		for(int i = 0; i < 3; i++)
		{
			animals.get(i).draw(canvas);
		}
		
		
		question.setPosX(statusBar.getPosX());
		question.setPosY(statusBar.getPosY());    
		question.draw(canvas);
		
		//edited by nirjash
		
		
		
		player.draw(canvas);
	//	animals.clear();
		back.posX=(int) (canvas.getWidth()*.35);
		back.posY=(int) (canvas.getWidth()*.01);
		back.draw(canvas);
		//canvas.drawARGB(0, 100, 100, 100);
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(ArrayList<Animal> animals) {
		this.animals = animals;
	}

	public void showComplete()
	{
		
	}
}
