package com.project;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.validation.Errors;

public class ValidatorTest {

    public void validateRejectIfEmptyOrWhitespace(final Errors errors, final String field, final String errorCode) {
        verify(errors).rejectValue(field, errorCode, null, null);
    }

    public void validateRejectIfEmptyOrWhitespace(final Errors errors, final String field, final String errorCode, final Object[] errorArgs) {
        verify(errors).rejectValue(field, errorCode, errorArgs, null);
    }

    public void validateRejectIfEmptyOrWhitespace(final Errors errors, final String field, final String errorCode, final Object[] errorArgs, final int count) {
        verify(errors, times(count)).rejectValue(field, errorCode, errorArgs, null);
    }

    public void validateRejectValue(final Errors errors, final String field, final String errorCode, final Object[] errorArgs) {
        verify(errors).rejectValue(field, errorCode, errorArgs, "");
    }

    public void validateRejectValue(final Errors errors, final String field, final String errorCode, final Object[] errorArgs, final int count) {
        verify(errors, times(count)).rejectValue(field, errorCode, errorArgs, "");
    }

    public void validateRejectValue(final Errors errors, final String field, final String errorCode) {
        verify(errors).rejectValue(field, errorCode);
    }

    public void validateRejectValue(final Errors errors, final String field, final String errorCode, final int count) {
        verify(errors, times(count)).rejectValue(field, errorCode);
    }

    public void validateNeverRejectValue(final Errors errors) {
        verify(errors, never()).rejectValue(anyString(), anyString(), anyString());
        verify(errors, never()).rejectValue(anyString(), anyString(), isNull(Object[].class), anyString());
    }
}
