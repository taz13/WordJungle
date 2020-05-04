package word.jungle.com;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

public class StartView extends ImageView
{

	Random rand;
	public void changeImage(String path,Context context)
	{
		try
		{
			int imgId=getResources().getIdentifier(path,"drawable","word.jungle.com");
			StartView img=new StartView(context);
			img.setImageResource(imgId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public StartView(Context context) {
		super(context);
		
	}

	protected void onDraw(Canvas canvas)
	{

		rand=new Random();
		//int value=rand.nextInt(10);
		int value=0;
		
		String [] images=new String[5];
		images[0]=new String("cat");
		//images[1]=new String("Bat");
		//images[2]=new String("Rat");
		//images[3]=new String("Hat");
		//images[4]=new String("Ant");
		
		changeImage(images[value],getContext());
		super.onDraw(canvas);
	}
}
