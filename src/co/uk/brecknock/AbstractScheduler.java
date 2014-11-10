package co.uk.brecknock;

public abstract class AbstractScheduler implements Scheduler{
	protected boolean terminated = false;
	protected Gateway gateway;
	
	
	public AbstractScheduler(Gateway gateway){
		this.gateway = gateway;
	}
	
	
	abstract public void addMessage(Message msg);

	abstract public String getName();

}
