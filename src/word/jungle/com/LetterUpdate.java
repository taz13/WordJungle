package word.jungle.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class LetterUpdate extends Drawable {
	String letter,alphabet;
	private int color;
	private float fontSize;
	private String question;
	private int counter;
	public Context context;
	
	public String getLetter() {
		return letter;
	}
	public void setQuestion(String text)
	{
		this.question=text;
		counter=text.length();
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public void setChar()
	{
		String temp="";
		System.out.println("inside Setchar");
		int index=question.indexOf(alphabet.charAt(0));
		//System.out.println(temp+":"+index+alphabet+letter);
		for(int i=0;i<letter.length();i++)
		{
			if(i==index)
			{
				temp=temp+alphabet;
			//	counter--;
			}
			else
			{
				temp=temp+letter.charAt(i);
				
				
			}	
			System.out.println("Counter:"+counter);
		}
	//	System.out.println(temp+":"+index+alphabet+letter);
		letter=temp;
	/*	
		if(counter==0)
		{
			//if(letterupdate.isComplete() && !lock)
			//	lock=true;
				System.out.println("Inside if.");
				new AlertDialog.Builder(this.context).setTitle("Welcome").setMessage("Please visit the animals to collect letters.").setPositiveButton("Start", new DialogInterface.OnClickListener() 
				{
			        public void onClick(DialogInterface dialog, int which) 
			        { 
			        	Intent intent = new Intent();
			            intent.setClass(context, WordJungleActivity.class);
			            ((Activity)context).startActivity(intent);
			        }
			     }).show();
		}
			else
			{	
				System.out.println("Inside else.");
				new AlertDialog.Builder(this.context).setTitle("Congratulations!!!").setMessage("You have collected the letter "+alphabet+".").setPositiveButton("Continue", new DialogInterface.OnClickListener() 
				{
			        public void onClick(DialogInterface dialog, int which)
			        { 
			        	//player.setDestination(posX, posY);
			        }
			     }).show();
				System.out.println("Outside else.");
			}*/
		}
	
	public boolean isComplete()
	{
		System.out.println("inside isComplete"+counter);
		int temp=letter.indexOf("-");
		if(temp<0)
			return true;
		else
			return false;
	}
	
	public void setAlphabet(String character)
	{
		this.alphabet=character;
		System.out.println(alphabet);
	}
	@Override
	protected void draw(Canvas c) {
		// TODO Auto-generated method stub
		//letter=letter+"_"+"-";
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setTextSize(fontSize);
		paint.setAntiAlias(true);
		c.drawText(letter, posX, posY, paint);
		
	}
	
	public void setContext(Context cont)
	{
		this.context=cont;
	}
}
