package com.prueba.administradortarea.user.api.external;


import com.prueba.administradortarea.helper.HttpHelper;
import com.prueba.administradortarea.models.domain.external.TokenResponse;
import com.prueba.administradortarea.models.domain.external.User;
import com.prueba.administradortarea.parsers.Parser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public class ApiUserImpl implements ApiUser{

    private String urlApi;
    private String userName;
    private String password;
    private CloseableHttpClient client;
    private HttpHelper httpHelper;
    private Parser parser;

    public ApiUserImpl(String urlApi, String userName, String password, CloseableHttpClient client, HttpHelper httpHelper, Parser parser){
        this.urlApi = urlApi;
        this.userName = userName;
        this.password = password;
        this.client = client;
        this.httpHelper = httpHelper;
        this.parser = parser;
    }

    private String getToken(){
        try {
            HttpPost httpPost = new HttpPost(urlApi+"/login");

            String json = "{\"username\":\""+userName+"\", \"password\":\""+password+"\"}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = client.execute(httpPost);
            String content = httpHelper.getBody(response);
            TokenResponse tokenResponse = parser.parseToObject(content, TokenResponse.class);

            return tokenResponse.getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAllUsers(){
        String authorization = getToken();

        try {
            HttpGet httpGet = new HttpGet(urlApi+"/users");
            httpGet.setHeader("Authorization", authorization);

            CloseableHttpResponse response = client.execute(httpGet);
            String content = httpHelper.getBody(response);

            return parser.parseToListObject(content, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserById(Integer userId){
        String authorization = getToken();

        try {
            HttpGet httpGet = new HttpGet(urlApi+"/users/"+userId.toString());
            httpGet.setHeader("Authorization", authorization);

            CloseableHttpResponse response = client.execute(httpGet);
            String content = httpHelper.getBody(response);

            return parser.parseToObject(content, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
