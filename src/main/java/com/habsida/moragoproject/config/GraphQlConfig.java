package com.habsida.moragoproject.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.habsida.moragoproject.config.upload.GraphqlMultipartHandler;
import com.habsida.moragoproject.config.upload.UploadCoercing;
import graphql.language.StringValue;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import static com.habsida.moragoproject.config.upload.GraphqlMultipartHandler.SUPPORTED_RESPONSE_MEDIA_TYPES;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Slf4j
@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        GraphQLScalarType localDateScalar = localDateScalar() ;
        GraphQLScalarType uploadScalar = GraphQLScalarType.newScalar()
                .name("Upload")
                .description("Java MultipartFile type")
                .coercing(new UploadCoercing())
                .build();

        return wiringBuilder -> wiringBuilder
                .scalar(localDateScalar)
                .scalar(uploadScalar)
                .scalar(localDateTimeScalar())
                ;
    }
    @Bean
    @Order(1)
    public RouterFunction<ServerResponse> graphQlMultipartRouterFunction(
            GraphQlProperties properties,
            WebGraphQlHandler webGraphQlHandler,
            ObjectMapper objectMapper
    ) {
        String path = properties.getPath();
        RouterFunctions.Builder builder = RouterFunctions.route();
        GraphqlMultipartHandler graphqlMultipartHandler = new GraphqlMultipartHandler(webGraphQlHandler, objectMapper);
        builder = builder.POST(path, RequestPredicates.contentType(MULTIPART_FORM_DATA)
                .and(RequestPredicates.accept(SUPPORTED_RESPONSE_MEDIA_TYPES.toArray(MediaType[]::new))), graphqlMultipartHandler::handleRequest);
        return builder.build();
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

    public GraphQLScalarType localDateTimeScalar() {
        log.info("GraphQlConfig.localDateTimeScalar - creating bean");
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Java LocalDataTime type")
                .coercing(new Coercing<LocalDateTime, String>() {

                    @Override
                    public String serialize(final Object dataFetcherResult)  {
                        if (dataFetcherResult instanceof LocalDateTime) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDateTime object.");
                        }
                    }

                    @Override
                    public  LocalDateTime parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDateTime.parse((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException ex) {
                            throw new CoercingParseValueException(String.format("Not a valid date: %s.", input), ex);
                        }
                    }

                    @Override
                    public LocalDateTime parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDateTime.parse(((StringValue) input).getValue());
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
