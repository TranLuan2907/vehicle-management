/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Vehicle;
import utils.MenuUtils;
import utils.PrintVehicle;
import utils.Validation;
import utils.Repository;

/**
 *
 * @author LuanTNKSE184059
 */
public class VehicleManagement extends ArrayList<Vehicle> {

    public static final Scanner in = new Scanner(System.in);

    public void addAVehicle() {
        Vehicle v;
        String id;
        boolean isContinue;
        do {
            System.out.println("ADD VEHICLE #" + (this.size() + 1));
            do {
                id = Validation.readStr(Repository.VEHILCE_ID_QUESTION, Repository.ERROR_CODE, Repository.CODE);
                v = findVehicleByID(id);
                if (v != null) {
                    System.err.println("DUPLICATED ID! PLEASE TRY AGAIN");
                }
            } while (v != null);
            
           
            String name = Validation.getString(Repository.VEHILCE_NAME_QUESTION, Repository.ERROR);
            String color = Validation.getString(Repository.VEHILCE_COLOR_QUESTION, Repository.ERROR);
            double price = Validation.getADouble(Repository.VEHILCE_PRICE_QUESTION, Repository.ERROR_PRICE, 10000, Double.POSITIVE_INFINITY);
            String brand = Validation.getString(Repository.VEHILCE_BRAND_QUESTION, Repository.ERROR);
            String type = Validation.getString(Repository.VEHILCE_TYPE_QUESTION, Repository.ERROR);

            Calendar cal = Calendar.getInstance();
            cal.set(1990, 0, 1);
            Date min = cal.getTime();
            Date max = new Date();

            Date year = Validation.readDateBetween(Repository.VEHILCE_YEAR_QUESTION, Repository.ERROR_YEAR, Repository.YEAR, min, max);
            v = new Vehicle(id, name, color, price, brand, type, year);
            this.add(v);
            MenuUtils.printPaddingMessage("add vehicle #" + this.size() + " successfully!", 128);
            v.showProfile();
            isContinue = Validation.parseBoolean(Repository.YES_NO_QUESTION, Repository.YES_NO_ANSWER);
        } while (isContinue);
    }

    public Vehicle findVehicleByID(String id) {
        return this.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void checkExistedVehicle() {
        String id = Validation.readStr(Repository.VEHILCE_ID_QUESTION, Repository.ERROR_CODE, Repository.CODE);
        Vehicle vehicle = findVehicleByID(id);

        if (vehicle != null) {
            MenuUtils.printPaddingMessage("existed vehicle! here is your vehicle according to vehicle code " + vehicle.getId(), 118);
            vehicle.showProfile();
        } else {
            System.err.println("NO VEHICLE FOUND!");
        }
    }

    public void updateVehicle() {
        if (this.isEmpty()) {
            System.err.println("NOTHING TO UPDATE!");
            return;
        }

        String id = Validation.readStr(Repository.VEHILCE_ID_QUESTION.toUpperCase(), Repository.ERROR_CODE, Repository.CODE);
        Vehicle v = findVehicleByID(id);
        if (v == null) {
            System.err.println("VEHICLE DOES NOT EXIST");
            return;
        }

        String temp;
        Double n;
        boolean isValid = false;

        String name = Validation.readUpdatedString(Repository.VEHILCE_NAME_QUESTION);

        if (!name.isEmpty()) {
            v.setName(name);
        }

        String color = Validation.readUpdatedString(Repository.VEHILCE_COLOR_QUESTION);

        if (!color.isEmpty()) {
            v.setColor(color);
        }

        do {
            System.out.print(Repository.VEHILCE_PRICE_QUESTION.toUpperCase() + ": ");
            temp = in.nextLine().trim().toUpperCase();
            if (!temp.isEmpty()) {
                try {
                    n = Double.parseDouble(temp);
                    if (n < 10000) {
                        System.err.println(Repository.ERROR_PRICE);
                    } else {
                        isValid = true;
                        v.setPrice(n);
                    }
                } catch (NumberFormatException e) {
                    System.err.println(Repository.ERROR_PRICE);
                    isValid = false;
                }
            } else {
                isValid = true;
            }
        } while (!isValid);

        String brand = Validation.readUpdatedString(Repository.VEHILCE_BRAND_QUESTION);
        if (!brand.isEmpty()) {
            v.setBrand(brand);
        }

        String type = Validation.readUpdatedString(Repository.VEHILCE_TYPE_QUESTION);

        if (!type.isEmpty()) {
            v.setType(type);
        }

        boolean isValidYear = false;
        while (!isValidYear) {
            System.out.print(Repository.VEHILCE_YEAR_QUESTION.toUpperCase() + ": ");
            temp = in.nextLine().trim().toUpperCase();

            if (!temp.isEmpty()) {
                try {
                    int inputYear = Integer.parseInt(temp);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                    if (inputYear > 1990 && inputYear <= currentYear) {
                        v.setProductYear(Validation.parseDate(temp, Repository.YEAR));
                        isValidYear = true;
                    } else {
                        System.err.println(Repository.ERROR_YEAR.toUpperCase());
                    }
                } catch (NumberFormatException e) {
                    System.err.println(Repository.ERROR_YEAR.toUpperCase());
                }
            } else {
                isValidYear = true;
            }
        }

        MenuUtils.printPaddingMessage("updating successfully! here is the vehicle with code " + v.getId() + " after updating", 118);
        v.showProfile();
    }

    public void saveVehicleData() {
        try {
            try (FileOutputStream fos = new FileOutputStream(Repository.FILE_NAME);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this);
            }
            System.out.println("VEHICLES DATA SAVED SUCCESSFULLY!");
        } catch (IOException e) {
            System.out.println("ERROR WHILE SAVING VEHICLE DATA: " + e.getMessage());
        }
    }

    public void loadVehicleData() {
        try {
            try (FileInputStream fis = new FileInputStream(Repository.FILE_NAME);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                VehicleManagement vm = (VehicleManagement) ois.readObject();
                this.addAll(vm);
            }
            System.out.println("VEHICLE DATA LOADED SUCCESSFULLY!");
        } catch (Exception e) {
            System.out.println("ERROR WHILE LOADING VEHICLE DATA: " + e.getMessage());
        }
    }

    public void deleteVehicle() {
        if (this.isEmpty()) {
            System.err.println("NOTHING TO REMOVE!");
            return;
        }
        Vehicle v;
        String id = Validation.readStr(Repository.VEHILCE_ID_QUESTION.toUpperCase(), Repository.ERROR_CODE, Repository.CODE);
        v = findVehicleByID(id);

        if (v == null) {
            System.err.println("NOT FOUND VEHICLE THAT YOU WANT TO REMOVE!");
            return;
        }

        MenuUtils.printPaddingMessage("here is the vehicle before removing!", 128);
        v.showProfile();
        boolean confirm = Validation.parseBoolean("are you sure that you want to delete this vehilce (Y OR N)", Repository.YES_NO_ANSWER);
        if (confirm) {
            this.remove(v);
            System.out.println("VEHILCE REMOVED SUCCESSFULLY!");
        } else {
            System.err.println("CANCELLED REMOVED!");
        }
    }

    public void searchByName() {
        String name = Validation.getString(Repository.VEHILCE_NAME_QUESTION, Repository.ERROR);
        List<Vehicle> vehicleName = this.stream()
                .filter(v -> v.getName().contains(name))
                .sorted(Comparator.comparing(Vehicle::getName).reversed())
                .collect(Collectors.toList());

        if (vehicleName.isEmpty()) {
            System.err.println("VEHICLE(S) NOT FOUND!");
        } else {
            MenuUtils.printPaddingMessage("here is all vehicles contais the name " + name + "!", 128);
            PrintVehicle.printVehicleHeader();
            vehicleName.forEach(v -> {
                System.out.println(v);
                PrintVehicle.printVehicleBar();
            });
        }
    }

    public void searchById() {
        String id = Validation.readStr(Repository.VEHILCE_ID_QUESTION, Repository.ERROR_CODE, Repository.CODE);
        Vehicle v = findVehicleByID(id);

        if (v == null) {
            System.err.println("NOT FOUND VEHICLES THAT MATCH THE GIVEN ID!");
            return;
        }

        MenuUtils.printPaddingMessage("here is the vehicle with the id " + id + "!", 128);
        v.showProfile();
    }

    public void printVehicle() {
        if (this.isEmpty()) {
            System.err.println("NOTHING TO PRINT OUT!");
            return;
        }
        MenuUtils.printPaddingMessage("here is all the vehilces in the show room!", 128);
        PrintVehicle.printVehicleHeader();
        this.stream()
                .map(Vehicle::toString)
                .forEach(vehilce -> {
                    System.out.println(vehilce);
                    PrintVehicle.printVehicleBar();
                });
    }

    public void printVehilcePrice() {
        double price = Validation.getADouble(Repository.VEHILCE_PRICE_QUESTION, Repository.ERROR_PRICE, 0, Double.POSITIVE_INFINITY);

        List<Vehicle> vehiclePrice = this.stream()
                .filter(v -> v.getPrice() < price)
                .sorted(Comparator.comparingDouble(Vehicle::getPrice).reversed())
                .collect(Collectors.toList());

        if (vehiclePrice.isEmpty()) {
            System.err.println("VEHICLE(S) NOT FOUND!");
        } else {
            MenuUtils.printPaddingMessage("here is all vehicles with descending price!", 128);
            PrintVehicle.printVehicleHeader();
            vehiclePrice.forEach(v -> {
                System.out.println(v);
                PrintVehicle.printVehicleBar();
            });
        }

    }

    public void printVehilceYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(1990, 0, 1);
        Date min = cal.getTime();
        Date max = new Date();

        Date year = Validation.readDateBetween(Repository.VEHILCE_YEAR_QUESTION, Repository.ERROR_YEAR, Repository.YEAR, min, max);
        List<Vehicle> printYear = this.stream()
                .filter(v -> !v.getProductYear().before(year))
                .sorted(Comparator.comparing(Vehicle::getProductYear).reversed())
                .collect(Collectors.toList());

        if (printYear.isEmpty()) {
            System.err.println("VEHICLE(S) NOT FOUND!");
        } else {
            MenuUtils.printPaddingMessage("here is all vehicles with descending product years!", 128);
            PrintVehicle.printVehicleHeader();
            printYear.forEach(v -> {
                System.out.println(v);
                PrintVehicle.printVehicleBar();
            });
        }
    }
}
