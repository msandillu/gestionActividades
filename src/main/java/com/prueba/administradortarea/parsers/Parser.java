package com.prueba.administradortarea.parsers;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface Parser {

    <T> T parseToObject(String content, Class<T> valueType) throws IOException;

    String parseToString(Object o) throws JsonProcessingException;

}
