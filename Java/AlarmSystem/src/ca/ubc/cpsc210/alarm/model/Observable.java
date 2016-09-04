package ca.ubc.cpsc210.alarm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an observable in the Observer Design Pattern
 */
public abstract class Observable {
	protected List<Observer> observers;
	
	/**
	 * Constructor creates an empty list of observers
	 */
	public Observable() {
		observers = new ArrayList<Observer>();
	}
	
	/**
	 * Adds an observer to the list of observers
	 * @param o
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	/**
	 * Notifies the observers when a change occurs
	 * in the status of this Observable.
	 */
	public abstract void notifyObservers();
}
