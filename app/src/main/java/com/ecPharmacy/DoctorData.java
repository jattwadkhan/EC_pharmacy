package com.ecPharmacy;

import java.io.Serializable;

public class DoctorData implements Serializable {

    int id, image, specialistId;
    String name, location, specialist, fee, description, experience;

    public DoctorData(int id, int image, String name, String location, int specialistId, String specialist,
                      String fee, String description, String experience) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.location = location;
        this.specialistId = specialistId;
        this.specialist = specialist;
        this.fee = fee;
        this.description = description;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
