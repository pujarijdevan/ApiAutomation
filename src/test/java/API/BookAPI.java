package API;

import apiconfig.HeaderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.BookRequest;
import models.request.Destination;
import models.request.Source;
import setup.BaseTest;

import static io.restassured.RestAssured.given;

public class BookAPI extends BaseTest {

    public static Response bookACar(int sourceLat,int sourceLang,int destinationLat,int destinationLang)
    {
        Source src=new Source(sourceLat,sourceLang);
        Destination des=new Destination(destinationLat,destinationLang);
        BookRequest book=new BookRequest(src,des);
        HeaderConfig head = new HeaderConfig();
        RequestSpecification request = given().headers(head.headersWithToken());
        Response response=request.body(book).post(config.getProperty("bookCarApi"));
        return response;
    }

    public static Response bookApiWithInvalidToken(int sourceLat,int sourceLang,int destinationLat,int destinationLang,String token)
    {
        Source src=new Source(sourceLat,sourceLang);
        Destination des=new Destination(destinationLat,destinationLang);
        BookRequest book=new BookRequest(src,des);
        HeaderConfig head = new HeaderConfig();
        RequestSpecification request = given().headers(head.headerWithInvalidToken(token));
        Response response=request.body(book).post(config.getProperty("bookCarApi"));
        return response;
    }

}
