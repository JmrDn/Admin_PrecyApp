package com.example.admin_precyapp.model;

public class PendingListModel {

    String reservationID;
    String name;
    String mobilenum;
    String event;
    String reservationDate;
    String numofPeople;


    public PendingListModel(String reservationID, String name, String mobilenum, String event, String reservationDate, String numofPeople) {
        this.reservationID = reservationID;
        this.name = name;
        this.mobilenum = mobilenum;
        this.event = event;
        this.reservationDate = reservationDate;
        this.numofPeople = numofPeople;
    }

    public String getReservationID() {
        return reservationID;
    }

    public String getName() {
        return name;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public String getEvent() {
        return event;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getNumofPeople() {
        return numofPeople;
    }


}
