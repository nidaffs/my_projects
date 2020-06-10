package com.nidaff.api.exceptions;

public class BookAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -351032283042677439L;

    public BookAlreadyExistsException() {

        super("Such book is already exists in this department! Please choose another department");
    }

}
