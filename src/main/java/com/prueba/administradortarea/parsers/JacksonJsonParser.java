package com.prueba.administradortarea.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jdk.nashorn.internal.runtime.ParserException;

import java.io.IOException;
import java.util.List;


public class JacksonJsonParser implements Parser {

    private final ObjectMapper objectMapper;

    public JacksonJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T parseToObject(String content, Class<T> valueType) throws IOException {
        return objectMapper.readValue(content, valueType);
    }

    @Override
    public String parseToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    @Override
    public Integer parseToInteger(String content) {
        Integer value;
        try{
            value = Integer.parseInt(content);
        } catch(NumberFormatException exception){
            value = null;
        }
        return value;
    }

    @Override
    public <T> List<T> parseToListObject(String toParse, Class<T> type) throws ParserException {
        toParse = normalizeInput(toParse);

        try {
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(toParse, javaType);
        } catch (Exception e) {
            throw new ParserException(e.getMessage());
        }
    }

    private String normalizeInput(String input) {
        if (input == null || input.isEmpty()) {
            input = "{}";
        }

        return input;
    }

}
