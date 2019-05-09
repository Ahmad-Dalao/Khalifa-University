/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglotsystem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ahmad Dalao
 */
public class ParkingLotSystem {

    
    static Scanner console = new Scanner(System.in); // global scanner to be used in many methods

    /**
     * the main method keeps the program running and exchange between consoles as per requested until exit
     * @param input is where the user choose which console he wants 
     * @param runTime this parameter will only change to false when the user wants to exit the system
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        int input;
        boolean runTime = true;

        do {
            input = initialization();
            if (input == 0) {
                runTime = false;
            } else if (input == 1) {
                admin();
            } else if (input == 2) {
                operator();
            } else {
                customer();
            }

        } while (runTime);

        System.out.println("Thank you for using Dalao Smart Parking System ");
    }

    /**
     * this method is where the user is asked to choose the console he wants
     * @return the choice number of the user
     */
    public static int initialization() {
        int x;
        boolean flag;
        System.out.println("Welcome to Dalao Smart Parking System");
        do {
            System.out.println("Please choose your portal corresponding number, either 1, 2, 3 or 0\n1. Admin \n2. Operator \n3. Customer\n0. EXIT");
            x = console.nextInt();
            if (x == 0 || x == 1 || x == 2 || x == 3) {
                flag = false;
            } else {
                flag = true;
            }
        } while (flag);
        return x;
    }

    /**
     * this method is called if the user chose the Admin console, where it has all the Admin services available to be performed
     * throughout the method, the Admin class is called and manipulated
     * @param x is the choice of the user to which service to perform
     * @throws FileNotFoundException 
     */
    public static void admin() throws FileNotFoundException {
        Admin admin1 = new Admin();
        String username, password;
        int ID, parkingSpaces, x;
        double perHourRate;
        boolean flag;
        do {
            System.out.println("Please choose your service corresponding number, either 1, 2, 3, 4, 5, 6 or 7\n1. Add operator \n2. Delete operator \n3. Add parking lot\n4. Delete parking lot\n5. List all operators\n6. List all parking lots\n7. Save operators and parking lots information to a file");
            x = console.nextInt();
            if (x == 1 || x == 2 || x == 3 || x == 4 || x == 5 || x == 6 || x == 7) {
                flag = false;
            } else {
                flag = true;
            }
        } while (flag);
        switch (x) {
            case 1:
                System.out.println("Please enter Username: ");
                username = console.next();
                System.out.println("Please enter Password: ");
                password = console.next();
                Operator opAdd = new Operator(username, password);
                admin1.addOperator(opAdd);
                break;
            case 2:
                System.out.println("Please enter Username: ");
                username = console.next();
                Operator opDelete = new Operator(username, "");
                admin1.deleteOperator(opDelete);
                break;
            case 3:
                System.out.println("Please enter parking lot ID: ");
                ID = console.nextInt();
                System.out.println("Please enter parking spaces for this lot: ");
                parkingSpaces = console.nextInt();
                System.out.println("Please enter per hour rate for this lot: ");
                perHourRate = console.nextDouble();
                ParkingLot lotAdd = new ParkingLot(ID, parkingSpaces, perHourRate);
                admin1.addParkingLot(lotAdd);
                break;
            case 4:
                System.out.println("Please enter parking lot ID: ");
                ID = console.nextInt();
                ParkingLot lotDelete = new ParkingLot(ID, 0, 0);
                admin1.deleteParkingLot(lotDelete);
                break;
            case 5:
                admin1.printListOfOperators();
                break;
            case 6:
                admin1.printlistOfParkingLots();
                break;
            case 7:
                admin1.saveInfoToFile();
                break;
        }

    }

    /**
     * this method is called if the user chose the Operator console, where it has all the Operator services available to be performed
     * throughout the method, the Operator class is called and manipulated
     * @param x is the choice of the user to which service to perform
     * @param numberOfLots is the total number of lots the corresponding operator has under his custody
     * @param totalRevenue is the total revenue calculated for the corresponding operator
     */
    public static void operator() {
        String username, password;
        int ID, numberOfLots, plateNumber, x;
        double totalRevenue;
        ArrayList<ParkingLot> lotsUnderCustody = new ArrayList();

        System.out.println("Please enter Username: ");
        username = console.next();
        System.out.println("Please enter Password: ");
        password = console.next();
        if (Operator.isValid(username, password)) {
            Operator op1 = new Operator(username, password);
            System.out.println("Please choose your service corresponding number, either 1, 2, or 3\n1. Query number of parking slots in a lot \n2. Query revenue \n3. Find lot that has specific car");
            x = console.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Please enter parking lot ID: ");
                    ID = console.nextInt();
                    System.out.println("number of parking slots in given lot is: " + Admin.queryParkingLotSpaces(ID));
                    break;
                case 2:
                    System.out.println("Please enter number of parking lots under custody: ");
                    numberOfLots = console.nextInt();
                    op1.addNumberOfLotsUnderCustody(numberOfLots);
                    System.out.println("Please enter parking lots IDs: ");
                    for (int i = 0; i < numberOfLots; i++) {
                        lotsUnderCustody.add(new ParkingLot(console.nextInt(), 0, 0));
                        op1.addParkingLot(lotsUnderCustody.get(i));
                    }
                    totalRevenue = 0;
                    for (int i = 0; i < numberOfLots; i++) {
                        totalRevenue += (Admin.queryParkingLotSpaces(lotsUnderCustody.get(i).getID())) * Admin.queryParkingRate(lotsUnderCustody.get(i).getID());
                    }
                    System.out.println("Total Revenue is: " + totalRevenue + " AED");
                    break;
                case 3:
                    System.out.println("Please enter car plate number (5 digits number): ");
                    plateNumber = console.nextInt();
                    break;
            }
        } else {
            System.out.println("This Operator is not in the database");

        }
    }

    /**
     * this method is called if the user chose the Customer console, where it has all the Customer services available to be performed
     * @param x is the choice of the user to which service to perform
     * @param plateNumber is the variable that holds the customer car plate number
     * @param ID is the lot ID in which the customer wants to park his car
     */
    public static void customer() {
        int ID, plateNumber, hours, x;

        System.out.println("Please enter car plate number (5 digits number): ");
        plateNumber = console.nextInt();
        System.out.println("Please choose your service corresponding number, either 1, or 2\n1. Park Car \n2. Drive Away");
        x = console.nextInt();
        switch (x) {
            case 1:
                System.out.println("Please enter parking lot ID: ");
                ID = console.nextInt();
                break;
            case 2:
                System.out.println("Please enter number of hours stayed: ");
                hours = console.nextInt();
                System.out.println("Total Fees is: ");
                break;
        }

    }
}