package z2_ThrowTryCatch;

import java.util.ArrayList;

import z1_basic.Printer;

public class ZooKeeper {
	
	public void feedAnimals(ArrayList<Animal> animals){
		Printer.print("ZK: I've been told to feed the animals");
		for (Animal a : animals){
			try{
				a.feed("veggies");
			}
			catch (FoodException food){
				food.printStackTrace();
				Printer.print("ZK: food was thrown at me, but I caught it");
			}
		}
		Printer.print("ZK: Done feeding the animals");
	}
	
}
