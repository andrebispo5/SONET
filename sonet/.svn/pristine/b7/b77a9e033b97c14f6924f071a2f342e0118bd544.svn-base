package pt.ist.sonet.service.dto;

import java.io.Serializable;

public class NoteDto extends PublicationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	private String caption;
	private int scorePlus;
	private int scoreMinus;
	
	public NoteDto(){}
	
	public NoteDto(int id, String text) {
		super("note");
		this.id = id;
		this.caption = "No Caption";
		this.text = text;
	}
	
	public NoteDto(int id, String text, String caption) {
		super("note");
		this.id = id;
		this.caption = caption;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public int getId() {
		return id;
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
	
	public NoteDto createDto(){
		NoteDto note = new NoteDto(this.id, this.text, this.caption);
		return note;
	}
}
