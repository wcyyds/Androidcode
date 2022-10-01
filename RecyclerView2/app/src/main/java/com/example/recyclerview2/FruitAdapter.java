package com.example.recyclerview2;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;



    public FruitAdapter (List<Fruit> Fruit){
        mFruitList = Fruit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: x");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fruit_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = mFruitList.get(i);
        viewHolder.fruitname.setText(fruit.getName());
        viewHolder.fruitimage.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fruitimage;

        TextView fruitname;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitimage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitname = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }


}
