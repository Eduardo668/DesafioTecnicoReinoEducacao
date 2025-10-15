package br.com.reinoeducacao.exceptions;

public class InsufficientMilesException extends RuntimeException {
    public InsufficientMilesException(String message) {
        super(message);
    }
}
