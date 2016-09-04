package z3_ThrowCatchThrow;

public class Animal { 
	String status;
	
	//REQUIRES: food to be "red meat"
	public void feed(String food) throws FoodException{
		if (food.equals("red meat")){
			Printer.print("I got fed:"+food);
			this.status="fed";
		}
		else{
			throw new FoodException();
		}
	}
}
