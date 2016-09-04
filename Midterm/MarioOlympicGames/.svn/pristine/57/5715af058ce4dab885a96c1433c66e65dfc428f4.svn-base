package characters;

import java.util.ArrayList;
import java.util.List;

import util.Icon;

public abstract class SharedCharacterBehaviour implements Character {

	protected String name;

	protected List<Power> powers;
	
	private Icon image;

	public SharedCharacterBehaviour() {
		powers = new ArrayList<Power>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setIcon(Icon image) {
		this.image = image;
	}
	
	public Icon getIcon() {
		return image;
	}

	protected void addPower(Power power) {
		powers.add(power);
	}

}
