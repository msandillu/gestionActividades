package com.prueba.administradortarea.helper;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpHelperImpl implements HttpHelper{

    public String getBody(CloseableHttpResponse closeableHttpClient) throws IOException {
        return EntityUtils.toString(closeableHttpClient.getEntity());
    }
}
