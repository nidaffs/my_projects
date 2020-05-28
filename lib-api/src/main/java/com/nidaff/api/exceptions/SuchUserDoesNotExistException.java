package com.nidaff.api.exceptions;

public class SuchUserDoesNotExistException extends Exception {

    private static final long serialVersionUID = 8042482825233177957L;

    public SuchUserDoesNotExistException() {

        super("Such user does not exist!");
    }

}
