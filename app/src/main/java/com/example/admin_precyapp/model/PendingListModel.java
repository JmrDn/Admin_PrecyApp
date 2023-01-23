package com.example.admin_precyapp.model;

public class PendingListModel {

    String reservationID;
    String name;
    String mobilenum;
    String event;
    String reservationDate;
    String numofPeople;
    Boolean status;
    String userID;
    String timeOfReservation;
    String dateOfReservation;
    String venue;
    String companyName;
    String gCashNum;



    public PendingListModel(String reservationID, String name, String mobilenum, String event, String reservationDate,
                            String numofPeople, Boolean status, String userID, String timeOfReservation, String dateOfReservation,
                            String companyName, String venue, String gCashNum) {
        this.reservationID = reservationID;
        this.name = name;
        this.mobilenum = mobilenum;
        this.event = event;
        this.reservationDate = reservationDate;
        this.numofPeople = numofPeople;
        this.status = status;
        this.userID = userID;
        this.timeOfReservation = timeOfReservation;
        this.dateOfReservation = dateOfReservation;
        this.companyName = companyName;
        this.venue = venue;
        this.gCashNum = gCashNum;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public String getTimeOfReservation() {
        return timeOfReservation;
    }

    public String getDateOfReservation() {
        return dateOfReservation;
    }

    public String getVenue() {
        return venue;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getgCashNum() {
        return gCashNum;
    }
}
