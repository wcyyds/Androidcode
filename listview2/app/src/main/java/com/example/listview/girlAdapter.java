package com.example.listview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class girlAdapter extends RecyclerView.Adapter<> {

    private List<girl> girlList;

    public girlAdapter(List<girl> girlList) {
        this.girlList = girlList;
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        //return .siz
        //
        return 0;
    }
}
