package pt.ist.sonet.domain;

import pt.ist.sonet.service.dto.NoteDto;

public class Note extends Note_Base {
    
	public Note(){
		super();
	}
	
	
    public  Note(int id, String text) {
        super();
        init(id);
        setText(text);
    }
    public  Note(int id, String text, String caption) {
        super();
        init(id,caption);
        setText(text);
    }
    
    @Override
    public NoteDto createDto(){
    	NoteDto note = new NoteDto(super.getId(), super.getText(), super.getCaption());
    	return note;
    }
}
