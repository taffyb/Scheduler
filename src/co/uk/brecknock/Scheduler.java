package co.uk.brecknock;

public interface Scheduler {
	public void terminate();
	public void addMessage(Message msg);
	public boolean hasMessages();
	public void sendNextMessage();
	public String getName();
}
