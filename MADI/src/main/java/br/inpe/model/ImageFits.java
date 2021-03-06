package br.inpe.model;

import java.io.IOException;

import java.text.ParseException;
import org.bson.Document;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;
import nom.tam.util.Cursor;

public class ImageFits {
	private Fits fits;
	private Document document;
	private String path;
	private ConvertTime time;

	public ImageFits(String fits) throws FitsException, ParseException, IOException {	

		this.fits = new Fits(fits);
		this.document = new Document();
		this.path = fits;
		setDocument();
		this.time = new ConvertTime();
	}
	public String getPath() {
		return path;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setKeyValue(String key, String value) {
		this.document.put(key, value);
	}

	private void setDocument() throws FitsException, ParseException, 
									IOException, NullPointerException {

		Header header = this.fits.getHDU(0).getHeader();
		Cursor<String, HeaderCard> c = header.iterator();
		
		while (c.hasNext()) {
			
			HeaderCard bb = c.next();

			if (bb.getKey().equals("END") == false) {

				if (bb.getKey() != null) {
					if (bb.getKey().isEmpty() == false) {

						if (bb.getKey().equals("COMMENT")) {
							this.document.put(bb.getKey(), bb.getComment());
						} else if (bb.getValue() != null) {
							this.document.put(bb.getKey(), bb.getValue());
						} else {
							this.document.put(bb.getKey(), "");
						}
					}
				}
			}
		}
		this.document.put("_id", this.path.substring(this.path.lastIndexOf("/") + 1));
	}

	
	
	public Image getImage() throws ParseException {
		
		//if(this.documents.containsKey("ST_PARAM");
			return getImagesCollection();
		//else
			//return getImageComplete();
	}
	
	private ImagesCollection getImagesCollection() throws ParseException{
		
		ImagesCollection image = new ImagesCollection();
		Document docImagesCollection = this.document;
	
		image.setId(docImagesCollection.getString("_id"));
		docImagesCollection.remove("_id");
		String timeJuliano = null;
		timeJuliano = docImagesCollection.get("DATE-OBS").toString();
		image.setDate(this.time.getDate(timeJuliano));
		image.setTime(this.time.getTime(timeJuliano));
		
		image.setData(docImagesCollection);
		return image;
	}
	
	
	//na versão final, irá sair esse if
//	if (docImagesCollection.containsKey("DATE-OBS"))

///		else if (docImagesCollection.containsKey("DATE"))
//		timeJuliano = docImagesCollection.get("DATE").toString();
	
	
}
