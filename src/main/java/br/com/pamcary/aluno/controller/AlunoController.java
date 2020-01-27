package br.com.pamcary.aluno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pamcary.aluno.model.Aluno;
import br.com.pamcary.aluno.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/aluno")
@Api(value="API alunos")

public class AlunoController {
	
		@Autowired
		AlunoService service;
		
		@ApiOperation(value = "Cadastro de aluno")
		@ApiResponse(code = 409, message = "Dados incosistentes")
		@PostMapping
		@ResponseBody
		public ResponseEntity<Aluno> save(@RequestBody Aluno input)  {
			
			return new ResponseEntity<>(service.save(input).orElseThrow(),HttpStatus.CREATED);
			
		}
		
		@ApiOperation(value = "Consultar aluno")
		@GetMapping(value="/{id}")
		public ResponseEntity<Aluno> findById(@PathVariable("id") Integer id ) {
			return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
		}
		
		@ApiOperation(value = "Consultar todos os alunos")
		@GetMapping(value="/alunos")
		public ResponseEntity<List<Aluno>> findAll() {
			return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
		}
		
		@ApiOperation(value = "Atualizar aluno")
		@PutMapping
		public ResponseEntity<Aluno> update(@RequestBody Aluno input)  {
			return new ResponseEntity<>(service.update(input).orElseThrow(),HttpStatus.OK);
		}

		@ApiOperation(value = "Excluir aluno")
		@DeleteMapping(value="/{id}")
		public ResponseEntity<String> destroy( @PathVariable("id") Integer id ) {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	
}
