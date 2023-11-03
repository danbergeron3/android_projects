/***********************************************
 Author: Daniel Bergeron
 Date: 10/8/2023
 Purpose: Add landscape portrait to android app and save its state
 What Learned: How to save states, how to loop through radio groups,
 how to create horizontal layouts.
 Sources of Help: Android documentation and lecture slides
 Time Spent: 3hrs
 Extra Points Explanation: <briefly explain what the surprise is>
************************************************************************
Mobile App Development I -- COMP.4630 Honor Statement
The practice of good ethical behavior is essential for maintaining
good order in the classroom, providing an enriching learning
experience for students, and training as a practicing computing
professional upon graduation. This practice is manifested in the
University's Academic Integrity policy. Students are expected to
strictly avoid academic dishonesty and adhere to the Academic
Integrity policy as outlined in the course catalog. Violations will be
dealt with as outlined therein. All programming assignments in this
class are to be done by the student alone unless otherwise specified.
No outside help is permitted except the instructor and approved
tutors.
I certify that the work submitted with this assignment is mine and was
generated in a manner consistent with this document, the course
academic policy on the course website on Blackboard, and the UMass
Lowell academic code.
Date:10/8/2023
Name: Daniel Bergeron
*/
package com.mobileapp.pizzaorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public int people = 0;
    // hold state values in case if state change
    public boolean isUIEnabled = true;
    public String displayStr = "";
    public String toppingsDisplayStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView toppings = findViewById(R.id.textView6);
        Button submit = findViewById(R.id.button);
        CheckBox box1 = findViewById(R.id.checkBox);
        CheckBox box2 = findViewById(R.id.checkBox2);
        CheckBox box3 = findViewById(R.id.checkBox3);
        EditText textIn = findViewById(R.id.editTextText);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        textIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                TextView deltaText = findViewById(R.id.textView5);
                String inString = textIn.getText().toString();
                if (!inString.isEmpty()) {
                    people = Integer.parseInt(inString);
                    deltaText.setText(charSequence);
                    deltaText.setVisibility(View.INVISIBLE);
                }
                radioGroup.clearCheck();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        int num;

                        if (checkedId == R.id.radioButton) {
                            num = calculatePizza(people, 2);
                            displayStr = Integer.toString(num);
                            deltaText.setText(displayStr);
                            deltaText.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.radioButton2) {
                            num = calculatePizza(people, 3);
                            displayStr = Integer.toString(num);
                            deltaText.setText(displayStr);
                            deltaText.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.radioButton3){
                            num = calculatePizza(people, 4);
                            displayStr = Integer.toString(num);
                            deltaText.setText(displayStr);
                            deltaText.setVisibility(View.VISIBLE);
                        } else {
                            displayStr = " ";
                            deltaText.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(submit, "Order Submitted!", Snackbar.LENGTH_SHORT).show();
                // disable here
                for(int i = 0; i < radioGroup.getChildCount(); i++){
                    View radioButton = radioGroup.getChildAt(i);
                    radioButton.setEnabled(false);
                }
                textIn.setEnabled(false);
                box1.setEnabled(false);
                box2.setEnabled(false);
                box3.setEnabled(false);
                submit.setEnabled(false);
                isUIEnabled = false;
            }
        });
        box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    toppings.append(box1.getText().toString());
                    toppings.append("\n");
                } else {
                    String tempStr = toppings.getText().toString();
                    tempStr = tempStr.replace("Extra Cheese\n","");
                    toppings.setText(tempStr);
                }
                toppingsDisplayStr = toppings.getText().toString();
            }
        });
        box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    toppings.append(box2.getText().toString());
                    toppings.append("\n");
                } else {
                    String tempStr = toppings.getText().toString();
                    tempStr = tempStr.replace("Red Pepper\n","");
                    toppings.setText(tempStr);
                }
                toppingsDisplayStr = toppings.getText().toString();
            }
        });

        box3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    toppings.append(box3.getText().toString());
                    toppings.append("\n");
                } else {
                    String tempStr = toppings.getText().toString();
                    tempStr = tempStr.replace("Mushrooms\n","");
                    toppings.setText(tempStr);
                }
                toppingsDisplayStr = toppings.getText().toString();
            }
        });
        if (savedInstanceState != null) {
            if (!(savedInstanceState.getBoolean("UI_Enabled"))) {
                submit.performClick();
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //save UI state, text display for pizza, and text display for toppings
        savedInstanceState.putBoolean("UI_Enabled", isUIEnabled);
        savedInstanceState.putString("Text_Display", displayStr);
        savedInstanceState.putString("Toppings_Display", toppingsDisplayStr);
        super.onSaveInstanceState(savedInstanceState);
    }
    static int calculatePizza(int people, int level) {
        int remainder = (people * level) % 12;
        int boxes = (people * level) / 12;
        if (remainder > 0) {
            boxes += 1;
        }
        return boxes;
    }
}