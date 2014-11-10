package co.uk.brecknock;

import java.util.ArrayList;
import java.util.List;

/*
 * Gateway is a Singleton
 */
public class Gateway {
	private static  Gateway gateway = null;
	private List<Resource> resources = new ArrayList<Resource>();
	
	private Gateway(int resources){
		for(int i=0;i<resources;i++){
			this.resources.add(new Resource(i));
		}
	}

	public static Gateway getInstance(){
		return gateway;
	}
	
	public static Gateway getInstance(int resources){
		if(gateway == null){
			gateway = new Gateway(resources);
		}
		return getInstance();
	}
	
	public boolean hasInactiveResources(){
		for(Resource r:resources){
			synchronized(r){
				if(!r.isActive()){
					return true;
				}
			}
		}	
		return false;
	}
	
	public void send(Message msg) {
		//send msg to first inactive resource
		
		for(Resource r:resources){
			if(!r.isActive()){
				r.setActive(true);
				r.handleMessage(msg);
				break;
			}
		}
	}

}
