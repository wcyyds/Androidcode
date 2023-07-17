package com.example.databindinglearntest05java;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databindinglearntest05java.databinding.ItemSwordsmanBinding;

import java.util.List;

public class SwordsmanAdapter extends RecyclerView
        .Adapter<SwordsmanAdapter.SwordsmanViewHolder> {

    private List<Swordsman> mlist;

    public SwordsmanAdapter(List<Swordsman> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public SwordsmanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSwordsmanBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_swordsman, parent,false);
        return new SwordsmanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SwordsmanViewHolder holder, int position) {
        Swordsman swordsman = mlist.get(position);
        holder.getBinding().setRecycleSwordsman(swordsman);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class SwordsmanViewHolder extends RecyclerView.ViewHolder{

        ItemSwordsmanBinding binding;
        public SwordsmanViewHolder(@NonNull ItemSwordsmanBinding itemView) {
            super(itemView.getRoot());
            this.binding = (ItemSwordsmanBinding) itemView;
        }
        public ItemSwordsmanBinding getBinding(){
            return binding;
        }
    }

}
