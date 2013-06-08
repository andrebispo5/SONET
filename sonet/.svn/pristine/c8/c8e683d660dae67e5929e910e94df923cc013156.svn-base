package pt.ist.sonet.service.dto;

import java.io.Serializable;

import pt.ist.sonet.domain.*;

public class OrganizationDto extends AgentDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String username;
	private String email;
	private String city;
	private String country;
	private String password;
	
	
	
public OrganizationDto(){}
	
	public OrganizationDto(String username, String name, String email, String city, String country, String password) {
		super("Organization", "FRIEND");
		this.username=username;
		this.name=name;
		this.email=email;
		this.city=city;
		this.country=country;
		this.password=password;

	}
	public OrganizationDto(String username, String name, String email, String city, String country, String password, String permission) {
		super("Organization", permission);
		this.username=username;
		this.name=name;
		this.email=email;
		this.city=city;
		this.country=country;
		this.password=password;
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
}
