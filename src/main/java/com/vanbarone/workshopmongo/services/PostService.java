package com.vanbarone.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanbarone.workshopmongo.domain.Post;
import com.vanbarone.workshopmongo.repositories.PostRepository;
import com.vanbarone.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;

	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public Post findById(String id){
		Optional<Post> post = repo.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		//consulta usando swing
		//return repo.findByTitleContainingIgnoreCase(text);
		
		//consulta usando mongo
		return repo.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); //acrescenta 1 dia na data máxima
		
		return repo.fullSearch(text, minDate, maxDate);
	}
	
}
