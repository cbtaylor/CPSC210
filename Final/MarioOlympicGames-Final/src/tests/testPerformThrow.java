package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.Bowser;

public class testPerformThrow {

	@Test
	public void test() {
		try {
			Bowser b = new Bowser();
			b.performThrow(1);
		} catch (Exception te) {
			fail();
		}
	}

}
