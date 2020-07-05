package testcases;

import org.testng.annotations.DataProvider;
import utilities.Constants;

import java.lang.reflect.Method;

public class TestData {

    @DataProvider(name="data")
    public Object[][] dpMethod(Method m) {
        switch (m.getName()) {
            case "verifyUserIsAllocatedCar":
                return new Object[][] {{2,3 ,5,4},{-1,0,1,2}};
            case "verifyBookApiReturnsEmptyResponseWhenNoCarIsAvailable":
                return new Object[][] {{2, 4,6,8}};
            case "verifyAllocatedCarIsAvailableForBookingAfterReachingDestination":
            case "verifyBookAPIWithInvalidToken":
                return new Object[][] {{1,2,1,1}};
            case "verifyBookApiWithInvalidRequestBody":
                return new Object[][] {{"1.1","2.5" ,"1","1"},{"-232424214234314","4234","23","24"}};
            case "verifyAllCarsAreAvailableForBookingOnceDestinationIsReached":
                return new Object[][] {{1, 1,1,1}};

        }
        return null;

    }
}
