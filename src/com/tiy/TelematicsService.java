package com.tiy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelematicsService {

    void report(VehicleInfo vehicleInfo) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(vehicleInfo);
            System.out.println(json);
            File file = new File((vehicleInfo.getVin()) + ".json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File file = new File(".");
        List<VehicleInfo> vehicleList = new ArrayList<>();
        for (File f: file.listFiles()) {
            if(f.getName().endsWith(".json")) {
                ObjectMapper newMapper = new ObjectMapper();
                try {
                    Scanner sc = new Scanner(f);
                    String i = "";
                    while(sc.hasNextLine()) {
                        i += sc.nextLine();
                    }
                    VehicleInfo vi = newMapper.readValue(i, VehicleInfo.class);
                    vehicleList.add(vi);
                    sc.close();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Averages

        System.out.println("*****************************************");

        List<Double> vehicleAverages = new ArrayList<>();
        //Odometer
        Double sumOdometer = 0.0;
        Double avgOdometer;
        for (VehicleInfo vehicle: vehicleList) {
            sumOdometer += vehicle.getOdometer();
        }
        avgOdometer = sumOdometer / vehicleList.size();
        vehicleAverages.add(avgOdometer);

        //Gas consumed
        Double sumGallonsOfGas = 0.0;
        Double avgGallonsOfGas;
        for (VehicleInfo vehicle: vehicleList) {
            sumGallonsOfGas += vehicle.getConsumption();
        }
        avgGallonsOfGas = sumGallonsOfGas / vehicleList.size();
        vehicleAverages.add(avgGallonsOfGas);

        //Odometer at last oil change
        Double sumOilOdometer = 0.0;
        Double avgOilOdometer;
        for (VehicleInfo vehicle: vehicleList) {
            sumOilOdometer += vehicle.getOdometerReading();
        }
        avgOilOdometer = sumOilOdometer / vehicleList.size();
        vehicleAverages.add(avgOilOdometer);

        //Engine size
        Double sumEngineSize = 0.0;
        Double avgEngineSize;
        for (VehicleInfo vehicle: vehicleList) {
            sumEngineSize += vehicle.getEngine();
        }
        avgEngineSize = sumEngineSize / vehicleList.size();
        vehicleAverages.add(avgEngineSize);

        System.out.println("Averages: " + vehicleAverages);
        System.out.println("****************************************");

        String output = "";

        output += HTMLTemplate.header;


        //Fills in average vehicle table rows
        String averageRow = HTMLTemplate.avgRow;

        averageRow = averageRow.replace("avgOdometer", vehicleAverages.get(0).toString());
        averageRow = averageRow.replace("avgGas", vehicleAverages.get(1).toString());
        averageRow = averageRow.replace("avgOil", vehicleAverages.get(2).toString());
        averageRow = averageRow.replace("avgEngine", vehicleAverages.get(3).toString());
        output += averageRow;

        //Fills in current vehicle table rows


        for (VehicleInfo vehicle: vehicleList) {
            String currentRow = HTMLTemplate.currentRow;
            currentRow = currentRow.replace("vin", vehicle.getVin());
            currentRow = currentRow.replace("odometer", vehicle.getOdometer().toString());
            currentRow = currentRow.replace("gas", vehicle.getOdometer().toString());
            currentRow = currentRow.replace("oil", vehicle.getOdometerReading().toString());
            currentRow = currentRow.replace("engine", vehicle.getEngine().toString());
            output += currentRow;
        }
        output += HTMLTemplate.footer;

        try {
            File outPutFile = new File("telemetry.html");
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(outPutFile);
            fileWriter.write(output);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(output);
    }
}
