package br.com.reinoeducacao.exceptions;

public class ClienteException extends RuntimeException {
    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }
}

