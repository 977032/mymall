package com.ffyc.exception.attach;

import com.ffyc.exception.CustomException;

public class AttachUploadException extends CustomException
{
    private static final long serialVersionUID = 2793424079096882049L;

    public AttachUploadException(String message)
    {
        super(message);
    }
}
