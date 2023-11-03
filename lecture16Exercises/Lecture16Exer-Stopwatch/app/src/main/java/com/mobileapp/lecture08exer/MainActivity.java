/*
What do you see when rotating the device/emulator with a
non-zero stopwatch: The time reset to 00:00 when it was rotated.
*/
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
Date: 11/1/2023
Name: Daniel Bergeron
*/
package com.mobileapp.lecture08exer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;

import com.mobileapp.lecture08exer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    final long[] offset = {0};
    final long[] base = {0};
    final boolean[] isRunning = {false};
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("STOPWATCH_DEBUG", "in inCreate()");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Chronometer aChronometer = binding.aChronometer; // findViewById(R.id.aChronometer);


        base[0] = SystemClock.elapsedRealtime();

        if (savedInstanceState != null) {
            base[0] = savedInstanceState.getLong("kbase");
            offset[0] = savedInstanceState.getLong("koffset");
            isRunning[0] = savedInstanceState.getBoolean("kisRunning");
            if (isRunning[0] == true) {
                aChronometer.setBase(base[0]);
                aChronometer.start();
            } else {
                // is paused
                base[0] = SystemClock.elapsedRealtime() - offset[0];
            }
        }

        aChronometer.setBase(base[0]);
        Button start = binding.buttonStart;
        // Button start = findViewById(R.id.buttonStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning[0] == false) {
                    base[0] = SystemClock.elapsedRealtime() - offset[0];
                    aChronometer.setBase(base[0]);
                    aChronometer.start();
                    isRunning[0] = true;
                }
            }
        });
        // Button pause = findViewById(R.id.buttonPause);
        Button pause = binding.buttonPause;
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning[0] == true) {
                    aChronometer.stop();
                    base[0] = aChronometer.getBase();
                    offset[0] = SystemClock.elapsedRealtime() - base[0];
                    isRunning[0] = false;
                }
            }
        });
        // Button reset = findViewById(R.id.buttonReset);
        Button reset = binding.buttonReset;
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base[0] = SystemClock.elapsedRealtime();
                aChronometer.setBase(base[0]);
                offset[0] = 0;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("STOPWATCH_DEBUG", "in inStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();;
        Log.d("STOPWATCH_DEBUG", "in inResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("STOPWATCH_DEBUG", "in inPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("STOPWATCH_DEBUG", "in inStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("STOPWATCH_DEBUG", "in inDestroy()");
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // TODO: save the stopwatch state,
        savedInstanceState.putLong("koffset", offset[0]);
        savedInstanceState.putBoolean("kisRunning", isRunning[0]);
        savedInstanceState.putLong("kbase", base[0]);
        super.onSaveInstanceState(savedInstanceState);
        Log.d("STOPWATCH_DEBUG", "in inSavedInstance()");
    }

}
