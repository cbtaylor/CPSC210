package sports;

import java.util.List;

public class FiftyMetreDash implements RunningSport {

	private String name;
	private List<RunningSport> characters;

	public void setName(String name) {
		this.name = name;
	}

	public void performRun() {
		for (RunningSport c: characters) {
			c.performRun();
		}
	}

	public int getSpeed() {
		int maxSpeed = 0;
		for (RunningSport c: characters) {
			if (c.getSpeed() > maxSpeed)
				maxSpeed = c.getSpeed();
		}
		return maxSpeed;
	}

}
