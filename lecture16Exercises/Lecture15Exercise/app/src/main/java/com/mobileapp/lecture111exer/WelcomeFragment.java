package com.mobileapp.lecture111exer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileapp.lecture111exer.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {

    Button startButton;
    private FragmentWelcomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater,
                container, false);
        // View view = inflater.inflate(R.layout.fragment_welcome,
                //container, false);
        startButton = binding.startButton;
        View view = binding.getRoot();
        //add a listener to the button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_messageFragment);
            }
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}