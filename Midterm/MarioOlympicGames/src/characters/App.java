package characters;

import java.util.ArrayList;

import exceptions.FlyingException;
import exceptions.GameException;
import exceptions.SinkingException;
import sports.FiftyMetreDash;
import sports.MedlaySwimRelay;
import sports.PoolSport;
import sports.RunningSport;
import sports.Sport;

public class App {

	public static void main(String[] args) {
		try{
			FiftyMetreDash fmd = new FiftyMetreDash();
			fmd.addBirdos(new Birdo());
			fmd.addBirdos(new Birdo());
			fmd.performRun();
		} catch (FlyingException fe) {
			System.out.println("A flying exception!");
		}
	}

}
