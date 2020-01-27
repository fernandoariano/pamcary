package br.com.pamcary;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.pamcary.aluno.model.Aluno;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT,
properties = "port:8000"
		)

class PamcaryApplicationTests {
	
	@Autowired
	static RestTemplate restTemplate ;
	HttpHeaders headers ;
	static JSONObject alunoJson ;
	@Test
	void contextLoads() {
	}
	
	public ResponseEntity<Aluno> insereAluno() throws JSONException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    restTemplate = new RestTemplate();
	    
		alunoJson = new JSONObject();
		alunoJson.put("nome", "Aluno teste");
		alunoJson.put("idade", 11);
		
		HttpEntity<String> request = 
			      new HttpEntity<String>(alunoJson.toString(),headers);
			     
	    ResponseEntity<Aluno> result = 
	      restTemplate.exchange("http://localhost:8000/aluno", HttpMethod.POST, request, Aluno.class);
		
	    return result;
		
	}
	
	
	@Test
	public void testInsertAluno() throws Exception {
		
		ResponseEntity<Aluno> result = insereAluno();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	    
	}
	
	@Test
	public void testeAtualizaAluno() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    restTemplate = new RestTemplate();
	    
	    ResponseEntity<Aluno> aluno = insereAluno();
		
	    alunoJson = new JSONObject();
		alunoJson.put("id", aluno.getBody().getId());
		alunoJson.put("nome", "Aluno alterado");
		alunoJson.put("idade", 18);
		
		HttpEntity<String>request = new HttpEntity<String>(alunoJson.toString(),headers);
		
		ResponseEntity<Aluno>result = restTemplate.exchange("http://localhost:8000/aluno", HttpMethod.PUT, request, Aluno.class);
		
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	    
	}
	
	
	@Test
	public void testeGetAluno() throws Exception {
		
		ResponseEntity<Aluno> aluno = insereAluno();
			
	    String idAluno = aluno.getBody().getId().toString(); 
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    restTemplate = new RestTemplate();
	    
		HttpEntity<String> request = 
			      new HttpEntity<String>(headers);
			     
	    ResponseEntity<Aluno> result = 
	      restTemplate.exchange("http://localhost:8000/aluno/".concat(idAluno), HttpMethod.GET, request, Aluno.class);
		
	    
	    assertThat(result.getBody().getId().toString()).isEqualTo(idAluno);
	    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	    
	}
	
	@Test
	public void testeGetAlunos() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    restTemplate = new RestTemplate();
	    
		HttpEntity<String> request = 
			      new HttpEntity<String>(headers);
			     
	    ResponseEntity<String> result = 
	      restTemplate.exchange("http://localhost:8000/aluno/alunos", HttpMethod.GET, request, String.class);
		
	    
	    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	    
	}
	
	
	
	
}


