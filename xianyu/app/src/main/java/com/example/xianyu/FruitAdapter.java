package com.example.xianyu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.viewholder> {

    public List<Fruit> fruitList;

    static class viewholder extends RecyclerView.ViewHolder {

        ImageView fruitimage;

        TextView fruitname;

        TextView fruitmoney;

        ImageView sellimage;

        TextView sellname;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            fruitimage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitname = (TextView) itemView.findViewById(R.id.fruit_name);
            fruitmoney = (TextView) itemView.findViewById(R.id.fruit_money_number);
            sellimage = (ImageView) itemView.findViewById(R.id.seller_image);
            sellname = (TextView) itemView.findViewById(R.id.seller_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("123", "onCreateViewHolder: creat" + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Log.d("456", "onBindViewHolder: bind" + position);
        Fruit fruit = fruitList.get(position);
        holder.fruitimage.setImageResource(fruit.getFruit_image());
        holder.fruitname.setText(fruit.getFruit_name());
        holder.fruitmoney.setText(fruit.getFruit_money_number());
        holder.sellimage.setImageResource(fruit.getSeller_image());
        holder.sellname.setText(fruit.getSeller_name());
    }


    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
