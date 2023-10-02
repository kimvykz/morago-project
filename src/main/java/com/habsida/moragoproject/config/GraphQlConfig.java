package com.habsida.moragoproject.config;


import graphql.language.StringValue;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Slf4j
@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        GraphQLScalarType scalarType = localDateScalar() ;

        return wiringBuilder -> wiringBuilder
                .scalar(scalarType)
                ;
    }

    public GraphQLScalarType localDateScalar() {
        log.info("GraphQlConfig.localDateScalar - creating bean");
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("Java LocalData type")
                .coercing(new Coercing<LocalDate, String>() {

                    @Override
                    public String serialize(final Object dataFetcherResult)  {
                        if (dataFetcherResult instanceof LocalDate) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public  LocalDate parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException ex) {
                            throw new CoercingParseValueException(String.format("Not a valid date: %s.", input), ex);
                        }
                    }

                    @Override
                    public LocalDate parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue());
                            } catch (DateTimeParseException ex) {
                                throw new CoercingParseLiteralException(ex);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();
    }


}
