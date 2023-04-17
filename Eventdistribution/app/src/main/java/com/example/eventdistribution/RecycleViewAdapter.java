package com.example.eventdistribution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewViewHolder>{

    private List<Recycleview> recycleviewList;

    public RecycleViewAdapter(List<Recycleview> recycleviewList) {
        this.recycleviewList = recycleviewList;
    }

    @NonNull
    @Override
    public RecycleViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item,parent,false);
        RecycleViewViewHolder recycleViewViewHolder = new RecycleViewViewHolder(view);
        return recycleViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewViewHolder holder, int position) {
        Recycleview recyclerView = recycleviewList.get(position);
        holder.imageView.setImageResource(recyclerView.getImage());
    }

    @Override
    public int getItemCount() {
        return recycleviewList.size();
    }

    static class RecycleViewViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public RecycleViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
