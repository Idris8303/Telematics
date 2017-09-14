package com.tiy;

public class VehicleInfo {

    //(Vehicle Identification Number)
    String Vin;

    //(miles traveled)
    Double odometer;

    //gallons of gas consumed
    Double Consumption;

    // for last oil change
    Double odometerReading;

    //size in liters
    Double engine;

    public VehicleInfo() {
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public Double getConsumption() {
        return Consumption;
    }

    public void setConsumption(Double consumption) {
        Consumption = consumption;
    }

    public Double getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Double odometerReading) {
        this.odometerReading = odometerReading;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }
}
