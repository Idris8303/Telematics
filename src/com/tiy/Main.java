package com.tiy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        VehicleInfo vehicleInfo = new VehicleInfo();

        System.out.println("Enter your VIN");
        String vin = scan.nextLine();
        vehicleInfo.setVin(vin);
        System.out.println("Vin: " + vehicleInfo.getVin());

        System.out.println("Miles Travelled?");
        Double odometer = scan.nextDouble();
        vehicleInfo.setOdometer(odometer);
        System.out.println("Odomoter" + vehicleInfo.getOdometer());

        System.out.println(" Gas consumed?");
        Double consumed = scan.nextDouble();
        vehicleInfo.setConsumption(consumed);
        System.out.println("Consumed  " + vehicleInfo.getConsumption() + " gallons of gas." );

        System.out.println("Mileage at last oil change?");
        Double oilMileage = scan.nextDouble();
        vehicleInfo.setOdometerReading(oilMileage);
        System.out.println("Odometer at last oil change " + vehicleInfo.getOdometerReading() + "miles." );

        System.out.println("Engine Size?");
        Double engine = scan.nextDouble();
        vehicleInfo.setEngine(engine);
        System.out.println("The vehicle's engine size is " + vehicleInfo.getEngine());


        TelematicsService telematicsService = new TelematicsService();

        telematicsService.report(vehicleInfo);
    }
}
