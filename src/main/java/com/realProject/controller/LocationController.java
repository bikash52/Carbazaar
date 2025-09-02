package com.realProject.controller;

import com.realProject.service.GeoLocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final GeoLocationService geoLocationService;
    private final RestTemplate restTemplate = new RestTemplate();

    public LocationController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @GetMapping
    public String getLocation(HttpServletRequest request) {
        String clientIp = getClientIp(request);
        System.out.println("Detected IP Address: " + clientIp); // Debug log

        JSONObject locationData = geoLocationService.getLocationFromIp(clientIp);

        if (locationData != null) {
            return String.format("City: %s, Region: %s, Country: %s, Latitude: %s, Longitude: %s",
                    locationData.optString("city", "Unknown"),
                    locationData.optString("regionName", "Unknown"),
                    locationData.optString("country", "Unknown"),
                    locationData.optString("lat", "Unknown"),
                    locationData.optString("lon", "Unknown"));
        }

        return "Location not found";
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = ip.split(",")[0]; // Handle multiple IPs

        // If running locally, fetch public IP
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = restTemplate.getForObject("https://api64.ipify.org", String.class);
            } catch (Exception e) {
                ip = "8.8.8.8"; // Fallback IP (Google DNS)
            }
        }

        return ip;
    }
}
