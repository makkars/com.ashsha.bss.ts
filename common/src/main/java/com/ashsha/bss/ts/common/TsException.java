package com.ashsha.bss.ts.common;

public class TsException extends RuntimeException
{
    private static final long serialVersionUID = -6652299868962605757L;

    private String code;
    private int httpCode = 400;

    public TsException(String message)
    {
        super(message);
        this.code = message;
    }

    public TsException(String code, String message)
    {
        super(message);
        this.code = code;
    }

    public TsException(Exception cause)
    {
        super(cause);
        this.code = "ERROR";
    }

    public TsException(String code, String message, Exception innerException)
    {
        super(message);
        super.addSuppressed(innerException);
        this.code = code;
    }
}
