package com.nidaff.api.exceptions;

public class SuchBookDoesNotExistsException extends Exception {

    private static final long serialVersionUID = -7059913244830512409L;

    public SuchBookDoesNotExistsException() {

        super("We didn’t find such book. Please check ISBN!");
    }

}
