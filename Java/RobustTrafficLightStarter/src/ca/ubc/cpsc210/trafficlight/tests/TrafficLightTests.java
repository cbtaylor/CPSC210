package ca.ubc.cpsc210.trafficlight.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.trafficlight.model.TrafficLight;

/*
 * Test the TrafficLight class.
 * 
 * @author Gail Murphy
 */
public class TrafficLightTests {
	
	private TrafficLight light;
	
	@Before
	public void setUpBeforeEachTest() {
		light = new TrafficLight();
	}
	
	@Test
	public void testYellowToRed() {
		light.advance(); // red to green
		light.advance(); // green to yellow
		light.setColour("red"); // yellow to red
		assertEquals("red", light.getColour());
	} 
	
	@Test
	public void testRedToGreen() {
		light.setColour("green"); // red to green
		assertEquals("green", light.getColour());
	}
	
	@Test
	public void testGreenToYellow() {
		light.advance(); // red to green
		light.setColour("yellow"); // green to yellow
		assertEquals("yellow", light.getColour());
	}
	
	@Test
	public void testAdvance() {
		assertEquals("red", light.getColour());
		light.advance();
		assertEquals("green", light.getColour());
		light.advance();
		assertEquals("yellow", light.getColour());
		light.advance();
		assertEquals("red", light.getColour());
	}
	
	@Test
	public void testYellowToYellow() {
		light.advance(); // red to green
		light.advance(); // green to yellow
		light.setColour("yellow"); // yellow to red
		assertEquals("yellow", light.getColour());
	}
	
	@Test
	public void testRedToRed() {
		// default is red
		light.setColour("red");
		assertEquals("red", light.getColour());
	}
	
	@Test
	public void testGreenToGreen() {
		light.advance(); // red to green
		light.setColour("green"); 
		assertEquals("green", light.getColour());
	}
	
	@Test
	public void testThingsThatIdRatherNotBeAbleToDo() {
		// shouldn't be able to set traffic light colour to blue
		light.setColour("blue");
		assertEquals("blue", light.getColour());
		
		// shouldn't be able to change colour from green directly to red
		light.setColour("green");
		light.setColour("red");
		assertEquals("red", light.getColour());
	}
}
