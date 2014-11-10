package co.uk.brecknock;

public class Resource {
	private boolean active = false;

	private int id;
	
	public int getId() {
		return id;
	}
	
	public Resource(int id){
		this.id = id;
	}
	

	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {		
		return active;
	}

	public void handleMessage(Message msg) {
		Thread t = new Thread(new ResourceThread(this, msg));
		t.start();
	}
	
	private class ResourceThread implements Runnable {

		private Resource resource;
		private Message msg;
		
		public ResourceThread(Resource resource, Message msg) {
			this.resource = resource;
			this.msg = msg;
		}

		@Override
		public void run() {
			resource.active = true;
			try {
				System.out.println("Resource["+id+"] GroupId["+msg.getGroupId()+"] <"+msg.getDuration()+"> - Start");
				Thread.sleep(msg.getDuration());
				// log msg
				System.out.println("Resource["+id+"] GroupId["+msg.getGroupId()+"] - Complete");
				msg.completed();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resource.active = false;
		}

	}
}
