package com.pn.management.client.rest;

import com.pn.management.model.Order;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    static String url = "http://localhost:8080/order/";

    public static JSONObject getJsonObject() throws JSONException {
        JSONObject orderJsonObject = new JSONObject();
        orderJsonObject.put("ticker", "AB21X");
        orderJsonObject.put("quantity", 10);
        orderJsonObject.put("price", 1.3);
        orderJsonObject.put("direction", "B");
        return orderJsonObject;
    }

    public static void addOrder(JSONObject orderJsonObject){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(orderJsonObject.toString(), headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(url, request, String.class);
        LOGGER.info(String.valueOf(responseEntityStr));
    }

    public static void getOrderById(int orderId) {
        String urlbyId = url +"/" + orderId;
        RestTemplate restTemplate = new RestTemplate();
        Order order = restTemplate.getForObject(urlbyId, Order.class);
        LOGGER.info(order.toString());
    }
}
