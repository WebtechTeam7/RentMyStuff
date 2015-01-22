package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class OurObserver {

	private static Collection<Observer> observers = new ArrayList<Observer>();

	public static void add(Observer observer) {
		if (observers.contains(observer)) {
			System.out.println("Observer bereits in Collection!");
		} else {
			observers.add(observer);
		}
	}

	public static void delete(Observer observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
			System.out.println("Observer wurde entfernt!");
		}
	}
	
	public static void notify(Object object){
		for(Observer o : observers){
			o.update(new Observable(), object);
			System.out.println("Notify Observer ");
		}
	}

	public static Collection<Observer> getObservers() {
		return observers;
	}

	public static void setObservers(Collection<Observer> observers) {
		OurObserver.observers = observers;
	}

}
