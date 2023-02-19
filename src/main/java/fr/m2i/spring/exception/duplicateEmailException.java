package fr.m2i.spring.exception;

public class duplicateEmailException extends Exception {

    public duplicateEmailException() {
        super("cet email existe déja!");
    }
}
