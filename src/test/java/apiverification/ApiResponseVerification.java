package apiverification;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.Constants;

public class ApiResponseVerification {



    public static void validateResponseHeader_200(Response response)
    {
            System.out.println("Validating Response headers");
            String contentType = response.header("Content-Type");
            Assert.assertEquals(contentType, "application/json");
            String contentTypeOption=response.header("X-Content-Type-Options");
            Assert.assertEquals(contentTypeOption, "nosniff");
            int status=response.statusCode();
            Assert.assertEquals(status, Constants.STATUSCODE_200);

    }

}
