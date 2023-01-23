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
    String imageProof;


    public PendingListModel(String reservationID, String name, String mobilenum, String event, String reservationDate, String numofPeople, Boolean status, String userID, String timeOfReservation, String dateOfReservation, String venue, String companyName, String gCashNum, String imageProof) {
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
        this.venue = venue;
        this.companyName = companyName;
        this.gCashNum = gCashNum;
        this.imageProof = imageProof;
    }

    public String getImageProof() {
        return imageProof;
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
