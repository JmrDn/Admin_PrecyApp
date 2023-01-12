package com.example.admin_precyapp.model;

public class UserPendingListModel {

    String userReservationID;
    String user_Name;
    String user_MobileNum;
    String user_Event;
    String user_ReservationDate;
    String user_NumofPeople;
    Boolean user_Status;
    String companyName;
    String venue;


    public UserPendingListModel(String userReservationID, String user_Name, String user_MobileNum,
                                String user_Event, String user_ReservationDate, String user_NumofPeople,
                                Boolean user_Status, String companyName, String venue) {
        this.userReservationID = userReservationID;
        this.user_Name = user_Name;
        this.user_MobileNum = user_MobileNum;
        this.user_Event = user_Event;
        this.user_ReservationDate = user_ReservationDate;
        this.user_NumofPeople = user_NumofPeople;
        this.user_Status = user_Status;
        this.companyName = companyName;
        this.venue = venue;
    }



    public String getuserReservationID() {
        return userReservationID;
    }

    public String getuser_Name() {
        return user_Name;
    }

    public String getuser_MobileNum() {
        return user_MobileNum;
    }

    public String getuser_Event() {
        return user_Event;
    }

    public String getuser_ReservationDate() {
        return user_ReservationDate;
    }

    public String getuser_NumofPeople() {
        return user_NumofPeople;
    }

    public Boolean getuser_Status() {
        return user_Status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getVenue() {
        return venue;
    }
}
