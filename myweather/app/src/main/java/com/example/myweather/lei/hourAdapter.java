package com.example.myweather.lei;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweather.R;

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
        String duname = String.valueOf(timehour.getDu());
        holder.timedu.setText(duname + "°");
        //holder.weather.setImageResource(timehour.getWeather());

        String weaName = String.valueOf(timehour.getWeather());
        switch (weaName){
            case "xue":
                holder.weather.setImageResource(R.drawable.xue);
                break;
            case "lei":
                holder.weather.setImageResource(R.drawable.lei);
                break;
            case "shachen":
                holder.weather.setImageResource(R.drawable.shachen);
                break;
            case "wu":
                holder.weather.setImageResource(R.drawable.wu);
                break;
            case "bingbao":
                holder.weather.setImageResource(R.drawable.bingbao);
                break;
            case "yun":
                holder.weather.setImageResource(R.drawable.yun);
                break;
            case "yu":
                holder.weather.setImageResource(R.drawable.yu);
                break;
            case "yin":
                holder.weather.setImageResource(R.drawable.yin);
                break;
            case "qing":
                holder.weather.setImageResource(R.drawable.qing);
                break;
            default:
        }

        holder.timeair.setText(timehour.getAir() + "");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
