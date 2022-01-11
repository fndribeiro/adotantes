package br.com.petbytes.adotantes.ports.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7281364641211985484L;
	
	private String message;

	public NotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
