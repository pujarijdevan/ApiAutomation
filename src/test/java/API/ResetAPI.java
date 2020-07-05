package API;

import apiconfig.HeaderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import setup.BaseTest;
import utilities.Constants;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ResetAPI extends BaseTest {

    public static Response resetToInitialState()
    {
        HeaderConfig head=new HeaderConfig();
        RequestSpecification request = given().headers(head.headersWithToken());
        Response response=request.put(config.getProperty("resetApi"));
        int statusCode = response.getStatusCode();
        assertEquals(statusCode , Constants.STATUSCODE_200, "In Correct status code returned");
        return response;
    }

}
