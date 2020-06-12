package com.nidaff.api.exceptions;

public class SuchBookDoesNotExistsException extends Exception {

    private static final long serialVersionUID = -7059913244830512409L;

    public SuchBookDoesNotExistsException() {

        super("The ISBN given was invalid. Please double-check the number!");
    }

}
