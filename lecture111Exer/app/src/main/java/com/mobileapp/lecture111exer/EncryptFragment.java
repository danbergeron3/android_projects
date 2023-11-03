package com.mobileapp.lecture111exer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EncryptFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String hiddenMessage;
        String message = EncryptFragmentArgs.fromBundle(requireArguments()).getMessage();
        Log.d("DISPLAY_DEBUG", "Value: " + message);
        hiddenMessage = encrypt(message);
        Log.d("DISPLAY_DEBUG", "Value: " + hiddenMessage);
        View rootView = inflater.inflate(R.layout.fragment_encrypt, container, false);
        TextView text = rootView.findViewById(R.id.textView4);
        text.setText(hiddenMessage);
        return rootView;
    }

    static String encrypt( String str) {
        String output = "";
        for(int i = str.length() - 1; i >= 0 ; i--){
            output = output + (str.charAt(i));
        }
        Log.d("DISPLAY_DEBUG", "in encrypt");
        return output;
    }
}