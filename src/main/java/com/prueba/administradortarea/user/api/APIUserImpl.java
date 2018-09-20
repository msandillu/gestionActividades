package com.prueba.administradortarea.user.api;



import org.apache.http.client.HttpClient;
import org.apache.http.config.ConnectionConfig;

import javax.print.DocFlavor;
import javax.xml.bind.JAXBContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIUserImpl implements APIUser {

    private static final String loginURL = "https://gentle-eyrie-95237.herokuapp.com/login";

    public String login (String username, String password){
        HttpClient httpClient;

        try {
            URL targetUrl = new URL(loginURL);
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getUsers(){
        return "";
    }

    public String getUsersById(Integer userId){
        return "";
    }
}
