package com.nidaff.api.exceptions;

public class DepartmentAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -8875950538864633952L;

    public DepartmentAlreadyExistsException() {

        super("Such department has already existed!");
    }

}
