/*
Mobile App Development I -- COMP.4630 Honor Statement
The practice of good ethical behavior is essential for
maintaining good order in the classroom, providing an
enriching learning experience for students, and training as
a practicing computing professional upon graduation. This
practice is manifested in the University's Academic
Integrity policy. Students are expected to strictly avoid
academic dishonesty and adhere to the Academic Integrity
policy as outlined in the course catalog. Violations will
Hello Sirong!
COMP.4630 HW 01 F23 - Dr. Lin
be dealt with as outlined therein. All programming
assignments in this class are to be done by the student
alone unless otherwise specified. No outside help is
permitted except the instructor and approved tutors.
I certify that the work submitted with this assignment is
mine and was generated in a manner consistent with this
document, the course academic policy on the course website
on Blackboard, and the UMass Lowell academic code.
Date: 9/8/2023
Name: Daniel Bergeron
*/
// A package is the file hiarchie you are working in
package com.mobileapp.helloworld;

// main library/classes
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//
public class MainActivity extends AppCompatActivity {
    // calls intial veiw I.e. the xml file activity_main
    // R is an automated class generated by android build system
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // returns the resource i.d of a specific layout
    }
}