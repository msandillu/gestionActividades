package com.prueba.administradortarea.helper;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public interface HttpHelper {

    String getBody(CloseableHttpResponse closeableHttpClient) throws IOException;
}
