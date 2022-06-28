package com.API.day09;

import com.API.pojos.BookingDatesPojoInner;
import com.API.pojos.BookingPojoInner;
import com.API.pojos.BookingResponsePojoOuter;
import com.API.testBase.HerOkuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestMitPojo02 extends HerOkuAppTestBase {
      /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "mustingo",
                   "lastname": "celik",
                   "totalprice": 5000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2021-10-01",
                       "checkout": "2021-09-21"
                    }
                 }
   Status code is 200
    response body  {       "bookingid": 11,
                            "booking": {
                                "firstname": "mustingo",
                                "lastname": "celik",
                                "totalprice": 5000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2021-10-01",
                                    "checkout": "2021-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test() {

        // uri
        spec02.pathParam("parametre1", "booking");

        // request body expected data ayni

        BookingDatesPojoInner bookingDatesPojoInner = new BookingDatesPojoInner("2021-10-01", "2021-09-21");
        System.out.println("bookingDatesPojoInner = " + bookingDatesPojoInner);

        BookingPojoInner requestAndExpectedData = new BookingPojoInner("mustingo", "celik", 5000, true, bookingDatesPojoInner);
        System.out.println("bookingPojoInner = " + requestAndExpectedData);

        // request gonder

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().
                basic("admin", "password123").
                body(requestAndExpectedData).
                when().
                post("/{parametre1}");

        response.prettyPrint();


        // actual data olustur ve dogrula

        BookingResponsePojoOuter actualData = response.as(BookingResponsePojoOuter.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(requestAndExpectedData.getFistname(), actualData.getBooking().getFistname());
        assertEquals(requestAndExpectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(requestAndExpectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(requestAndExpectedData.isDepositpaid(), actualData.getBooking().isDepositpaid());
        assertEquals(requestAndExpectedData.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestAndExpectedData.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());


    }


}
