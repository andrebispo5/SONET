package pt.ist.sonet.service.dto;

//import pt.ist.sonet.domain.*;
import java.util.List;
import java.io.Serializable;
public class AgentDto  implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	private String email;
	private String city;
	private String country;
	private String password;
	private String type;
	private String permission;
	
	public AgentDto(){}
	
	public AgentDto(String type){
		this.type=type;
	}
	
	public AgentDto(String type, String permission){
		this.type=type;
		this.setPermission(permission);
	}
	
	public AgentDto(String username, String name, String email, String city, String country, String password) {
		this.name=name;
		this.username=username;
		this.email=email;
		this.city=city;
		this.country=country;
		this.password=password;
		this.setPermission("friend");
	}
	
	public AgentDto(String username, String name, String email, String city, String country, String password, String type) {
		this.name=name;
		this.username=username;
		this.email=email;
		this.city=city;
		this.country=country;
		this.password=password;
		this.type=type;
		this.setPermission("FRIEND");
	}
	
	public AgentDto(String username, String name, String email, String city, String country, String password, String type, String permission) {
		this.name=name;
		this.username=username;
		this.email=email;
		this.city=city;
		this.country=country;
		this.password=password;
		this.type=type;
		this.setPermission(permission);
	}

	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
		
}
