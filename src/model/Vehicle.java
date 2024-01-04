/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import utils.PrintVehicle;
import utils.Validation;
import utils.Repository;

/**
 *
 * @author LuanTNKSE184059
 */
public class Vehicle implements Serializable {

    private String Id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String type;
    private Date productYear;

    public Vehicle(String Id, String name, String color, double price, String brand, String type, Date productYear) {
        this.Id = Id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productYear = productYear;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getProductYear() {
        return productYear;
    }

    public void setProductYear(Date productYear) {
        this.productYear = productYear;
    }

    @Override
    public String toString() {
        
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        String year = Validation.toString(productYear, Repository.YEAR);

        String formattedString = String.format("|   %-5s  | %-23s|     %-8s  |   %-9s    |         %-15s |   %-10s |         %-10s     |",
                Id, name, color, fmt.format(price), brand, type, year);

        return formattedString;

    }

    public void showProfile() {
        PrintVehicle.printVehicleHeader();
        System.out.println(toString());
        PrintVehicle.printVehicleBar();
    }

    public static void main(String[] args) {

        Date testDate = new Date(); 

        Vehicle test = new Vehicle("V1234", "Mercedes-Benz C-Class", "red", 45235, "Tesla", "Sports Car", testDate);

        test.showProfile();

    }
}
