package word.jungle.com;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Option extends Activity implements View.OnClickListener{
	Button onSound,offSound,backFromOption;
	MediaPlayer gameMusic;
	String cheese;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.option);
		
		onSound = (Button) findViewById(R.id.bSoundOn);
    	offSound = (Button) findViewById(R.id.bSoundOff);
    	backFromOption = (Button) findViewById(R.id.bBackfromOption);
    	
    	onSound.setOnClickListener(this);
    	offSound.setOnClickListener(this);
    	backFromOption.setOnClickListener(this);
		
		gameMusic = MediaPlayer.create(Option.this, R.raw.blue);
		
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSoundOn:
			gameMusic.start();
			gameMusic.setLooping(true);
			break;
		case R.id.bSoundOff:
			gameMusic.setLooping(false);
			gameMusic.stop();
			gameMusic.release();
			break;
		case R.id.bBackfromOption:
			cheese = "WordJungleActivity";
			break;
		}
		try{
			@SuppressWarnings("rawtypes")
			Class ourClass = Class.forName("word.jungle.com."+ cheese );
			Intent ourIntent = new Intent(Option.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
				e.printStackTrace();
			}		
	}
}
