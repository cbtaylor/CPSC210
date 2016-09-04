package z4_ExceptionHierarchy;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {

	@Test (expected=VeggiesException.class)
	public void test() throws FoodException{
		Animal a = new Animal();
		a.feed("spam");
	}

}
