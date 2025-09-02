package com.realProject.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public JSONObject getLocationFromIp(String ip) {
        String apiUrl = "http://ip-api.com/json/" + ip;
        String response = restTemplate.getForObject(apiUrl, String.class);

        if (response != null) {
            return new JSONObject(response);
        }
        return null;
    }
}
