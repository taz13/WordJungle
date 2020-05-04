package word.jungle.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class dialog extends Activity
{
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        Intent intent=getIntent();
        int score=intent.getIntExtra("score", 0);
        //this.setTheme(resid)
        TextView tv=(TextView) findViewById(R.id.textView1);
        tv.setText("Congratulions!!\nYou have successfully\ncompleted the level.\nYour score is:"+score);
        
      Button menu=(Button) findViewById(R.id.menu);
        menu.setOnTouchListener(new OnTouchListener()
        {

			public boolean onTouch(View arg0, MotionEvent arg1) 
			{
				Intent startGameIntent=new Intent(dialog.this,WordJungleActivity.class);
				startActivity(startGameIntent);
				return false;
			}
        	
        });
	}
}
