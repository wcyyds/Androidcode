package com.example.remote_help.sharescreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.remote_help.R;

public class GetNumberFragment extends Fragment {

    private View view;

    private Person person = Person.getInstance();

    private Button button;

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_addfamily,container,false);
        }
        // Inflate the layout for this fragment\
        editText = (EditText) view.findViewById(R.id.editText);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里可以加一个限制手机号的判断的东西
                person.setUserID(editText.getText().toString());
                person.setRoomID(editText.getText().toString());
                person.setStreamID(editText.getText().toString());
            }
        });

        return view;
    }
}