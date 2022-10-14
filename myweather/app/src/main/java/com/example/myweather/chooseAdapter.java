package com.example.myweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class chooseAdapter extends RecyclerView.Adapter<chooseAdapter.ViewHolder> {

    private List<choose> mchooseList;

    public chooseAdapter(List<choose> chooseList){
        mchooseList = chooseList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView wea_img;
        TextView city;
        TextView air;
        TextView item;
        TextView item1;
        TextView item2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wea_img = (ImageView) itemView.findViewById(R.id.wea_img);
            city = (TextView) itemView.findViewById(R.id.city);
            air = (TextView) itemView.findViewById(R.id.air);
            item = (TextView) itemView.findViewById(R.id.item);
            item1 = (TextView) itemView.findViewById(R.id.item1);
            item2 = (TextView) itemView.findViewById(R.id.item1);
        }
    }

    @NonNull
    @Override
    public chooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull chooseAdapter.ViewHolder holder, int position) {
        choose  choose1 = mchooseList.get(position);
        holder.wea_img.setImageResource(choose1.getWea_img());
        holder.city.setText(choose1.getCity());
        holder.air.setText(choose1.getAir());
        holder.item.setText(choose1.getItem());
        holder.item1.setText(choose1.getItem1());
        holder.item2.setText(choose1.getItem2());
    }

    @Override
    public int getItemCount() {
        return mchooseList.size();
    }


}
