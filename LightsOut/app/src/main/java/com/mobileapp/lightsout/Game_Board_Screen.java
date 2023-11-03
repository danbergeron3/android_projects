package com.mobileapp.lightsout;


import static com.mobileapp.lightsout.Game_Const.Constants.COLUMN_LENGTH;
import static com.mobileapp.lightsout.Game_Const.Constants.GAME_BUTTONS;
import static com.mobileapp.lightsout.Game_Const.Constants.ROW_LENGTH;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Game_Board_Screen extends Fragment implements View.OnClickListener{
    Game_Board game_board;
    Context con; //need by toast
    Button[] btn;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_game_board_screen, container, false);
        // create a game board object

        // set onclick listeners
        /*Button btn1 = view.findViewById(R.id.button);
        Button btn2 = view.findViewById(R.id.button2);
        Button btn3 = view.findViewById(R.id.button3);
        Button btn4 = view.findViewById(R.id.button4);
        Button btn5 = view.findViewById(R.id.button5);
        Button btn6 = view.findViewById(R.id.button6);
        Button btn7 = view.findViewById(R.id.button7);
        Button btn8 = view.findViewById(R.id.button8);
        Button btn9 = view.findViewById(R.id.button9);*/

         Button[] temp_btn = {view.findViewById(R.id.button),
                view.findViewById(R.id.button2), view.findViewById(R.id.button3),
                view.findViewById(R.id.button4), view.findViewById(R.id.button5),
                view.findViewById(R.id.button6), view.findViewById(R.id.button7),
                view.findViewById(R.id.button8), view.findViewById(R.id.button9)};
         btn = temp_btn;
        Button new_game = view.findViewById(R.id.new_game);

        // set onClick listeners
        new_game.setOnClickListener(this);
        /*btn[0].setOnClickListener(this);
        btn[1].setOnClickListener(this);
        btn[2].setOnClickListener(this);
        btn[3].setOnClickListener(this);
        btn[4].setOnClickListener(this);
        btn[5].setOnClickListener(this);
        btn[6].setOnClickListener(this);
        btn[7].setOnClickListener(this);
        btn[8].setOnClickListener(this);
        new_game.setOnClickListener(this);*/
        for (int i = 0; i < GAME_BUTTONS; i++){
            btn[i].setOnClickListener(this);
        }

        // will add on save information
        game_board = new Game_Board(btn);
        game_board.initialize(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        int clickedBtn = v.getId();
        Log.d("ON_CLICK_DEBUG", "FOUND id: " + clickedBtn);
        if (clickedBtn == R.id.new_game) {
            for (int i = 0; i < ROW_LENGTH; i++) {
                for(int j = 0; j < COLUMN_LENGTH; j++) {
                    game_board.toggle_light(i, j, view, false, false);
                    game_board = new Game_Board(btn);
                    game_board.initialize(view);
                }
            }
            Log.d("New_GAME", "in onClick new game");
        }


        if (clickedBtn == R.id.button) {

            Log.d("Board_game_DEBUG", "in onClick button1");
            game_board.button_click(R.id.button, view);
            test_game_over();

        } else if (clickedBtn == R.id.button2) {

            Log.d("Board_game_DEBUG", "in onClick button2");
            game_board.button_click( R.id.button2, view);
            test_game_over();

        } else if (clickedBtn == R.id.button3) {

            Log.d("Board_game_DEBUG", "in onClick button3");
            game_board.button_click( R.id.button3, view);
            test_game_over();

        } else if (clickedBtn == R.id.button4) {

            Log.d("Board_game_DEBUG", "in onClick button4");
            game_board.button_click( R.id.button4, view);
            test_game_over();

        } else if (clickedBtn == R.id.button5) {

            Log.d("Board_game_DEBUG", "in onClick button5");
            game_board.button_click( R.id.button5, view);
            test_game_over();

        } else if (clickedBtn == R.id.button6) {

            Log.d("Board_game_DEBUG", "in onClick button6");
            game_board.button_click( R.id.button6,view);
            test_game_over();

        } else if (clickedBtn == R.id.button7) {

            Log.d("Board_game_DEBUG", "in onClick button7");
            game_board.button_click( R.id.button7, view);
            test_game_over();

        } else if (clickedBtn == R.id.button8) {

            Log.d("Board_game_DEBUG", "in onClick button8");
            game_board.button_click( R.id.button8, view);
            test_game_over();

        } else if (clickedBtn == R.id.button9) {

            Log.d("Board_game_DEBUG", "in onClick button9");
            game_board.button_click(R.id.button9, view);
            test_game_over();

        } else {
            Log.d("Board_game_DEBUG", "in else condition");
        }
    }
    private void test_game_over() {
        if(game_board.game_over()) {
            // Navigation.findNavController(view).navigate(R.id.action_game_Board_Screen_to_game_Completion_Screen);
            Log.d("Board_game_DEBUG", "win condition go to next screen");
            // nav message
            String message = "Moves " + game_board.get_moves();
            Log.d("ADebugTag", "Value: " + message);
            Game_Board_ScreenDirections.ActionGameBoardScreenToGameCompletionScreen action =
                Game_Board_ScreenDirections.actionGameBoardScreenToGameCompletionScreen(message);
            Navigation.findNavController(view).navigate(action);
        }
    }
}

