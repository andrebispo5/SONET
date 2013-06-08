package pt.ist.sonet.service;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.AgentNotFoundException;
import pt.ist.sonet.service.*;
import junit.framework.TestCase;
import jvstm.Atomic;

public abstract class SonetServiceTestCase extends TestCase {
    static {
    	if(FenixFramework.getConfig()==null) {
    		FenixFramework.initialize(new Config() {{
		    domainModelPath="src/main/dml/domain.dml";
		    dbAlias = "//localhost:3306/sonetdb";
		    dbUsername = "sonet";
		    dbPassword = "s0n3t";
		    rootClass=Sonet.class;
    		}});
    	}
    }
    
    protected SonetServiceTestCase(String msg) {
    	super(msg);
    }
    
    protected SonetServiceTestCase() {
    	super();
    }
    protected void setUp() {
    	cleanSonet();
    }	
    protected void tearDown() {
	cleanSonet();
    }

    /**
     * Clears the persistent state in the Sonet application in the sense that the current root object
     * no longer holds any Agent instance.
     **/
    @Atomic
    protected void cleanSonet() {
	Sonet sonet = FenixFramework.getRoot();
	Set<Agent> allAgents = sonet.getAgentSet();
	if(allAgents!=null)
		allAgents.clear();
    }
    
    @Atomic
    protected void addOrganization(String username, String name, String email, String city,
		String country, String password){
    	Sonet sonet = FenixFramework.getRoot();
    	Organization orgExistingName = new Organization(username,name,email,city,country,password);
    	sonet.addAgent(orgExistingName);
    }
    
    @Atomic
    protected void addIndividual(String username, String name, String email, String city,
		String country, String password){
    	Sonet sonet = FenixFramework.getRoot();
    	Individual indExistingName = new Individual(username,name,email,city,country,password,Permission.generatePermission("PUBLIC"));
    	sonet.addAgent(indExistingName);
    }
    
    @Atomic
    protected void addPub(String agent, int id, String text){
    	Sonet sonet = FenixFramework.getRoot();
    	Agent owner=sonet.getAgentByUsername(agent);
    	Note pub= new Note(id, text);
    	owner.addPub(pub);
    }
    
    @Atomic
    protected int votePlusCount(int id){
    	Sonet sonet = FenixFramework.getRoot();
    	Publication pub = sonet.getPublicationById(id);
    	int votes = pub.getScorePlus();
    	return votes;
    }
    
    @Atomic
    protected int voteMinusCount(int id){
    	Sonet sonet = FenixFramework.getRoot();
    	Publication pub = sonet.getPublicationById(id);
    	int votes = pub.getScoreMinus();
    	return votes;
    }
    
    @Atomic
    protected boolean getAgentByName(String name) {
    	Sonet sonet = FenixFramework.getRoot();
    	Agent agent = sonet.getAgentByName(name);
    	if(agent != null)
    		return true;
    	return false;
    }
    
    @Atomic
    protected void setPolemicPubLimit(int value){
    	Sonet sonet=FenixFramework.getRoot();
    	sonet.setPolemicPubLimit(1);
    }
    
    @Atomic
    protected boolean hasRequest(String hasR , String didR ) {
    	
    	Sonet sonet = FenixFramework.getRoot();
    	Agent requested = sonet.getAgentByUsername(hasR);
    	Agent adder = sonet.getAgentByUsername(didR);
    	boolean has = requested.getRequests().contains(adder);
    	return has;
    }
    
    @Atomic
 protected int numberRequests(String hasR) {
    	
    	Sonet sonet = FenixFramework.getRoot();
    	Agent requested = sonet.getAgentByUsername(hasR);
    	int nr = requested.getRequests().size();
    	return nr;
    }
  @Atomic  
protected int numberRequestsOrg(String hasR) {
    	
    	Sonet sonet = FenixFramework.getRoot();
    	Agent requested = sonet.getAgentByName(hasR);
    	int nr = requested.getRequests().size();
    	return nr;
    }
    
    
    @Atomic
    protected Agent returnAgentByName(String name) throws AgentNotFoundException {
    	Sonet sonet = FenixFramework.getRoot();
    	Agent agent = sonet.getAgentByName(name);
    	if (agent != null)
    		return agent;
    	throw new AgentNotFoundException(name);
    }
    
    @Atomic
    protected Agent returnAgentByUsername(String name) throws AgentNotFoundException {
    	Sonet sonet = FenixFramework.getRoot();
    	Agent agent = sonet.getAgentByUsername(name);
    	if (agent !=null)
    		return agent;
    	throw new AgentNotFoundException(name);
    }
    
    
    
    @Atomic
    protected int getNumberOfAgents(){
    	Sonet sonet = FenixFramework.getRoot();
    	int count= sonet.getAgentCount();
    	return count;
    	
    }
    
    @Atomic
    protected void addPubUrl(Agent agent, int id, String content, int price){
    	Sonet sonet = FenixFramework.getRoot();
    	Url pub= new Url(id, content, price);
    	agent.addPub(pub);	
    }
    
    @Atomic
    protected void addPubNote(Agent agent, int id, String content){
    	Sonet sonet = FenixFramework.getRoot();
    	Note pub= new Note(id, content);
    	agent.addPub(pub);	
    }
    
    
}
