package com.example.xianyu.homepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xianyu.R;

import java.util.List;

public class MiddleChoiceSellAdapter extends RecyclerView.Adapter<MiddleChoiceSellAdapter.ViewHolder> {

    private List<MiddleChoiceSell> middleChoiceSellList;

    public MiddleChoiceSellAdapter(List<MiddleChoiceSell> middleChoiceSellList11) {
        middleChoiceSellList = middleChoiceSellList11;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.middlechocesell_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MiddleChoiceSell middleChoiceSell = middleChoiceSellList.get(position);
        holder.middleImage.setImageResource(middleChoiceSell.getImageId());
        holder.bigmessage.setText(middleChoiceSell.getBig_message());
        holder.smallmessage.setText(middleChoiceSell.getSmall_message());
    }

    @Override
    public int getItemCount() {
        return middleChoiceSellList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView middleImage;
        TextView bigmessage;
        TextView smallmessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            middleImage = (ImageView) itemView.findViewById(R.id.middle_image);
            bigmessage = (TextView) itemView.findViewById(R.id.big_message);
            smallmessage = (TextView) itemView.findViewById(R.id.small_message);
        }
    }



}
