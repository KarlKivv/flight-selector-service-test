package com.cgi2025summer.model;

import java.util.ArrayList;

public class Airplane {
    private String id;
    private int nrOfSeats;
    private int nrOfColumns;
    private ArrayList<AirplaneSeat> allSeats;

    public Airplane(String id, int nrOfSeats, int nrOfColumns) {
        if (nrOfSeats % nrOfColumns != 0) {
            throw new IllegalArgumentException(
                    "number of seats needs to be cleanly divisible by the number of columns");
        }
        this.id = id;
        this.nrOfSeats = nrOfSeats;
        this.allSeats = new ArrayList<>();

        addSeats();
    }

    private void addSeats() {
        int maxRows = this.nrOfSeats / this.nrOfColumns;
        for (int row = 0, column = 0; row < maxRows; column++) {
            if (column > nrOfColumns) {
                column = 0;
                row++;
            }
            allSeats.add(new AirplaneSeat(row, SeatColumnEnum.values()[column]));
        }
    }

    public String getId() {
        return id;
    }
}
