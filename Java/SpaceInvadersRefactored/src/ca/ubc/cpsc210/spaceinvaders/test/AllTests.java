package ca.ubc.cpsc210.spaceinvaders.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * Runs all tests on Tank, Missile, Invader and Game.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	TestTank.class, 
	TestMissile.class, 
	TestInvader.class } )
public class AllTests {
}
