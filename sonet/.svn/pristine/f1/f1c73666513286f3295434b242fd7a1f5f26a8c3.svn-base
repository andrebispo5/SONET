package pt.ist.sonet.service.dto;

import java.io.Serializable;

import pt.ist.sonet.domain.*;

public class CommentDto   implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	private String agentName;
	public  CommentDto(){}
    public  CommentDto(int id, String agentName, String text) {
        super();
        this.agentName = agentName;
        this.id = id;
        this.text = text;
    }
    
    public int getId(){
    	return id;
    }
    
    public String getText(){
    	return text;
    }
    
    public void setId(int id){
    	this.id = id;
    }
    
    public void setText(String text){
    	this.text = text;
    }
    
    public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public  CommentDto(int id) {
        super();
        this.id = id;
        this.text = "";
    }
    
}
    
  