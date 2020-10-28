package br.com.concrete.desafio.handler;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) {
        super(message);
    }
}
