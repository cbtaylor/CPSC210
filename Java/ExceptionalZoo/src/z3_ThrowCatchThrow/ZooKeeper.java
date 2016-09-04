package z3_ThrowCatchThrow;

import java.util.ArrayList;

import z1_basic.Printer;

public class ZooKeeper {
	
	public void feedAnimals(ArrayList<Animal> animals)
	throws BadWorkConditions{
		Printer.print("ZK: I've been told to feed the animals");
		for (Animal a : animals){
			try{
				a.feed("veggies");
			}
			catch (FoodException food){
				food.printStackTrace();
				Printer.print("ZK: food was thrown at me, I caught it");
				Printer.print("ZK: But I am NOT going to keep working!");
				Printer.print("ZK: I am going on strike!");
				throw new BadWorkConditions();
			}
			finally{
				Printer.print("ZK: I'm going home");
			}
		}
		Printer.print("ZK: Done feeding the animals");
	}
	
}
