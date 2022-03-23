package com.vanbarone.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanbarone.workshopmongo.domain.User;
import com.vanbarone.workshopmongo.dto.UserDTO;
import com.vanbarone.workshopmongo.repositories.UserRepository;
import com.vanbarone.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id){
		Optional<User> user = repo.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(),obj.getName(), obj.getEmail());		
	}
}
