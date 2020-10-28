package br.com.concrete.desafio.handler;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String message) {
        super(message);
    }
}
