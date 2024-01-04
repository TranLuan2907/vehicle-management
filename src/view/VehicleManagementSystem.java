/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.VehicleManagement;
import utils.Validation;

/**
 *
 * @author 
 */
public class VehicleManagementSystem {

    public static void main(String[] args) {
        Menu mainMenu = new Menu("welcome to vehicle management!");
        Menu searchMenu = new Menu("search vehicle");
        Menu displayMenu = new Menu("display vehicle ");
        Menu printMenu = new Menu("print vehicle ");

        printMainMenu(mainMenu);
        printSearchMenu(searchMenu);
        printDisplayMenu(displayMenu);
        printPrintMenu(printMenu);

        VehicleManagement vm = new VehicleManagement();
        vm.loadVehicleData();

        boolean change = false;
        boolean confirm;
        int choice;
        do {
            choice = mainMenu.getUserChoice();
            switch (choice) {
                case 1:
                    vm.addAVehicle();
                    change = true;
                    break;
                case 2:
                    vm.checkExistedVehicle();
                    change = true;
                    break;
                case 3:
                    vm.updateVehicle();
                    change = true;
                    break;
                case 4:
                    vm.deleteVehicle();
                    change = true;
                    break;
                case 5:
                    searchVehicle(searchMenu, vm);
                    change = true;
                    break;
                case 6:
                    displayVehicle(displayMenu, vm);
                    change = true;
                    break;
                case 7:
                    vm.saveVehicleData();
                    change = true;
                    break;
                case 8:
                    printVehilce(printMenu, vm);
                    change = true;
                    break;
                case 9:
                    if (change) {
                        System.out.println("CHANGES HAVE BEEN MADE TO THE DATA. DO YOU WANT TO SAVE AND EXIT?");
                        confirm = Validation.parseBoolean("TYPE 'Y' TO SAVE AND EXIT, OR 'N' TO EXIT WITHOUT SAVING: ", "YOUR CHOICE MUST BE 'Y' OR 'N' AND CANNOT BE LEAVE EMPTY!");
                        if (confirm) {
                            vm.saveVehicleData();
                            System.out.println("YOUR CHANGES HAVE BEEN SAVED! SEE YOU NEXT TIME!");
                            break;
                        } else {
                            System.out.println("SEE YOU NEXT TIME!");
                        }
                    }
                    change = true;
                    break;

            }
        } while (choice != 9);

    }

    public static void printMainMenu(Menu mainMenu) {
        mainMenu.addMenuItem("add new vehicle");
        mainMenu.addMenuItem("check exists vehicle");
        mainMenu.addMenuItem("update vehicle");
        mainMenu.addMenuItem("delete vehicle");
        mainMenu.addMenuItem("search vehicle");
        mainMenu.addMenuItem("display all vehicles");
        mainMenu.addMenuItem("save all vehicles to file");
        mainMenu.addMenuItem("print all vehicles from the file");
        mainMenu.addMenuItem("quit");
    }

    public static void printSearchMenu(Menu searchMenu) {
        searchMenu.addMenuItem("search by name");
        searchMenu.addMenuItem("search by id");
        searchMenu.addMenuItem("back to the main menu");
    }

    public static void printDisplayMenu(Menu displayMenu) {
        displayMenu.addMenuItem("show all");
        displayMenu.addMenuItem("show by price");
        displayMenu.addMenuItem("back to the main menu");
    }

    public static void printPrintMenu(Menu printMenu) {
        printMenu.addMenuItem("print all");
        printMenu.addMenuItem("print by year");
        printMenu.addMenuItem("back to the main menu");
    }

    public static void searchVehicle(Menu searchMenu, VehicleManagement vm) {
        int userChoice;
        do {
            userChoice = searchMenu.getUserChoice();
            switch (userChoice) {
                case 1:
                    vm.searchByName();
                    break;
                case 2:
                    vm.searchById();
                    break;
                case 3:
                    break;
            }

        } while (userChoice != 3);
    }

    public static void displayVehicle(Menu displayMenu, VehicleManagement vm) {
        int userChoice;
        do {
            userChoice = displayMenu.getUserChoice();
            switch (userChoice) {
                case 1:
                    vm.printVehicle();
                    break;
                case 2:
                    vm.printVehilcePrice();
                    break;
                case 3:
                    break;
            }

        } while (userChoice != 3);
    }

    public static void printVehilce(Menu printMenu, VehicleManagement vm) {
        int userChoice;
        do {
            userChoice = printMenu.getUserChoice();
            switch (userChoice) {
                case 1:
                    vm.printVehicle();
                    break;
                case 2:
                    vm.printVehilceYear();
                    break;
                case 3:
                    break;
            }

        } while (userChoice != 3);
    }
}

