package com.mobileapp.lightsout;

// holds information about states of individual buttons
public class Game_Lights {

    public Game_Lights(int _id, int _row, int _col, boolean _isOn) {
        id = _id;
        row = _row;
        col = _col;
        isOn = _isOn;
    }

    // getters
    public int get_row() {
        return row;
    }
    public int get_col() {
        return col;
    }

    public boolean get_isOn() {
        return isOn;
    }

    public int get_id() {
        return id;
    }

    // accessor
    public void set_isOn(boolean _isOn) {
        isOn = _isOn;
    }

    // class variables
    private boolean isOn;
    final private int row;
    final private int col;
    final private int id;
}
