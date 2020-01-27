package br.com.pamcary.aluno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pamcary.aluno.handler.AlunoNotFoundException;
import br.com.pamcary.aluno.model.Aluno;
import br.com.pamcary.aluno.repository.AlunoRepository;

@Service
public class AlunoService  {
	
	@Autowired
	private AlunoRepository repository;
	
	
	public List<Aluno> findAll(){
		return repository.findAll();
	}
	
	public Aluno findById(Integer id){
		return repository.findById(id).orElseThrow();
	}
	
	public Optional<Aluno> save(Aluno entity) {
		return Optional.of(repository.save(entity));
		
	}
	
	public Optional<Aluno> update(Aluno entity) {
		
		if (repository.findById(entity.getId()).isEmpty() ) throw new AlunoNotFoundException();
		
		return Optional.of(repository.save(entity));
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	
}
