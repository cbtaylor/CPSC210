package z1_basic;

import java.util.ArrayList;
import java.util.List;

public class ZooManager {
	private ArrayList<Animal> animals;
	
	public ZooManager(){
		animals = new ArrayList<Animal>();
	}
	
	public void addAnimal(Animal a){
		animals.add(a);
	}
	
	public void manageZoo(ZooKeeper zk){
		Printer.print("ZM: Telling Phil to feed the animals");
		zk.feedAnimals(animals);
		Printer.print("ZM: Well that worked out well!");
	}
	
	public static void main(String[] args){
		ZooManager Gerald = new ZooManager();
		ZooKeeper Phil = new ZooKeeper();
		Gerald.addAnimal(new Animal());
		Gerald.addAnimal(new Animal());	
		Gerald.manageZoo(Phil);
	}

}
