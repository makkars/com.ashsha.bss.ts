package com.ashsha.bss.ts.rest.service.validators;

import com.ashsha.bss.ts.entity.dto.common.MessageDTO;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerValidationHandler
{
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex)
    {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();

        return processFieldError(error);
    }

    @ExceptionHandler (ConstraintViolationException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(ConstraintViolationException ex)
    {
        Set<ConstraintViolation<?>> result = ex.getConstraintViolations();

        String errorMessage = null;
        // Get the first error:
        for (ConstraintViolation<?> s : result)
        {
            errorMessage = s.getMessageTemplate();
            break;
        }
        return processFieldError(errorMessage);
    }
/*
Need to see how to handle exception
    @ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(Exception ex)
    {
        String result = ex.getMessage();

        return new MessageDTO(MessageDTO.MessageType.ERROR, "should send this");
    }
*/

    private MessageDTO processFieldError(String error)
    {
        MessageDTO message = null;
        if (error != null)
        {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(error, null, currentLocale);
            message = new MessageDTO(MessageDTO.MessageType.ERROR, msg);
        }
        return message;
    }

    private MessageDTO processFieldError(FieldError error)
    {
        MessageDTO message = null;
        if (error != null)
        {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
            message = new MessageDTO(MessageDTO.MessageType.ERROR, msg);
        }
        return message;
    }
}
