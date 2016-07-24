package com.guangfeng.police.traffic.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyongjian
 * on 16/7/16.
 */
public class TrafficTicket {
    private int id;
    private String entryId;
    private List<String> violationsCodes;
    private String violationsDate;
    private String carNumber;
    private String carModel;
    private String carMileage;
    private List<String> carPhotos;
    private String carStoreLocation;
    private String dutyPolice;
    private String carManager;
    private List<String> carProcessResults;
    private String carReleaseDate;

    public TrafficTicket() {
        violationsCodes = new ArrayList<>();
        carPhotos = new ArrayList<>();
        carProcessResults = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public List<String> getViolationsCodes() {
        return violationsCodes;
    }

    public void setViolationsCodes(List<String> violationsCodes) {
        this.violationsCodes = violationsCodes;
    }

    public void addViolationsCode(String code) {
        violationsCodes.add(code);
    }

    public String getViolationsDate() {
        return violationsDate;
    }

    public void setViolationsDate(String violationsDate) {
        this.violationsDate = violationsDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public List<String> getCarPhotos() {
        return carPhotos;
    }

    public void setCarPhotos(List<String> car_photos) {
        this.carPhotos = car_photos;
    }

    public void addCarPhoto(String photo) {
        carPhotos.add(photo);
    }

    public String getCarStoreLocation() {
        return carStoreLocation;
    }

    public void setCarStoreLocation(String carStoreLocation) {
        this.carStoreLocation = carStoreLocation;
    }

    public String getDutyPolice() {
        return dutyPolice;
    }

    public void setDutyPolice(String dutyPolice) {
        this.dutyPolice = dutyPolice;
    }

    public List<String> getCarProcessResults() {
        return carProcessResults;
    }

    public void setCarProcessResults(List<String> carProcessResults) {
        this.carProcessResults = carProcessResults;
    }

    public void addCarProcessResults(String result) {
        carProcessResults.add(result);
    }

    public String getCarManager() {
        return carManager;
    }

    public void setCarManager(String carManager) {
        this.carManager = carManager;
    }

    public String getCarReleaseDate() {
        return carReleaseDate;
    }

    public void setCarReleaseDate(String carReleaseDate) {
        this.carReleaseDate = carReleaseDate;
    }

    @Override
    public String toString() {
        return "TrafficTicket{" +
                "entryId='" + entryId + '\'' +
                ", violationsCodes=" + violationsCodes +
                ", violationsDate='" + violationsDate + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carMileage='" + carMileage + '\'' +
                ", carPhotos=" + carPhotos +
                ", carStoreLocation='" + carStoreLocation + '\'' +
                ", dutyPolice='" + dutyPolice + '\'' +
                ", carManager='" + carManager + '\'' +
                ", carProcessResults=" + carProcessResults +
                ", carReleaseDate='" + carReleaseDate + '\'' +
                '}';
    }
}


