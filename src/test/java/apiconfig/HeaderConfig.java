package apiconfig;

import setup.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig extends BaseTest {

    public Map<String, String> headersWithToken(){
        Map<String, String> defaulttHeaders = new HashMap<>();
        defaulttHeaders.put("Content-Type", "application/json");
        defaulttHeaders.put("x-fas-signature",config.getProperty("x-fas-signature"));
        return defaulttHeaders;

    }

    public Map<String,String> headerWithInvalidToken(String token){
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json");
        defaultHeaders.put("x-fas-signature", token);
        return defaultHeaders;
    }


}
