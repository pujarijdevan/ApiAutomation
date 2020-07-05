package apiconfig;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import setup.BaseTest;
import utilities.Constants;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig extends BaseTest {

    public Map<String, String> headersWithToken(){
        Map<String, String> defalutHeaders = new HashMap<>();
        defalutHeaders.put("Content-Type", "application/json");
        defalutHeaders.put("x-fas-signature",config.getProperty("x-fas-signature"));
        return defalutHeaders;

    }

    public Map<String,String> headerWithInvalidToken(String token){
        Map<String, String> defalutHeaders = new HashMap<>();
        defalutHeaders.put("Content-Type", "application/json");
        defalutHeaders.put("x-fas-signature", token);
        return defalutHeaders;
    }


}
