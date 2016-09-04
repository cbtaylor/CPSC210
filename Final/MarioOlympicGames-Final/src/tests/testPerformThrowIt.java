package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import characters.Bowser;
import exceptions.ThrowingException;

public class testPerformThrowIt {

	@Test(expected=ThrowingException.class)
	public void test()  throws ThrowingException {
		Bowser b = new Bowser();
		b.performThrow(1);
		fail();
	}

}
