package co.uk.brecknock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AlternateScheduler extends AbstractScheduler {
	private Integer activeGroup =  null;
	private Map<Integer, LinkedList<Message>> queue = new HashMap<Integer, LinkedList<Message>>();
	
	public AlternateScheduler(Gateway gateway) {
		super(gateway);
	}

	@Override
	public void addMessage(Message msg) {
		if(queue.containsKey(msg.getGroupId())){
			LinkedList<Message> messages = queue.get(msg.getGroupId());
			messages.add(msg);
		}else{
			LinkedList<Message> messages = new LinkedList<Message>();
			messages.add(msg);
			queue.put(msg.getGroupId(), messages);			
		}
	}

	@Override
	public boolean hasMessages() {
		boolean hasMessages = !queue.isEmpty();
		
		if(terminated){
			hasMessages = queue.containsKey(activeGroup);
		}
		return hasMessages;
	}

	@Override
	public void sendNextMessage() {
		Message msg=null;
		if(queue.containsKey(activeGroup)){
			msg = queue.get(activeGroup).pop();
			if(queue.get(activeGroup).isEmpty()){
				queue.remove(activeGroup);
			}
		}else{
			if(!queue.isEmpty() && !terminated ){
				activeGroup = queue.entrySet().iterator().next().getKey();
				msg = queue.get(activeGroup).pop();
				if(queue.get(activeGroup).isEmpty()){
					queue.remove(activeGroup);
				}
			}
		}	
		
		Gateway.getInstance().send(msg);
	}

	@Override
	public void terminate() {
		terminated = true;
	}

	@Override
	public String getName() {
		return "Alternate Scheduler";
	}
}
