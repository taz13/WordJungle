package word.jungle.com;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class Score extends Drawable {

	private Location location = null;
	private Location prevLocation = null;
	private long currentLocationTime = -1;
	private long lastLocationTime = -1;
	private boolean locationUpdated = false;
	private int calories = 0;
	private float fontSize;
	private int color;
	private float totalDistanceCovered = 0;
	private float distanceCoveredInCurrentSegment = 0;
	private LocationListener simpleLocationListener;
	private LocationManager simpleLocationManager;
	public float getFontSize() {
		return fontSize;
	}



	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}



	public int getColor() {
		return color;
	}



	public void setColor(int color) {
		this.color = color;
	}

	

	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		prevLocation = this.location;
		this.location = location;
		locationUpdated = true;
	}



	public int getCalories() {
		return calories;
	}



	public void setCalories(int calories) {
		this.calories = calories;
	}



	public boolean isLocationUpdated() {
		return locationUpdated;
	}



	public void setLocationUpdated(boolean locationUpdated) {
		this.locationUpdated = locationUpdated;
	}



	public Location getPrevLocation() {
		return prevLocation;
	}



	public void setPrevLocation(Location prevLocation) {
		this.prevLocation = prevLocation;
	}



	public float getTotalDistanceCovered() {
		return totalDistanceCovered;
	}



	public void setTotalDistanceCovered(float totalDistanceCovered) {
		this.totalDistanceCovered = totalDistanceCovered;
	}



	public float getDistanceCoveredInCurrentSegment() {
		return distanceCoveredInCurrentSegment;
	}



	public void setDistanceCoveredInCurrentSegment(
			float distanceCoveredInCurrentSegment) {
		this.distanceCoveredInCurrentSegment = distanceCoveredInCurrentSegment;
	}



	public long getCurrentLocationTime() {
		return currentLocationTime;
	}



	public void setCurrentLocationTime(long currentLocationTime) {
		lastLocationTime = this.currentLocationTime;
		this.currentLocationTime = currentLocationTime;
	}



	public long getLastLocationTime() {
		return lastLocationTime;
	}



	public void setLastLocationTime(long lastLocationTime) {
		this.lastLocationTime = lastLocationTime;
	}

	public void setLocationListener(LocationListener object){
		
		this.simpleLocationListener=object;
	}
	
	public LocationListener getLocationListener(){
		
		return this.simpleLocationListener;
	}

	public void setLocationManager(LocationManager object){
		
		this.simpleLocationManager=object;
	}
	
	public LocationManager getLocationManager(){
		
		return this.simpleLocationManager;
	}

	@Override
	protected void draw(Canvas c) {
		Paint paint = new Paint();
		
		paint.setColor(color);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(fontSize);
		paint.setAntiAlias(true);
		float[] results = new float[2];
		//String score = " " + distanceCoveredInCurrentSegment + " " + totalDistanceCovered;
		String distance="Total Distance:"+ totalDistanceCovered;
	//	String temp=distance+"000";
		//int length=distance.length()+;
		c.drawText(distance, (float) (posX-(posX*.4)), posY, paint);
		c.drawText("Score:"+calories, (float) (posX-(posX*.4)), (float) (c.getHeight()*.06), paint);
		// TODO Auto-generated method stub

	}

}
