package co.uk.brecknock;

public class Message {
	private Integer groupId;
	private Integer processingDuration; //time to spend processing this message in milliseconds 
	private boolean completed = false;
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public long getDuration() {
		return processingDuration;
	}

	public void setProcessingDuration(Integer processingDuration) {
		this.processingDuration = processingDuration;
	}

	public void completed() {
		this.completed=true;
	}
	
	public boolean isCompleted(){
		return this.completed;
	}
}
