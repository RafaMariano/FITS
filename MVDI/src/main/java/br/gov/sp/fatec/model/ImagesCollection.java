package br.gov.sp.fatec.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Document(collection = "image")
public class ImagesCollection implements Image{
	
	@Id
	@JsonView({View.Main.class})
	private String id;
	@JsonView(View.All.class)
	private org.bson.Document data;
	@JsonView(View.All.class)
	private org.bson.Document time;
	@JsonView(View.All.class)
	private org.bson.Document date;
	@JsonView(View.Alternative.class)
	private String path;

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public org.bson.Document getData(){
		return data;
	}
	public void setData(org.bson.Document data){
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
