package collectionstuff;

public class Cat extends Pet {
	
	public Cat(String name, int age) {
		super(name, age);
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}

}
