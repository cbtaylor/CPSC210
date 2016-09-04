package collectionstuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		Dog mb = new Dog("Mia Bella", 11);
		Dog ch = new Dog("Chiara", 11);
		Cat ge = new Cat("Georgia", 12);
		Cat mi = new Cat("Misu", 11);
		Cat ma = new Cat("Matisse", 3);
		
		List<Pet> dogs = new ArrayList<Pet>();
		List<Pet> cats = new ArrayList<Pet>();
		
		dogs.add(mb);
		dogs.add(ch);
		cats.add(ge);
		cats.add(mi);
		cats.add(ma);
		
		for (Pet d : dogs) {
			System.out.println(d.getName() + " is " + d.getAge());
		}
		
		for (Pet c : cats) {
			System.out.println(c.getName() + " is " + c.getAge());
		}
		
		HashMap<String, List<Pet>> pets = new HashMap<String, List<Pet>>();
		pets.put("Dogs", dogs);
		pets.put("Cats", cats);
		
		for (Map.Entry<String, List<Pet>> entry : pets.entrySet()) {
			String key = entry.getKey();
			List<Pet> value = entry.getValue();
			
			System.out.println(key + " : " + value);
		}
	}

}
