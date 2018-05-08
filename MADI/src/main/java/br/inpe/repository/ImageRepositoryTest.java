package br.inpe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.inpe.model.Image;

public interface ImageRepositoryTest extends MongoRepository<Image, String>{

}
