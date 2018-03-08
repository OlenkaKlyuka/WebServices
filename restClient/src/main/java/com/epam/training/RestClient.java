package com.epam.training;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.File;

public class RestClient {

    private Client client;
    private WebResource webResource;
    private ClientResponse response;

    public RestClient() {
        client = Client.create();
    }

    public String Get(String url) {
        webResource = client.resource(url);
        response = webResource.accept("application/json")
                .get(ClientResponse.class);
        return response.getEntity(String.class);
    }

    public void Post(String url, File file) {
        webResource = client.resource(url);
        response = webResource.accept("application/json")
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, transformToUrlUncodedType(file));
        response.getEntity(String.class);
    }

    public String Delete(String url) {
        webResource = client.resource(url);
        response = webResource.accept("application/json")
                .delete(ClientResponse.class);
        return response.getEntity(String.class);
    }

    private String transformToUrlUncodedType(File file) {
        return "name=" + file.getName();
    }
}