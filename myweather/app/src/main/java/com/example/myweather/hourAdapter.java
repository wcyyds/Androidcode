package com.example.myweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class hourAdapter extends RecyclerView.Adapter<hourAdapter.ViewHolder> {

    private List<timehour> mlist;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView timehour;
        TextView timedu;
        ImageView weather;
        TextView timeair;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timehour = (TextView) itemView.findViewById(R.id.hour_item);
            weather = (ImageView) itemView.findViewById(R.id.weather);
            timedu = (TextView) itemView.findViewById(R.id.du_item);
            timeair = (TextView) itemView.findViewById(R.id.air_item);
        }
    }

    public hourAdapter(List<timehour> mtimehourlist) {
        mlist = mtimehourlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timehour_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        timehour timehour = mlist.get(position);
        holder.timehour.setText(timehour.getHour());
        holder.timedu.setText(timehour.getDu() + "");
        holder.weather.setImageResource(timehour.getWeather());
        holder.timeair.setText(timehour.getAir() + "");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
