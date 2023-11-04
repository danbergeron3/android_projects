package com.mobileapp.lightsout;

import static com.mobileapp.lightsout.Game_Const.Constants.COLUMN_LENGTH;
import static com.mobileapp.lightsout.Game_Const.Constants.ROW_LENGTH;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.res.ColorStateList;
import android.widget.TextView;


public class Game_Board{

    // class variables
    Game_Lights[][] matrix_of_lights = new Game_Lights[3][3];
    private int color;
    private int moves = 0;

    // value constructor
    public Game_Board(Button[] btn, int _color) {
        int btn_index = 0;

        // loop through and fill matrix_of_lights with light objects
        for (int i = 0; i < ROW_LENGTH; i++) {
            for(int j = 0; j < COLUMN_LENGTH; j++){
                // set board color
                color = _color;

                // fill matrix with lights
                Log.d("CONSTRUCTOR_DEBUG", "color: " + color);
                Game_Lights new_light = new Game_Lights(btn[btn_index].getId(), i, j, false);
                Log.d("CONSTRUCTOR_DEBUG", "id: " + btn[btn_index].getId());
                matrix_of_lights[i][j] = new_light;
                btn_index++;
            }
        }
    }

    // initialize: goes through and makes sure visual representation of lights reflects internal,
    // sets start state of game
    public void initialize(View view) {

        // generate random number for starting lights on
        double raw_random_number = Math.random();
        int lights_on = (int)(raw_random_number * ((ROW_LENGTH * COLUMN_LENGTH) - 1)) + 1;

        // if initializing enforce the start state, make sure all lights are off
        int current_lights_on = 0;
        int light_row, light_col;
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COLUMN_LENGTH; j++) {
                matrix_of_lights[i][j].set_isOn(false);
                Button light = view.findViewById(matrix_of_lights[i][j].get_id());
                int colorStateList = light.getResources().getColor(R.color.light_grey);
                light.setBackgroundTintList(ColorStateList.valueOf(colorStateList));
            }
        }

        // create light location by generating random number for column and row
        // loop through till we have turned on the specified amount of lights in valid locations
        while (current_lights_on < lights_on) {

            // random number generator
            raw_random_number = Math.random();
            light_row = (int)(ROW_LENGTH * raw_random_number);
            raw_random_number = Math.random();
            light_col = (int)(COLUMN_LENGTH * raw_random_number);

            if (!(matrix_of_lights[light_row][light_col].get_isOn())){

                // turn on lights,
                toggle_light(light_row, light_col, view, true, false);

                // increment because we found and open spot
                current_lights_on++;

            } else {
                // no open spot found so don't increment
                Log.d("INITIALIZE_DEBUG", "Roll for location: " + current_lights_on);
            }
        }

        // set Moves count
        moves = 0;
        TextView xml_moves = view.findViewById(R.id.move);
        String temp = "Moves: " + moves;
        xml_moves.setText(temp);
    }

    // takes light location, View and bool for if the light should be on or off,
    // flip tells us to flip the value of the light rather than forcing off or on
    public void toggle_light(int light_row, int light_col, View view, boolean on, boolean flip) {

        // if the request is outside of the matrix's valid indexes deny toggle request
        int max_size = 3;
        if((light_row < 0 || light_col < 0)
                || (light_col >= max_size || light_row >= max_size)) {
            Log.d("TOGGLE_DEBUG", "Toggle error, x,y out of bounds");
            return;
        }

        // check if flip is on, if so overwrite the on value with bool representing the opposite
        // state of the on state
        if(flip) {
            on = !matrix_of_lights[light_row][light_col].get_isOn();
            Log.d("TOGGLE_DEBUG", "Flip is set for id: " +
                    matrix_of_lights[light_row][light_col].get_id() +
                    " Former button value: " + matrix_of_lights[light_row][light_col].get_isOn()
                    + ": new value is: " + (!matrix_of_lights[light_row][light_col].get_isOn()));

        }

        // update internal representation of data, and retrieve internal id
        matrix_of_lights[light_row][light_col].set_isOn(on);
        int cur_id = (int)matrix_of_lights[light_row][light_col].get_id();

        // to ensure outside representation matches inside data we can only use internal id to find
        // light
        Button light = view.findViewById(cur_id);

        // use color to retrieve color value from memory
        int colorStateList;
        if(on) {
            colorStateList = light.getResources().getColor(color);
        } else {
            colorStateList = light.getResources().getColor(R.color.light_grey);
        }

        // Set the background tint using setBackgroundTintList
        light.setBackgroundTintList(ColorStateList.valueOf(colorStateList));
    }


    public void button_click (int id, View view){
        Log.d("BUTTON_CLICK_DEBUG", "game stub");

        // get the current lights locations
        Game_Lights currLight = get_light_by_id(id);
        int lights_to_toggle_row = currLight.get_row();
        int lights_to_toggle_col = currLight.get_col();

        // get value to control toggling, use flip value for light toggling
        boolean toggle_value = currLight.get_isOn();
        Log.d("BUTTON_CLICK_DEBUG", "base values: row = " + (lights_to_toggle_row) +
                " col = " + (lights_to_toggle_col));

        // toggle light up on
        Log.d("BUTTON_CLICK_DEBUG", "up values: row = " + (lights_to_toggle_row-1) +
                " col " + (lights_to_toggle_col));
        toggle_light((lights_to_toggle_row-1), (lights_to_toggle_col),
                view, toggle_value, true);

        // toggle light right on
        Log.d("BUTTON_CLICK_DEBUG", "right values: row = " + (lights_to_toggle_row) +
                " col = " + (lights_to_toggle_col+1));
        toggle_light((lights_to_toggle_row), (lights_to_toggle_col+1), view, true, true);

        // toggle light down on
        Log.d("BUTTON_CLICK_DEBUG", "down values: row = " + (lights_to_toggle_row+1) +
                " col = " + (lights_to_toggle_col));
        toggle_light((lights_to_toggle_row+1), (lights_to_toggle_col), view, true,true);

        // toggle light left on
        Log.d("BUTTON_CLICK_DEBUG", "left values: row = " + (lights_to_toggle_row) +
                " y = " + (lights_to_toggle_col-1));
        toggle_light((lights_to_toggle_row), (lights_to_toggle_col-1), view, true, true);

        // toggle current light off
        toggle_light(lights_to_toggle_row, lights_to_toggle_col, view, false, true);

        // update moves view
        moves++;
        TextView xml_moves = view.findViewById(R.id.move);
        String temp = "Moves: " + moves;
        xml_moves.setText(temp);
    }

    public boolean game_over() {
        Log.d("GAME_OVER_DEBUG", "game stub");

        // go through and check every index in the matrix to see if light is on, if all are off,
        // game over is true, else off
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COLUMN_LENGTH; j++) {
                //parameters (id, x, y, isOn)
                Log.d("GAME_OVER_DEBUG", "check if lights on");
                if (matrix_of_lights[i][j].get_isOn()) {
                    Log.d("GAME_OVER_DEBUG", "check if lights on: false");
                    return false;
                }
            }
        }
        Log.d("GAME_OVER_DEBUG", "check if lights on: true");
        return true;
    }

    // return the light who's id matches the argument
    private Game_Lights get_light_by_id(int id) {
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COLUMN_LENGTH; j++) {
                //parameters (id, x, y, isOn)
                Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "finding light at row: " +
                        i + " col: " + j);
                if (matrix_of_lights[i][j].get_id() == id) {
                    Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "found light at row: " +
                            i + " col: " + j + "id: " + id);
                    return matrix_of_lights[i][j];

                }
            }
        }

        // if we get here error
        Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "finding light error, could not find light");
        return matrix_of_lights[0][0];
    }

    // getter functions:
    public int get_moves() {
        return moves;
    }
}

