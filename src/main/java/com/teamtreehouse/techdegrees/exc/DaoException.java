package com.teamtreehouse.techdegrees.exc;

public class DaoException extends RuntimeException {
    private final Exception originalException;

    public DaoException(Exception originalException, String msg){
        super(msg);
        this.originalException = originalException;

    }
}
