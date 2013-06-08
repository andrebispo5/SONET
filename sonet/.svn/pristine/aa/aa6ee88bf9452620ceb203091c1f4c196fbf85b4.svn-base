package pt.ist.sonet.service.dto;

import java.io.Serializable;

public class UrlDto extends PublicationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String url;
	private String caption;
	private int scorePlus;
	private int scoreMinus;
	private int price;
	
	public UrlDto(){}
	
	public UrlDto (int id, String url, int price) {
		super("url");
		this.id = id;
		this.url = url;
		this.caption = "No Caption";
		this.price = price;
	}
	public UrlDto (int id, String url, String caption, int price) {
		super("url");
		this.id = id;
		this.caption = caption;
		this.url = url;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getScorePlus() {
		return scorePlus;
	}
	
	public void setScorePlus(int scorePlus) {
		this.scorePlus = scorePlus;
	}
	
	public int getScoreMinus() {
		return scoreMinus;
	}
	
	public void setScoreMinus(int scoreMinus) {
		this.scoreMinus = scoreMinus;
	}
	
	public void setPrice(int price){
		this.price=price;
	}
	
	public int getPrice(){
		return price;
	}
	
	public UrlDto createDto(){
		UrlDto url = new UrlDto( this.id,  this.url,  this.caption,  this.price);
		return url;
	}
	
}
