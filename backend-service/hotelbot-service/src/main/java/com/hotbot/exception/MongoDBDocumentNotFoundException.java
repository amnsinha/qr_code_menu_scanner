package com.hotbot.exception;

public class MongoDBDocumentNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 6445590049734137009L;


    /**
     * @param noargs
     */
    public MongoDBDocumentNotFoundException() {
        super("Document not found");
    }

    /**
     * @param message
     */
    public MongoDBDocumentNotFoundException(String message) {
        super(message);
    }

    /**
     * @param throwable
     */
    public MongoDBDocumentNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
