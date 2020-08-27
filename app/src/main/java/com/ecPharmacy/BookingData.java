package com.ecPharmacy;

public class BookingData {

    String date, experience, fee, location, name, specialist, time;
    int image, specialistId;

    public BookingData(int image, int specialistId, String name, String specialist, String location, String experience, String fee, String date, String time) {
        this.image = image;
        this.specialistId = specialistId;
        this.name = name;
        this.specialist = specialist;
        this.location = location;
        this.experience = experience;
        this.fee = fee;
        this.date = date;
        this.time = time;
    }
}
