package com.mobileapp.lightsout;


import static com.mobileapp.lightsout.Game_Const.Constants.COLUMN_LENGTH;
import static com.mobileapp.lightsout.Game_Const.Constants.GAME_BUTTONS;
import static com.mobileapp.lightsout.Game_Const.Constants.ROW_LENGTH;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Game_Board_Screen extends Fragment implements View.OnClickListener{

    // class variables required by view
    Game_Board game_board;
    public int color = R.color.current_color;
    Button[] btn;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_game_board_screen, container, false);

        Log.d("GAME_BOARD_SCREEN_DEBUG", "color is set to: " + color);

        // create and array of buttons to represent our lights
        btn = new Button[]{view.findViewById(R.id.button),
                view.findViewById(R.id.button2), view.findViewById(R.id.button3),
                view.findViewById(R.id.button4), view.findViewById(R.id.button5),
                view.findViewById(R.id.button6), view.findViewById(R.id.button7),
                view.findViewById(R.id.button8), view.findViewById(R.id.button9)};

        // get new game button
        Button new_game = view.findViewById(R.id.new_game);

        // get color from settings screen, if -1 go with default value
        color = Game_Board_ScreenArgs.fromBundle(getArguments()).getColor();
        if(color == -1) {
            color = R.color.current_color;
        }

        // set onClick listener for new game and buttons/lights
        new_game.setOnClickListener(this);
        for (int i = 0; i < GAME_BUTTONS; i++){
            btn[i].setOnClickListener(this);
        }

        // create instance of game_board then set its' start state
        game_board = new Game_Board(btn, color);
        game_board.initialize(view);

        return view;
    }

    // onClick method a for all clickable objects
    @Override
    public void onClick(View v) {

        // get which button was clicked
        int clickedBtn = v.getId();
        Log.d("ON_CLICK_DEBUG", "FOUND id: " + clickedBtn);

        // if new_game turn board off, and create new instance of game_board, then reinitialize
        if (clickedBtn == R.id.new_game) {
            for (int i = 0; i < ROW_LENGTH; i++) {
                for(int j = 0; j < COLUMN_LENGTH; j++) {
                    game_board.toggle_light(i, j, view, false, false);
                    game_board = new Game_Board(btn, color);
                    game_board.initialize(view);
                }
            }
            Log.d("New_GAME", "in onClick new game");
        }

        // if a button/lights is clicked call click method to handle it then check for win condition
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
            // if this is reached something went wrong, but wrong id won't effect game
            Log.d("Board_game_DEBUG", "in else condition");
        }
    }
    private void test_game_over() {

        // call a game over check from the class, if true pass moves to game completion fragment
        if(game_board.game_over()) {
            Log.d("Board_game_DEBUG", "win condition go to next screen");
            String message = "Moves: " + game_board.get_moves();
            Log.d("ADebugTag", "Value: " + message);
            Game_Board_ScreenDirections.ActionGameBoardScreenToGameCompletionScreen action =
                Game_Board_ScreenDirections.actionGameBoardScreenToGameCompletionScreen(message);
            Navigation.findNavController(view).navigate(action);
        }
    }
}

