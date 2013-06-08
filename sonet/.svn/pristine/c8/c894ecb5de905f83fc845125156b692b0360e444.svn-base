package pt.ist.sonet.domain;

public enum Permission {
	
	PUBLIC {
		@Override
		public boolean canPermission(Agent agent, Publication pub) {
			return true;	
		}
		@Override
		public boolean canPermission(Agent agent, Agent agent2) {
			return true;	
		}
	},
	
	FRIEND {
		@Override
		public boolean canPermission(Agent agent, Publication pub) {
			String targetUsername=pub.getAgent().getUsername();
			for(Agent agentA : agent.getFriendsSet()){
				if (agentA.getUsername().equals(targetUsername)){
					return true;
				}
			}
			return false;
		}
		@Override
		public boolean canPermission(Agent agent, Agent agent2) {
			String targetUsername=agent2.getUsername();
			for(Agent agentA : agent.getFriendsSet()){
				if (agentA.getUsername().equals(targetUsername)){
					return true;
				}
			}
			return false;
		}
	},
	
	FRIENDOFRIEND()  {

		@Override
		public boolean canPermission(Agent agent, Publication pub){
			Agent pubOwner = pub.getAgent();
			String commenterUsername = agent.getUsername();
			for(Agent a1 : pubOwner.getFriendsSet()) {
				
					if(a1.getUsername().equals(commenterUsername)){
						return true;
					}
					for(Agent friend : a1.getFriendsSet()) {
						
						if(friend.getUsername().equals(commenterUsername)){
							return true;
						}
					}
				
			}
			return false;
		}
		
		@Override
		public boolean canPermission(Agent agentL, Agent agentT){

			for(Agent a1 : agentL.getFriendsSet()) {
				
					if(a1.getUsername().equals(agentT.getUsername())){
						return true;
					}
					for(Agent friend : a1.getFriendsSet()) {
						
						if(friend.getUsername().equals(agentT.getUsername())){
							return true;
						}
					}
				
			}
			return false;
		}
	},
	
	PRIVATE{
		@Override
		public boolean canPermission(Agent agent, Publication pub){
			return false;
		}
		@Override
		public boolean canPermission(Agent agent, Agent a2){
			return false;
		}
	};

	public abstract boolean canPermission(Agent agent, Publication pub);
	public abstract boolean canPermission(Agent agentL, Agent agentT);
	public static Permission generatePermission(String permission){
		if(permission.equals("FRIEND")){
			return Permission.FRIEND;
		}else if(permission.equals("FRIENDOFRIEND")){
			return Permission.FRIENDOFRIEND;
		}else if(permission.equals("PUBLIC")){
			return Permission.PUBLIC;
		}else{
			return Permission.PRIVATE;
		}
		
	}
}