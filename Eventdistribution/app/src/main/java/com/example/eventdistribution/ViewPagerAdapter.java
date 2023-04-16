package com.example.eventdistribution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>{

    private List<ViewPager> viewPagerList;

    public ViewPagerAdapter(List<ViewPager> viewPagerList) {
        this.viewPagerList = viewPagerList;
    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.viewpager_item,parent,false);
        return new ViewPagerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        ViewPager viewPager = viewPagerList.get(position);
        holder.imageView.setImageResource(viewPager.getImage());
    }

    @Override
    public int getItemCount() {
        return viewPagerList.size();
    }

    class ViewPagerHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewpager_imageView);
        }
    }

}
