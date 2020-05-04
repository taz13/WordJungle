package word.jungle.com;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainGameThread extends Thread {
	
	

	private static final String TAG = MainGameThread.class.getSimpleName();
	
	private boolean running;
	private GamePanel gamePanel;
	private SurfaceHolder holder;
	
	public MainGameThread(GamePanel gamePanel,SurfaceHolder holder)
	{
		this.gamePanel = gamePanel;
		this.holder = holder;
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	
	
	public SurfaceHolder getHolder() {
		return holder;
	}

	public void setHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

	public void run()
	{
		Canvas c = null;
		try
		{
			c = holder.lockCanvas();
			synchronized (holder) 
			{
				gamePanel.init(c);
			}
			
		}
		catch(Exception e)
		{
			Log.d(TAG, "Cannot get canvas");
		}
		finally
		{
			holder.unlockCanvasAndPost(c);
		}
		while(running)
		{
			c = null;
			try
			{
				c = holder.lockCanvas();
				synchronized (holder) 
				{
					gamePanel.update();
					gamePanel.render(c);
				}
				
			}
			catch(Exception e)
			{
				Log.d(TAG, "Cannot get canvas");
			}
			finally
			{
				holder.unlockCanvasAndPost(c);
			}
		}
	}

}
