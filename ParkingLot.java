/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglotsystem;

/**
 *
 * @author Ahmad Dalao
 */
public class ParkingLot {
    private int ID;
    private int parkingSpaces;
    private double perHourRate;

    public ParkingLot(int ID, int parkingSpaces, double perHourRate) {
        this.ID = ID;
        this.parkingSpaces = parkingSpaces;
        this.perHourRate = perHourRate;
    }

    public int getID() {
        return ID;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public double getPerHourRate() {
        return perHourRate;
    }
    

    @Override
    public String toString() {
        return "\n{" + "ID = " + ID + ", parkingSpaces = " + parkingSpaces + ", perHourRate = " + perHourRate + '}';
    }
    
    
}
