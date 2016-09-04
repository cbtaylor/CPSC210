package z1_basic;

public class Animal { 
	String status;
	
	//REQUIRES: food to be "red meat"
	public void feed(String food){
		Printer.print("Animal: I got fed:"+food);
		this.status="fed";
	}
}
