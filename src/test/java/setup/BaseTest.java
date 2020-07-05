package setup;

import io.restassured.RestAssured;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {


    public static Properties config = new Properties();
    private FileInputStream fis;

    @BeforeSuite
    public void setUp() {
        try {
            fis = new FileInputStream("./src/test/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            config.load(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RestAssured.baseURI=config.getProperty("baseURI");


    }

    @AfterSuite
    public void tearDown() {


    }

}

