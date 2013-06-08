package pt.ist.sonet.domain;

import pt.ist.sonet.service.dto.NoteDto;
import pt.ist.sonet.service.dto.UrlDto;

public class Url extends Url_Base {
    
	public Url(){
		super();
	}
	
    public  Url(int id,  String url, int price) {
        super();
        init(id);
        setUrl(url);
        setPrice(price);
    }
    public  Url(int id,  String url, String caption, int price) {
        super();
        init(id, caption);
        setUrl(url);
        setPrice(price);
    }
    
    @Override
    public UrlDto createDto(){
    	UrlDto url = new UrlDto(super.getId(), super.getUrl(), super.getCaption(), super.getPrice());
    	return url;
    }
}