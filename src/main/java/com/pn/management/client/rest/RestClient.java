package com.pn.management.client.rest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;


public class RestClient {

    @Autowired
    static RestClientService restClientService;

    public static void main(String[] args) throws JSONException {

        restClientService.addOrder(restClientService.getJsonObject());

        restClientService.getOrderById(1);

    }



}
