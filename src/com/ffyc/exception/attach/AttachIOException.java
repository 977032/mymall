package com.ffyc.exception.attach; 

import com.ffyc.exception.CustomException;

public class AttachIOException extends CustomException
{
    private static final long serialVersionUID = -1444621781122128102L;

    public AttachIOException(String message)
    {
        super(message);
    }
}
