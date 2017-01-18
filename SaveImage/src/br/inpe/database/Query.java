package br.inpe.database;

import org.bson.Document;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;

import br.inpe.filesystem.Image;

public class Query {
	
	public void insertDocument(Image image) {
		try{
			MongoCollection<Document> collection =	Mongo.getInstance().getDataBase().getCollection("inpe");
			collection.insertOne(image.getDocument());
		}catch(MongoWriteException a){
			System.err.println("Está imagem já existe cadastrado no banco " + a);
			//if(caminho_da_imagem != caminho_da_imagem_nova_pasta)
			//	imagem vai para a nova pasta
			//else
			//deleta a imagem
		}
		}
}
