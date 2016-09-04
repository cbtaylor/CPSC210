package Everything;

public class Dog extends Animal implements Hunter{
	public void bark(){
		//barks!!!
	}

	public Prey chase(){
		//chases the poor sweet animal
		//look really focused and happy!
		//wag tail;
		return new Prey();
	}
	
	public void kill(){
		//murders it.
		bark();
	}

}
