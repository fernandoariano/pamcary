package br.com.pamcary.aluno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "Aluno")
@Data
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	@Size(min = 3,max=40,message = "Nome deve ter entre 3 e 40 caracteres")
	@NotBlank(message = "Nome deve ser preenchido")
	private String nome;
	
	@Column(name = "idade")
	@NotNull(message = "Idade deve ser preenchida")
	@PositiveOrZero(message = "Idade deve ser maior ou igual a 0")
	private Integer idade;
	
}
