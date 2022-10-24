package com.example.viewpager_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment extends Fragment {

    private static final String TAG = "BlankFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String mTextString1 = "xxx";
    String mTextString2 = "xxx";
    View rootView;

    public static BlankFragment newInstance(String param1, String param2) {
        Log.d(TAG, "newInstance: 从主函数那边调过来创建碎片的实例");

        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: 在这里创建碎片");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString1 = getArguments().getString(ARG_PARAM1);
            mTextString2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: 在这里加载视图");

        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        TextView textView = rootView.findViewById(R.id.mTextView);
        TextView textView1 = rootView.findViewById(R.id.anotherTextView);
        textView.setText(mTextString1);
        textView1.setText(mTextString2);
    }
}
