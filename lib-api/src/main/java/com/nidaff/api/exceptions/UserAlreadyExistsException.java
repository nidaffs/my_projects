package com.nidaff.api.exceptions;

public class UserAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 2448722756313310307L;

    public UserAlreadyExistsException() {

        super("User with such email already exists!");
    }

}
