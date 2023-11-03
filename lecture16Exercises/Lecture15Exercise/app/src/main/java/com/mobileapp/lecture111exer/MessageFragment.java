package com.mobileapp.lecture111exer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mobileapp.lecture111exer.databinding.FragmentMessageBinding;



public class MessageFragment extends Fragment {
    Button encryptButton;
    private FragmentMessageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // View view = inflater.inflate(R.layout.fragment_message, container, false);

        binding = FragmentMessageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // encryptButton = view.findViewById(R.id.encryptButton);
        encryptButton = binding.encryptButton;

        //add a listener to the button
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText amountTv = (EditText) getView().findViewById(R.id.editTextTextPassword);
                EditText amountTv = binding.editTextTextPassword;
                String message = amountTv.getText().toString();
                Log.d("ADebugTag", "Value: " + message);
                MessageFragmentDirections.ActionMessageFragmentToEncryptFragment action = MessageFragmentDirections.actionMessageFragmentToEncryptFragment(message);
                Navigation.findNavController(view).navigate(action);
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