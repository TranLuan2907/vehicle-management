/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author LuanTNKSE184059
 */
public class PrintVehicle {

    public static void printVehicleCell(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    public static void printVehicleBoxed(String text, int width) {
        printABar(119);
        System.out.println();
        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.println();
    }

    public static void printVehicleHeader() {
        printVehicleBar();
        printVehicleCell("ID", 10);
        printVehicleCell("NAME", 25);
        printVehicleCell("COLOR", 15);
        printVehicleCell("PRICE", 18);
        printVehicleCell("BRAND", 25);
        printVehicleCell("TYPE", 15);
        printVehicleCell("PRODUCT YEAR", 20);
        printSpaceAndDash();
        printVehicleBar();
    }

    public static void printVehicleBar() {
        System.out.println("+----------+------------------------+---------------+-----------------+-------------------------+--------------+------------------------+");
    }

    public static void printABar(int length) {
        String dashes = new String(new char[length]).replace("\0", "-");
        System.out.print(dashes);
    }

    public static void printSpaceAndDash() {
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
