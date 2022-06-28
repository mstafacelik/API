package com.API.pojos;

public class BookingResponsePojoOuter {

    /*

    {       "bookingid": 11,
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

     */


    private int bookingid;
    private BookingPojoInner booking;






    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojoInner getBooking() {
        return booking;
    }

    public void setBooking(BookingPojoInner booking) {
        this.booking = booking;
    }






    public BookingResponsePojoOuter() {
    }

    public BookingResponsePojoOuter(int bookingid, BookingPojoInner booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }






    @Override
    public String toString() {
        return "BookingResponsePojoOuter{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
