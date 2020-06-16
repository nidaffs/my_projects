package com.nidaff.api.exceptions;

public class SuchBookDoesNotExistException extends Exception {

    private static final long serialVersionUID = -7059913244830512409L;

    public SuchBookDoesNotExistException() {

        super("The ISBN given was invalid. Please double-check the number!");
    }

}
