package com.cgi2025summer.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Seat {

    @Autowired
    private SeatColumnEnum column;

    private int row;
    private boolean available;
    private int attractionScore;

    public Seat(int row, SeatColumnEnum column) {
        this.row = row;
        this.column = column;
        this.available = true;
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

    @Override
    public String toString() {
        return String.format("%s - %d", this.column.toString(), this.row);
    }

    public void setColumn(SeatColumnEnum column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
