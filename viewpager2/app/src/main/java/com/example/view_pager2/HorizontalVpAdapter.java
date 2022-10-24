package com.example.view_pager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalVpAdapter extends RecyclerView.Adapter<HorizontalVpAdapter.HorizontalVpViewHolder> {

    private List<ViewPager> viewpagerList;

    public HorizontalVpAdapter(List<ViewPager> viewpagerList) {
        this.viewpagerList = viewpagerList;
    }

    @NonNull
    @Override
    public HorizontalVpAdapter.HorizontalVpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item,parent,false);
        Log.d("123", "onCreateViewHolder: 开始创建一个viewholder了");
        return new HorizontalVpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalVpAdapter.HorizontalVpViewHolder holder, int position) {
        ViewPager viewpager = viewpagerList.get(position);
        holder.mImage.setImageResource(viewpager.getImage());
        holder.mText.setText(viewpager.getName());
        Log.d("456", "onBindViewHolder: 对滚入到屏幕的每个子项进行赋值");
    }

    @Override
    public int getItemCount() {
        return viewpagerList.size();
    }

    class HorizontalVpViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mText;

        HorizontalVpViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mText = itemView.findViewById(R.id.text);
        }
    }

}
