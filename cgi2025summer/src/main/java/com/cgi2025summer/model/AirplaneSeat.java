package com.cgi2025summer.model;

public class AirplaneSeat {
    private int row;
    private SeatColumn column;
    private boolean available;

    public AirplaneSeat(int row, SeatColumn column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public boolean isAvailable() {
        return available;
    }

    public SeatColumn getColumn() {
        return column;
    }

    public void reserveSeat() {
        this.available = false;
    }

    public void unreserveSeat() {
        this.available = true;
    }
}
