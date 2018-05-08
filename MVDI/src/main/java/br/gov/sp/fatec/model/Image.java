package br.gov.sp.fatec.model;

public interface Image {
	public String getId();
	public org.bson.Document getData();
	public org.bson.Document getDate();
	public org.bson.Document getTime();
	public void setPath(String path);
	public String getPath();
}
