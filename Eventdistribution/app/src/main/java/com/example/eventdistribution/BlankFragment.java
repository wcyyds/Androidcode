package com.example.eventdistribution;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {

    private List<Recycleview> recycleviewList = new ArrayList<>();

    private void initRecycle(){
        for(int i = 0; i < 3; i++){
            Recycleview recyclerView = new Recycleview(R.drawable.top_init);
            recycleviewList.add(recyclerView);
        }
    }

    private static final String TAG = "BlankFragment";

    private static final String ARG_PARAM1 = "param1";

    Integer mTextString1 = null;

    public static BlankFragment newInstance() {

        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank,container,false);
        initRecycle();
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(recycleviewList);
        recyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

}