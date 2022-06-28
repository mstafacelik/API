package com.API.pojos;

public class BookingPojoInner {
    /*
        {
            {   "firstname": "mustingo",
            "lastname": "celik",
            "totalprice": 5000,
            "depositpaid": true,
            "bookingdates": "checkin": "2021-10-01",
                              "checkout": "2021-09-21"
               }
          }

     */

    // 1- private degiskenler

    private String fistname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojoInner bookingdates;  //Daha önce oluşturulan  pojo kalıbını datatype olarak belirterek
                                                 // yeni pojonun içerine gömmüş oldum.



    // public getter ve setter

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojoInner getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesPojoInner bookingdates) {
        this.bookingdates = bookingdates;
    }

    // / 3- parametreli ve parametresiz constructor

    public BookingPojoInner() {
    }

    public BookingPojoInner(String fistname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojoInner bookingdates) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }


    // toString

    @Override
    public String toString() {
        return "BookingPojoInner{" +
                "fistname='" + fistname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
