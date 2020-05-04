package word.jungle.com;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Start extends Activity implements View.OnClickListener{
	Button startgame;
	String cheese;
	private View back;
	static Question question;
	int imgId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		
		startgame = (Button) findViewById(R.id.bStart);
		startgame.setOnClickListener(this);
		back=(Button)findViewById(R.id.bCancel);
		back.setOnClickListener(this);
		
		String [] images=new String[10];
		images[0]=new String("cat");
		images[1]=new String("bat");
		//images[2]=new String("ant");
		images[2]=new String("hat");
		images[3]=new String("sun");
		images[4]=new String("car");
		images[5]=new String("rat");
		images[6]=new String("ice");
		images[7]=new String("yak");
		
		Random rand = new Random();
		int value=rand.nextInt(8);
		//changeImage(images[value]);
		
		
		ImageView imageView=(ImageView) findViewById(R.id.imageView1);
		try
		{
			imgId=getResources().getIdentifier(images[value],"drawable","word.jungle.com");
			//imageView.setImageResource(imgId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		question = new Question(BitmapFactory.decodeResource(getResources(),imgId),images[value]);
		question.imageChange(imgId, imageView);
		
		Intent i= new Intent();
		Bundle b= new Bundle();
		//b.putParcelable("img",question);
		i.putExtras(b);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bStart:
			cheese = "GameActivity";		
			break;
		case R.id.bCancel:
			cheese = "WordJungleActivity";
			break;
		}
		try{
			@SuppressWarnings("rawtypes")
			Class ourClass = Class.forName("word.jungle.com." + cheese );
			Intent ourIntent = new Intent(Start.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		
	}
/*
	 @Override
	    protected void onDestroy() {
	    super.onDestroy();

	    unbindDrawables(findViewById(R.id.RootView));
	    System.gc();
	    }

	    private void unbindDrawables(View view) {
	        if (view.getBackground() != null) {
	        view.getBackground().setCallback(null);
	        }
	        if (view instanceof ViewGroup) {
	            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
	            unbindDrawables(((ViewGroup) view).getChildAt(i));
	            }
	        ((ViewGroup) view).removeAllViews();
	        }
	    }
	*/
}
