package ca.ubc.cpsc210.trafficlight.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.trafficlight.model.TrafficLight;
import ca.ubc.cpsc210.trafficlight.model.exceptions.ColourException;
import ca.ubc.cpsc210.trafficlight.model.exceptions.SequenceException;
import ca.ubc.cpsc210.trafficlight.model.exceptions.TrafficLightException;

/*
 * Test the TrafficLight class.
 * 
 * @author Gail Murphy
 *       - modified by Paul Carter, Jan 2011
 */
public class RobustTrafficLightTests {
	
	private TrafficLight light;
	
	@Before
	public void setUpBeforeEachTest() {
		light = new TrafficLight();
	}
	
	@Test (expected = ColourException.class)
	public void testBadColour() throws ColourException, SequenceException {
		light.setColour("blue");
	}
	
	@Test (expected = SequenceException.class)
	public void testRedToYellow() throws ColourException, SequenceException {
		// default is red
		light.setColour("yellow");
	}

	@Test (expected = SequenceException.class)
	public void testYellowToGreen() throws ColourException, SequenceException {
		light.advance();  // red to green
		light.advance();  // green to yellow
		light.setColour("green");
	}
	
	@Test (expected = SequenceException.class)
	public void testGreenToRed() throws ColourException, SequenceException {
		light.advance(); // red to green
		light.setColour("red");
	}
	
	
	@Test
	public void testYellowToRed() {
		light.advance(); // red to green
		light.advance(); // green to yellow
		light.advance(); // yellow to red
		assertEquals("red", light.getColour());
	}
	
	@Test
	public void testRedToGreen() {
		light.advance(); // red to green
		assertEquals("green", light.getColour());
	}
	
	@Test
	public void testGreenToYellow() {
		light.advance(); // red to green
		light.advance(); // green to yellow
		assertEquals("yellow", light.getColour());
	}
	
	@Test
	public void testGreenToGreen() {
		try {
			light.advance(); // red to green
			light.setColour("green");
		} catch (TrafficLightException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRedToRed() {
		try {
			// default is red
			light.setColour("red");
		} catch (TrafficLightException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testYellowToYellow() {
		try {
			light.advance(); // red to green
			light.advance(); // green to yellow
			light.setColour("yellow");
		} catch (TrafficLightException e) {
			fail(e.getMessage());
		}
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
}
