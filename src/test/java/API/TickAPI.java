package API;

import apiconfig.HeaderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import setup.BaseTest;

import static io.restassured.RestAssured.given;

public class TickAPI extends BaseTest {


    public static Response sendRequestToSimulateTime()
    {
        HeaderConfig head=new HeaderConfig();
        RequestSpecification request = given().headers(head.headersWithToken());
        Response response=request.post(config.getProperty("tickApi"));
        return response;
    }
}
