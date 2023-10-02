package com.habsida.moragoproject.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Component
public class GlobalExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        StringBuilder errorMessages = new StringBuilder();

        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cEx = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = cEx.getConstraintViolations();

            for (ConstraintViolation<?> violation : violations) {
                errorMessages.append(violation.getPropertyPath().toString() + " - "
                + violation.getMessageTemplate());
            }

        } else if (ex instanceof IllegalArgumentException){
            errorMessages.append(ex.getMessage());
        } else if (ex instanceof EmptyResultDataAccessException) {
            errorMessages.append(ex.getMessage());
        } else if (ex instanceof TokenException) {
            errorMessages.append(ex.getMessage());
        } else if (ex instanceof TokenRefreshException) {
            errorMessages.append(ex.getMessage());
        }
        else {
            return null;
        }
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(errorMessages.toString())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
