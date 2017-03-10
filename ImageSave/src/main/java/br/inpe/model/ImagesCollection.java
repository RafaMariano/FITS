package br.inpe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class ImagesCollection {
	@Id
	private String id;

	private org.bson.Document document;

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public org.bson.Document getDocument(){
		return document;
	}
	public void setDocument(org.bson.Document document){
		this.document = ToOrganize(document);
	}
	
	private org.bson.Document ToOrganize(org.bson.Document doc){
		
		if (doc.containsKey("_id")){
		setId(doc.get("_id").toString());
		doc.remove("_id");
		}
		return doc;
	}
}
