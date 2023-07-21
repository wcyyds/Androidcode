package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recyclerview.Fruit;
import com.example.recyclerview.R;
import com.example.recyclerview.databinding.FruitItemBinding;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;



        public ViewHolder(View view) {
            super(view);

            FruitItemBinding itemBinding = null;

            Log.d("wangceshi01","1111" + view.getContext().toString());
            Log.d("wangceshi02","2222" + LayoutInflater.from(view.getContext()).toString());
            Log.d("wangceshi03","333" + itemBinding.inflate(LayoutInflater.from(view.getContext()), (ViewGroup) view,false).toString());
            //LayoutInflater.from(view.getContext()).toString()这个会创建一个新的视图
            Log.d("wangceshi04","444" + view.toString());

            Log.d("wangceshi04","555" + itemBinding.bind(view).toString());
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter(List<Fruit>fruitList){
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item,parent,false);


        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               int position = holder.getAdapterPosition();
               Fruit fruit = mFruitList.get(position);
               Toast.makeText(v.getContext(),"you clicked view" + fruit.getName(),
                       Toast.LENGTH_LONG).show();
           }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getLayoutPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked image" + fruit.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }



}
