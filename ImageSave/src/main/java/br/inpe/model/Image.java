package br.inpe.model;

import java.io.IOException;
import java.text.ParseException;
import org.bson.Document;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;
import nom.tam.util.Cursor;

public class Image {
	private Fits fits;
	private Document document;
	private String path;
	private ConvertTime convertTime;
	
	public Image(String fits) throws FitsException, IOException, ParseException{
		this.fits = new Fits(fits);
		this.document = new Document();
		this.path = fits;
		this.convertTime = new ConvertTime();
		setDocument();
	}
	
	public String getPath(){
		return path;
	}
	
	public Document getDocument(){
		return this.document;
	}
	
	public void setKeyValue(String key, String value){
		this.document.put(key,value);
	}
	
	private void setDocument() throws FitsException, IOException, ParseException{
		Header header = this.fits.getHDU(0).getHeader();

		Cursor<String, HeaderCard> c = header.iterator();

		while(c.hasNext() ){
			HeaderCard bb = c.next();

			if(bb.getKey().equals("END") == false){

				if (bb.getKey() != null){
					if (bb.getKey().isEmpty() == false){

						if (bb.getKey().equals("COMMENT")){
							this.document.put(bb.getKey(),bb.getComment());
						}
						else if(bb.getValue() != null) {
							this.document.put(bb.getKey(), bb.getValue());

						}
						else {
							this.document.put(bb.getKey(),"");
						}
					}
				}
			}

		}	
		
		Document doc = null;
		if (document.containsKey("DATE-OBS"))
			 doc = this.convertTime.setTimeAndDay(document.get("DATE-OBS").toString());
		else if (document.containsKey("DATE") )
			doc = this.convertTime.setTimeAndDay(document.get("DATE").toString());
		
		this.document.put("TIMEANDDAY",doc);
		this.document.put("_id", this.path.substring(this.path.lastIndexOf("/")+1));
	}
	
	
}
