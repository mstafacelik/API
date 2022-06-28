package com.API.pojos;

public class BookingDatesPojoInner {
    /*
             "checkin": "2021-10-01",
             "checkout": "2021-09-21"

     */

    // 1- private degiskenler olusturulur

    private String checkin;
    private String checkout;

    //2- getter ve setter methodlar olusturulur

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 3- parametreli ve parametresiz constructor olusturulur

    public BookingDatesPojoInner() {
    }

    public BookingDatesPojoInner(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }


    // 4- toString methodu olusturulur

    @Override
    public String toString() {
        return "BookingDatesPojoInner{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
