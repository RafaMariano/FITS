package br.inpe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class ImagesCollection implements Image {

	@Id
	private String id;
	private org.bson.Document data;
	private org.bson.Document time;
	private org.bson.Document date;
	private String path;

	public ImagesCollection() {}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public org.bson.Document getData() {
		return data;
	}

	public void setData(org.bson.Document data) {
		this.data = data;
	}

	public org.bson.Document getTime() {
		return time;
	}

	public void setTime(org.bson.Document time) {
		this.time = time;
	}

	public org.bson.Document getDate() {
		return date;
	}

	public void setDate(org.bson.Document date) {
		this.date = date;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}

}
