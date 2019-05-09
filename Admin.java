package parkinglotsystem;

import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ahmad Dalao
 */
public class Admin {

    protected static ArrayList<Operator> listOfOperators;
    private static ArrayList<ParkingLot> listOfParkingLots;
    private PrintWriter filePrinter;

    public void addOperator(Operator o) {
        boolean flag = true;
        for (int i = 0; i < listOfOperators.size(); i++) {
            if (!((listOfOperators.get(i).getUsername()).equals(o.getUsername()))) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            listOfOperators.add(o);
        }
    }

    public void deleteOperator(Operator o) {
        boolean flag = false;
        int i;
        for (i = 0; i < listOfOperators.size(); i++) {
            if ((listOfOperators.get(i).getUsername()).equals(o.getUsername())) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag) {
            listOfOperators.remove(i);
        }
    }

    public void addNumberOfOperators(int numberOfOperators) {
        listOfOperators = new ArrayList();
    }

    public void addNumberOfParkingLots(int numberOfLots) {
        listOfParkingLots = new ArrayList();
    }

    public void addParkingLot(ParkingLot o) {
        boolean flag = true;
        for (int i = 0; i < listOfParkingLots.size(); i++) {
            if (!(listOfParkingLots.get(i).getID() == o.getID())) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            listOfParkingLots.add(o);
        }
    }

    public void deleteParkingLot(ParkingLot o) {
        boolean flag = false;
        int i;
        for (i = 0; i < listOfParkingLots.size(); i++) {
            if (listOfParkingLots.get(i).getID() == o.getID()) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag) {
            listOfParkingLots.remove(i);
        }
    }

    public void printListOfOperators() {
        System.out.println(listOfOperators);
    }

    public void printlistOfParkingLots() {
        System.out.println(listOfParkingLots);
    }

    public void saveInfoToFile() throws FileNotFoundException {
        this.filePrinter = new PrintWriter("file.txt");
        filePrinter.println("Parking Lots Information:");
        filePrinter.println(listOfParkingLots);
        filePrinter.println("Operators Information:");
        filePrinter.println(listOfOperators);
        filePrinter.close();
    }

    public static int queryParkingLotSpaces(int ID) {
        for (int i = 0; i < listOfParkingLots.size(); i++) {
            if (listOfParkingLots.get(i).getID() == ID) {
                return (listOfParkingLots.get(i).getParkingSpaces());
            }
        }
        return -1;
    }

    public static double queryParkingRate(int ID) {
        for (int i = 0; i < listOfParkingLots.size(); i++) {
            if (listOfParkingLots.get(i).getID() == ID) {
                return (listOfParkingLots.get(i).getPerHourRate());
            }
        }
        return -1;
    }

}
