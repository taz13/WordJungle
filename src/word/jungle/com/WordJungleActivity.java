package word.jungle.com;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;


public class WordJungleActivity extends Activity implements  View.OnClickListener{
    /** Called when the activity is first created. */
	
	ImageButton start,load,save,option,exit;
	String cheese;
	private MediaPlayer gameMusic;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        nursatAndChicken();
		
		start.setOnClickListener(this);
		option.setOnClickListener(this);
		load.setOnClickListener(this);
		save.setOnClickListener(this);
		exit.setOnClickListener(this);
		
		//gameMusic = MediaPlayer.create(this, R.raw.main_title);gameMusic.start();
	//	gameMusic.setLooping(true);
		
		
		/*start.setOnTouchListener(this);
		option.setOnTouchListener(this);
		load.setOnTouchListener(this);
		save.setOnTouchListener(this);
		exit.setOnTouchListener(this);*/
    }
    
    private void nursatAndChicken() {
		// TODO Auto-generated method stub
    	start = (ImageButton) findViewById(R.id.ibStart);
    	option = (ImageButton) findViewById(R.id.ibOption);
    	load = (ImageButton) findViewById(R.id.ibLoad);
    	save = (ImageButton) findViewById(R.id.ibSave);
    	exit = (ImageButton) findViewById(R.id.ibExit);
	}

    public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ibStart:
			cheese = "Start";		
			break;
		case R.id.ibOption:
			cheese = "Option";
			break;
		case R.id.ibLoad:
			cheese = "Load";
			break;
		case R.id.ibSave:
			cheese = "Save";
			break;
		case R.id.ibExit:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			System.exit(0);
			break;
		}
		try{
			@SuppressWarnings("rawtypes")
			Class ourClass = Class.forName("word.jungle.com." + cheese );
			Intent ourIntent = new Intent(WordJungleActivity.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		
	}

	/*public boolean onTouch(View v,MotionEvent event) 
	{
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ibStart:
			cheese = "Start";		
			break;
		case R.id.ibOption:
			cheese = "Option";
			break;
		case R.id.ibLoad:
			cheese = "Load";
			break;
		case R.id.ibSave:
			cheese = "Save";
			break;
		case R.id.ibExit:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		}
		try{
			@SuppressWarnings("rawtypes")
			Class ourClass = Class.forName("word.jungle.com." + cheese );
			Intent ourIntent = new Intent(WordJungleActivity.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		return true;
	}*/
}