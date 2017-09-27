package com.highmobility.core;

public class CryptoException extends Exception {
    enum Type {
        INTERNAL_ERROR, INVALID_ARGUMENT
    }
    Type type;

    CryptoException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
