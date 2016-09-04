package Q5;

import java.util.Observable;
import java.util.Observer;

public class AlertALifeGuard implements Observer {
	

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Take a look around you!");		
	}
}
