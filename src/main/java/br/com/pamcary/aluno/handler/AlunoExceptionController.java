package br.com.pamcary.aluno.handler;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlunoExceptionController extends ResponseEntityExceptionHandler {

	StringBuilder erro;

	@ExceptionHandler(value = {AlunoNotFoundException.class , 
							   NoSuchElementException.class ,
							   EmptyResultDataAccessException.class})
	public ResponseEntity<Object> alunoNotFoundException(RuntimeException exception) {
		return new ResponseEntity<>("Aluno não encontrado", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ TransactionSystemException.class })
	protected ResponseEntity<Object> handleTransactionViolation(RuntimeException ex, WebRequest request) {

		Throwable cause = ((TransactionSystemException)ex).getRootCause();

		String errorDescription = "";

		if (cause instanceof ConstraintViolationException) {

			errorDescription = ((ConstraintViolationException) cause).getConstraintViolations().stream()
					.map(violation -> violation.getMessage()+ "\n").
					reduce("", String::concat);

		}

		return new ResponseEntity<>(errorDescription, HttpStatus.CONFLICT);

	}

}