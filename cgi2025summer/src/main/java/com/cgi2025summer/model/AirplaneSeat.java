package com.cgi2025summer.model;

public class AirplaneSeat {
    private int row;
    private SeatColumnEnum column;
    private boolean available;

    public AirplaneSeat(int row, SeatColumnEnum column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public boolean isAvailable() {
        return available;
    }

    public SeatColumnEnum getColumn() {
        return column;
    }

    public void reserveSeat() {
        this.available = false;
    }

    public void unreserveSeat() {
        this.available = true;
    }
}
