package Everything;

public class Main {
	public static void main(String[] args){
		Hunter h;
		if (1==1){
		h = new Cat();
		}
		else{
			h = new Dog();
		}
		h.chase();
		h.kill();
		
		Animal whateverItIs = new Dog();
		whateverItIs.bark();
	}
}
