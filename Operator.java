/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglotsystem;

import java.util.ArrayList;

/**
 *
 * @author Ahmad Dalao
 */
public class Operator extends Admin {

    private String username;
    private String password;
    private static ArrayList<ParkingLot> listOfOperatorParkingLots;

    public Operator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean isValid(String username, String password) {
        boolean flag = false;
        for (int i = 0; i < Admin.listOfOperators.size(); i++) {
            if (((listOfOperators.get(i).getUsername()).equals(username)) && ((listOfOperators.get(i).getPassword()).equals(password))) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void addNumberOfLotsUnderCustody(int numberOfLots) {
        listOfOperatorParkingLots = new ArrayList(numberOfLots);
    }

    public void addParkingLot(ParkingLot o) {
        boolean flag = true;
        for (int i = 0; i < listOfOperatorParkingLots.size(); i++) {
            if (!(listOfOperatorParkingLots.get(i).getID() == o.getID())) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            listOfOperatorParkingLots.add(o);
        }
    }

    public void deleteParkingLot(ParkingLot o) {
        boolean flag = false;
        int i;
        for (i = 0; i < listOfOperatorParkingLots.size(); i++) {
            if (listOfOperatorParkingLots.get(i).getID() == o.getID()) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag) {
            listOfOperatorParkingLots.remove(i);
        }
    }

    public void printlistOfParkingLots() {
        System.out.println(listOfOperatorParkingLots);
    }

    @Override
    public String toString() {
        String str = "\n{" + "username = " + username + ", password = " + password + ", parking lot IDs =";
        for (int i = 0; i < listOfOperatorParkingLots.size(); i++) {
            if (i == (listOfOperatorParkingLots.size() - 1)) {
                str += " " + listOfOperatorParkingLots.get(i).getID() + '}';
            } else {
                str += " " + listOfOperatorParkingLots.get(i).getID() + ",";
            }
        }
        return (str);
    }

}
