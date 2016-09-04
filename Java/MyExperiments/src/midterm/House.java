package midterm;

public class House {
	
	private boolean furnaceInstalled;
	private boolean onOrOff;

	public House() {
		this.furnaceInstalled = false;
		this.onOrOff = false;
	}
	
	public void installNewFurnace() throws InstalledException, GasException{
		if (!isFurnaceInstalled() && !isGasTurnedOff()) {
			furnaceInstalled = true;
		}
		else if (isFurnaceInstalled()) {
			throw new InstalledException();
		}
		else if (isGasTurnedOff()) {
			throw new GasException();
		}
	}
	
	public boolean isFurnaceInstalled() {
		return furnaceInstalled;
	}
	
	public boolean isGasTurnedOff() {
		return onOrOff;
	}
	
	public void setFurnaceInstalled(boolean installed) {
		this.furnaceInstalled = installed;
	}
	
	public void turnGasOnOrOff (boolean onOrOff) {
		this.onOrOff = onOrOff; 
	}

}
