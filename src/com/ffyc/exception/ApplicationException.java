package com.ffyc.exception;

public abstract class ApplicationException extends com.nationsky.pub.framework.exception.ApplicationException
{

    private static final long serialVersionUID = 8837971627270686823L;

    public ApplicationException(String message)
    {
        super(message);
    }

    public abstract int getErrorCode();
}
