package com.prueba.administradortarea.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


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

}
