package com.example.xianyu.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xianyu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewpager2_1_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewpager2_1_3 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String mTextString1 = "xxx";
    String mTextString2 = "xxx";
    View rootView;

    public static viewpager2_1_3 newInstance(String param1, String param2) {

        viewpager2_1_3 fragment = new viewpager2_1_3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString1 = getArguments().getString(ARG_PARAM1);
            mTextString2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_viewpager2_1_3, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        TextView textView = rootView.findViewById(R.id.mTextView3);
        TextView textView1 = rootView.findViewById(R.id.anotherTextView3);
        textView.setText(mTextString1);
        textView1.setText(mTextString2);
    }
}