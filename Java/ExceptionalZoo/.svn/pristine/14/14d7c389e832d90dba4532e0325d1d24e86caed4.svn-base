package z5_unchecked;

import java.util.ArrayList;

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
		try{
			zk.feedAnimals(animals);
		}
		catch(BadWorkConditions bwc){
			Printer.print("ZM: Phil is complaining about work conditions");
			Printer.print("ZM: Ha - he thinks I care");
			throw new UnhappyWorkers();
		}
		Printer.print("ZM: Well that worked out well!");
	}
	
	public static void main(String[] args){
		ZooManager Gerald = new ZooManager();
		ZooKeeper Phil = new ZooKeeper();
		Gerald.addAnimal(new Animal());
		Gerald.addAnimal(new Animal());	
		try{
			Gerald.manageZoo(Phil);
		}
		catch (UnhappyWorkers uw){
			Printer.print("ZM: I am sad that my workers are unhappy");
		}
	}

}
