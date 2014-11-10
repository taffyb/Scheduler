package co.uk.brecknock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ScheduleFactory {

	public static Scheduler getScheduler(String schedulerType, Gateway gateway) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		
		String className = "co.uk.brecknock."+schedulerType;
		Constructor<?> c = Class.forName(className).getConstructor(Gateway.class);
		Scheduler s = (Scheduler) c.newInstance(gateway);
		return s;
	}
	
}
