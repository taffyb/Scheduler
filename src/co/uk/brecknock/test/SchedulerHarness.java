package co.uk.brecknock.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.uk.brecknock.Gateway;
import co.uk.brecknock.Message;
import co.uk.brecknock.ScheduleFactory;
import co.uk.brecknock.Scheduler;

public class SchedulerHarness {
	private static final String TERMINATE_IN = "t";
	private static final String SCHEDULER_TYPE = "s";
	private static final String RESOURCES = "r";
	private static final String USAGE = "SchedulerHarness r:[number of resources]  <s:[scheduler type]> <t:[ms before terminate]>";

	public static void main(String[] args) {
		int numberOfResources = 1;
		String schedulerType = "ResourceScheduler";
		int terminateMs =0;
		Map<String,String> arguments = new HashMap<String, String>();

		for(int i=0;i<args.length;i++){
			String key =args[i].split(":")[0];
			String val =args[i].split(":")[1];
			arguments.put(key, val);
		}
		
		if (arguments.isEmpty() || !arguments.containsKey(RESOURCES)) {
			System.out.println(USAGE);
		} else {
			
			numberOfResources = Integer.parseInt(arguments.get(RESOURCES));
			if(arguments.containsKey(SCHEDULER_TYPE)){
				schedulerType = arguments.get(SCHEDULER_TYPE);
			}
			if(arguments.containsKey(TERMINATE_IN)){
				terminateMs = Integer.parseInt(arguments.get(TERMINATE_IN));
			}
			System.out.println("Starting Gateway with " + numberOfResources + " resource");
			Gateway gateway = Gateway.getInstance(numberOfResources);
			try {
				Scheduler scheduler = ScheduleFactory.getScheduler(
						schedulerType, gateway);
				System.out.println("Using "+scheduler.getName());
				List<Message> messages = loadMessages();
				for (Message msg : messages) {
					scheduler.addMessage(msg);
				}
				int i = 1;
				do {
					if(i==terminateMs){
						scheduler.terminate();
					}
					i++;
					if (Gateway.getInstance().hasInactiveResources()) {
						scheduler.sendNextMessage();
					}
				} while (scheduler.hasMessages());
				System.out.println("*** Out of Messages ****");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static List<Message> loadMessages() {
		Message msg;
		List<Message> messageList = new ArrayList<Message>();

		msg = new Message();
		msg.setGroupId(1);
		msg.setProcessingDuration(10000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(2);
		msg.setProcessingDuration(30000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(1);
		msg.setProcessingDuration(5000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(3);
		msg.setProcessingDuration(20000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(3);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(2);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(4);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(4);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(5);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(6);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(6);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(6);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(6);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(7);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(7);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		msg = new Message();
		msg.setGroupId(7);
		msg.setProcessingDuration(1000);
		messageList.add(msg);

		return messageList;
	}
}
