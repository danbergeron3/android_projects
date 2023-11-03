/*
Mobile App Development I -- COMP.4630 Honor Statement
The practice of good ethical behavior is essential for maintaining
good order in the classroom, providing an enriching learning
experience for students, and training as a practicing computing
professional upon graduation. This practice is manifested in the
University's Academic Integrity policy. Students are expected to
strictly avoid academic dishonesty and adhere to the Academic
Integrity policy as outlined in the course catalog. Violations
will be dealt with as outlined therein. All programming assignments
in this class are to be done by the student alone unless otherwise
specified. No outside help is permitted except the instructor and
approved tutors.
I certify that the work submitted with this assignment is mine and
was generated in a manner consistent with this document, the course
academic policy on the course website on Blackboard, and the UMass
Lowell academic code.
Date:9/27/2023
Name: Daniel Bergeron
*/
package com.mobileapp.lecture07exer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox box = findViewById(R.id.checkBox1);
        TextView deltaText = findViewById(R.id.textView);
        EditText textIn = findViewById(R.id.editTextText);
        textIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                deltaText.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    deltaText.setTextColor(Color.RED);
                    Snackbar.make(radioGroup, "Red clicked!!", Snackbar.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radioButton2) {
                    deltaText.setTextColor(Color.YELLOW);
                    Snackbar.make(radioGroup, "Yellow clicked!!", Snackbar.LENGTH_SHORT).show();
                } else {
                    deltaText.setTextColor(Color.BLUE);
                    Snackbar.make(radioGroup, "Blue clicked!!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                RadioButton one = findViewById(R.id.radioButton1);
                RadioButton two = findViewById(R.id.radioButton2);
                RadioButton three = findViewById(R.id.radioButton3);
                if(isChecked) {
                    one.setEnabled(false);
                    two.setEnabled(false);
                    three.setEnabled(false);
                } else {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                }
            }
        });
    }
}