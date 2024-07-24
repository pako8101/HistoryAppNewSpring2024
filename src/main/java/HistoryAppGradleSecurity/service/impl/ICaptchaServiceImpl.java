package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class ICaptchaServiceImpl implements ICaptchaService {
    @Value("${secret_key}")
    private String recaptchaSecret;

    private static final String RECAPTCHA_URL =
            "https://www.google.com/recaptcha/api/siteverify";
@Override
    public boolean verifyCaptcha(String response) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", response);

        Map<String, Object> responseBody = restTemplate.postForObject(RECAPTCHA_URL, body, Map.class);
        return (Boolean) responseBody.get("success");
    }
}
