package com.mobileapp.lightsout;

import static com.mobileapp.lightsout.Game_Const.Constants.COLUMN_LENGTH;
import static com.mobileapp.lightsout.Game_Const.Constants.ROW_LENGTH;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.res.ColorStateList;
import android.widget.TextView;


public class Game_Board{
    // initialize game buttons with information

    Game_Lights[][] matrix_of_lights = new Game_Lights[3][3];
    private int moves = 0;
    public Game_Board(Button[] btn) {
        int btn_index = 0;
        for (int i = 0; i < ROW_LENGTH; i++) {
            for(int j = 0; j < COLUMN_LENGTH; j++){
                //parameters (id, x, y, isOn)
                Game_Lights new_light = new Game_Lights(btn[btn_index].getId(), i, j, false);
                Log.d("CONSTRUCTOR_DEBUG", "id: " + btn[btn_index].getId());
                matrix_of_lights[i][j] = new_light;
                btn_index++;
            }
        }
    }

    // getter

    public void initialize(View view) {
        // generate random number for lights on
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

        // create light location
        // loop through till we have turned on the specified amount of lights
        while (current_lights_on < lights_on) {

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
        xml_moves.setText("Moves: " + moves);
    }

    // takes light location, id and bool for if the light should be on or of,
    // flip tells us to flip the value rather than assign directly
    public void toggle_light(int light_row, int light_col, View view, boolean on, boolean flip) {
        int max_size = 3;
        if((light_row < 0 || light_col < 0)
                || (light_col >= max_size || light_row >= max_size)) {
            Log.d("TOGGLE_DEBUG", "Toggle error, x,y out of bounds");
            return;
        }
        if(flip) {
            on = !matrix_of_lights[light_row][light_col].get_isOn();
            Log.d("TOGGLE_DEBUG", "Flip is set for id: " + matrix_of_lights[light_row][light_col].get_id() +
                    " Former button value: " + matrix_of_lights[light_row][light_col].get_isOn()
                    + ": new value is: " + (!matrix_of_lights[light_row][light_col].get_isOn()));

        }
        matrix_of_lights[light_row][light_col].set_isOn(on);
        int cur_id = (int)matrix_of_lights[light_row][light_col].get_id();

        // current light we found based of id
        Button light = view.findViewById(cur_id);

        int colorStateList;

        // Create a ColorStateList with the desired colors
        if(on) {
            colorStateList = light.getResources().getColor(R.color.amber);
        } else {
            colorStateList = light.getResources().getColor(R.color.light_grey);
        }

        // Set the background tint using setBackgroundTintList
        light.setBackgroundTintList(ColorStateList.valueOf(colorStateList));
    }


    public void button_click (int id, View view){
        Log.d("BUTTON_CLICK_DEBUG", "game stub");
        Game_Lights currLight = get_light_by_id(id);
        // get adjacent lights

        int lights_to_toggle_row = currLight.get_row();
        int lights_to_toggle_col = currLight.get_col();
        boolean toggle_value = currLight.get_isOn();

        Log.d("BUTTON_CLICK_DEBUG", "base values: row = " + (lights_to_toggle_row) + " col = " + (lights_to_toggle_col));

        // toggle light up on
        Log.d("BUTTON_CLICK_DEBUG", "up values: row = " + (lights_to_toggle_row-1) + " col " + (lights_to_toggle_col));
        toggle_light((lights_to_toggle_row-1), (lights_to_toggle_col), view, toggle_value, true);

        // toggle light right on
        Log.d("BUTTON_CLICK_DEBUG", "right values: row = " + (lights_to_toggle_row) + " col = " + (lights_to_toggle_col+1));
        toggle_light((lights_to_toggle_row), (lights_to_toggle_col+1), view, true, true);

        // toggle light down on
        Log.d("BUTTON_CLICK_DEBUG", "down values: row = " + (lights_to_toggle_row+1) + " col = " + (lights_to_toggle_col));
        toggle_light((lights_to_toggle_row+1), (lights_to_toggle_col), view, true,true);

        // toggle light left on
        Log.d("BUTTON_CLICK_DEBUG", "left values: row = " + (lights_to_toggle_row) + " y = " + (lights_to_toggle_col-1));
        toggle_light((lights_to_toggle_row), (lights_to_toggle_col-1), view, true, true);

        // toggle current light off
        toggle_light(lights_to_toggle_row, lights_to_toggle_col, view, false, true);

        // update moves view
        moves++;
        TextView xml_moves = view.findViewById(R.id.move);
        xml_moves.setText("Moves: " + moves);
    }

    public boolean game_over() {
        Log.d("GAME_OVER_DEBUG", "game stub");
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
    private Game_Lights get_light_by_id(int id) {
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COLUMN_LENGTH; j++) {
                //parameters (id, x, y, isOn)
                Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "finding light at row: " + i + " col: " + j);
                if (matrix_of_lights[i][j].get_id() == id) {
                    Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "found light at row: " + i + " col: " + j + "id: " + id);
                    return matrix_of_lights[i][j];

                }
            }
        }

        // if we get here error
        Log.d("GET_LIGHT_DEBUG < BUTTON_CLICK", "finding light error, could not find light");
        return matrix_of_lights[0][0];
    }

    public int get_moves() {
        return moves;
    }
}

