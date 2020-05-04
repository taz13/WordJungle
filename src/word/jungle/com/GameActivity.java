package word.jungle.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class GameActivity extends Activity {
	
	private LocationManager locationManager;
	private GamePanel gamePanel;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gamePanel = new GamePanel(this);
        
        String location_context = Context.LOCATION_SERVICE;
        
        locationManager = (LocationManager)getSystemService(location_context);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new MyLocationListener());
        
        setContentView(gamePanel);
        gamePanel.getScore().setLocationManager(locationManager);
          
        
    }
    
    
    private class MyLocationListener implements LocationListener {

    	public void onLocationChanged(Location location) {
    		gamePanel.getScore().setLocation(location);
    		gamePanel.getScore().setCurrentLocationTime(System.currentTimeMillis());
    		gamePanel.getScore().setLocationListener(this);
    		
    	}

    	public void onProviderDisabled(String provider) {
    		// TODO Auto-generated method stub

    	}

    	public void onProviderEnabled(String provider) {
    		// TODO Auto-generated method stub

    	}

    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		// TODO Auto-generated method stub

    	}

    }
}
