package testcases;

import API.BookAPI;
import API.ResetAPI;
import API.TickAPI;
import apiconfig.HeaderConfig;
import apiverification.ApiResponseVerification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import listeners.ExtentListeners;
import models.request.response.BookResponse;
import org.testng.Assert;
import org.testng.annotations.*;
import setup.BaseTest;

import static org.testng.Assert.assertEquals;

import utilities.*;

public class E2ETestCases extends BaseTest {


    private static Response response;
    private static ResponseBody body;
    private static BookResponse bookResponseBody;
    private int totalCar = 3;
    private int carID;

    @BeforeMethod
    @AfterMethod
    public void resetCarData() {
        System.out.println("Reset Car Data");
        ResetAPI.resetToInitialState();
        carID = 1;
    }


    /*
       Pre-requisite : Car data is in initial state configuration
      1) Trigger the /v1/book API with valid header and request body
      2) validate the response status code to be 200
      3) Validate the response data
            Car id returned should be smallest when more than 1 car in same customer location
            Total time for car to reach from origin point to destination.

     */

    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyUserIsAllocatedCar(int sourcex, int sourcey, int destinationx, int destinationy) {
        response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
        System.out.println("Response body of the API"+response.asString());
        body = response.getBody();
        bookResponseBody = body.as(BookResponse.class);
        if (response.statusCode() == 200) {
            Assert.assertEquals("FETCHING_CUSTOMER", bookResponseBody.getState(), "In correct state returned");
            assertEquals(carID++, bookResponseBody.getCar_id(), "In correct car id allocated");
            assertEquals(totalTimeTaken(sourcex, sourcey, destinationx, destinationy), bookResponseBody.getTotal_time(), "In correct total time returned");
        }
        ApiResponseVerification.validateResponseHeader_200(response);

    }

    private int totalTimeTaken(int x, int y, int k, int l) {
        int timeFromCarToSource = Math.abs(x) + Math.abs(y);
        int timeFromSourceToDestination = Math.abs(k - x) + Math.abs(l - y);
        System.out.println("Total Time from origin " + (timeFromCarToSource + timeFromSourceToDestination));
        return timeFromCarToSource + timeFromSourceToDestination;
    }

    /*
        Verify Empty response is returned from /v1/book API
        When no more car is available for booking
        And response status should be 200.

     */

    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyBookApiReturnsEmptyResponseWhenNoCarIsAvailable(int sourcex, int sourcey, int destinationx, int destinationy) {
        for (int i = 1; i <= totalCar; i++) {
            response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
            System.out.println("Response body of the API"+response.asString());
            body = response.getBody();
            bookResponseBody = body.as(BookResponse.class);
            assertEquals(response.getStatusCode(), 200, "In Correct status code returned");
            assertEquals(carID++, bookResponseBody.getCar_id(), "CarID is same");

        }
        response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
        System.out.println("Response body of the API"+response.asString());
        String jsonString = response.asString();
        System.out.println("Response data" + jsonString);
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, Constants.STATUSCODE_200, "In Correct status code returned");
        String responseBody = response.asString();
        assertEquals(responseBody, "null");

    }



    /*
        1.Book a car and validate the car id allocated and time taken to allocate
        2. Trigger /tick "time_taken" times
        3. Book a car again , and validate the same car id is available for booking.
        4.

     */

    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyAllocatedCarIsAvailableForBookingAfterReachingDestination(int sourcex, int sourcey, int destinationx, int destinationy) {
        response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
        System.out.println("Response body of the API"+response.asString());
        body = response.getBody();
        BookResponse responseBody = body.as(BookResponse.class);
        if (response.statusCode() == 200) {
            Assert.assertEquals("FETCHING_CUSTOMER", responseBody.getState(), "In correct state returned");
            assertEquals(carID, responseBody.getCar_id(), "In correct car id allocated");
            assertEquals(totalTimeTaken(sourcex, sourcey, destinationx, destinationy), responseBody.getTotal_time(), "In correct total time returned");
        }
        for (int i = 1; i <= responseBody.getTotal_time(); i++) {
            response = TickAPI.sendRequestToSimulateTime();
            Assert.assertEquals(Constants.STATUSCODE_200, response.getStatusCode(), "In correct status code returned");
        }
        response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
        body = response.getBody();
        responseBody = body.as(BookResponse.class);
        assertEquals(200, response.statusCode(), "In correct state returned");
        assertEquals(carID, responseBody.getCar_id(), "In correct car id allocated");
        int expectedNewTotalTime = 2 * (Math.abs(destinationx - sourcex) + Math.abs(destinationy - sourcey));
        assertEquals(expectedNewTotalTime, responseBody.getTotal_time(), "In correct total time");
    }

    /*
        Verify all the cars are available for booking after reaching destination
     */
    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyAllCarsAreAvailableForBookingOnceDestinationIsReached(int sourcex, int sourcey, int destinationx, int destinationy) {
        int totalTimeTaken = 0;
        for (int i = 1; i <= totalCar; i++) {
            response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
            body = response.getBody();
            bookResponseBody = body.as(BookResponse.class);
            assertEquals(carID++, bookResponseBody.getCar_id(), "CarID is same");
            totalTimeTaken = totalTimeTaken(sourcex, sourcey, destinationx, destinationy);
            assertEquals(totalTimeTaken, bookResponseBody.getTotal_time(), "In correct total time returned");
        }
        carID = 1;
        System.out.println("Total Time Taken" + totalTimeTaken);
        for (int i = 1; i <= totalTimeTaken; i++) {
            response = TickAPI.sendRequestToSimulateTime();
            Assert.assertEquals(Constants.STATUSCODE_200, response.getStatusCode(), "In correct status code returned");
        }
        for (int i = 1; i <= totalCar; i++) {
            response = BookAPI.bookACar(sourcex, sourcey, destinationx, destinationy);
            body = response.getBody();
            bookResponseBody = body.as(BookResponse.class);
            assertEquals(carID++, bookResponseBody.getCar_id(), "CarID is same");
        }

    }


    /*
        Verify book API with invalidToken in the request header and verify 401 status is returned
     */
    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyBookAPIWithInvalidToken(int sourcex, int sourcey, int destinationx, int destinationy) {
        response = BookAPI.bookApiWithInvalidToken(sourcex, sourcey, destinationx, destinationy, Constants.INVALIDTOKEN);
        assertEquals(Constants.STATUSCODE_401, response.getStatusCode());

    }

    /*
    Verify bookAPI with invalid request body and validate 500 status is returned.

     */

    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void verifyBookApiWithInvalidRequestBody(String sourcex, String sourcey, String destinationx, String destinationy) {
        String Invalid_BOOKAPI_REQUEST = "{\n" +
                "\"source\": {\n" +
                "\"x\":" + sourcex + ",\n" +
                "\"y\":" + sourcey + "\n" +
                "},\n" +
                "\"destination\": {\n" +
                "\"x\":" + destinationx + ",\n" +
                "\"y\":" + destinationy + "\n" +
                "}\n" +
                "}";
        RequestSpecification request = RestAssured.given();
        HeaderConfig head = new HeaderConfig();
        response = request.headers(head.headersWithToken()).body(Invalid_BOOKAPI_REQUEST).post(config.getProperty("bookCarApi"));
        System.out.println("Status code returned" + response.statusCode());
        Assert.assertEquals(Constants.STATUSCODE_500, response.getStatusCode(), "Incorrect status code returned");

    }

    @Test()
    public void verifyTickApiWithInvalidHeader() {

        RequestSpecification request = RestAssured.given();
        HeaderConfig head = new HeaderConfig();
        response = request.headers(head.headerWithInvalidToken(Constants.INVALIDTOKEN)).post(config.getProperty("tickApi"));
        assertEquals(Constants.STATUSCODE_401, response.getStatusCode());


    }


    @Test()
    public void verifyResetApiWithInvalidHeader() {

        RequestSpecification request = RestAssured.given();
        HeaderConfig head = new HeaderConfig();
        response = request.headers(head.headerWithInvalidToken(Constants.INVALIDTOKEN)).put(config.getProperty("resetApi"));
        assertEquals(Constants.STATUSCODE_401, response.getStatusCode());


    }


}

