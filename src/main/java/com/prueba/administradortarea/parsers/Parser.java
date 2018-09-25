package com.prueba.administradortarea.parsers;


import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.nashorn.internal.runtime.ParserException;

import java.io.IOException;
import java.util.List;

public interface Parser {

    <T> T parseToObject(String content, Class<T> valueType) throws IOException;

    String parseToString(Object o) throws JsonProcessingException;

    Integer parseToInteger(String content);

    <T> List<T> parseToListObject(String toParse, Class<T> type) throws ParserException;

}
