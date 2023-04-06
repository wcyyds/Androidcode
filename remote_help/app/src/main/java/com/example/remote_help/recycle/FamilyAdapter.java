package com.example.remote_help.recycle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remote_help.R;
import com.example.remote_help.sqlite.Operate;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {

    private List<Family> list;

    private boolean deleteActivity = false;

    private Operate operate = Operate.getInstance();

    public void setDeleteActivity(boolean deleteActivity) {
        this.deleteActivity = deleteActivity;
    }

    public FamilyAdapter(List<Family> list){
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View familyView;
        TextView name;
        TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyView = itemView;
            name = itemView.findViewById(R.id.name);
            phone_number = itemView.findViewById(R.id.phone_number);
        }
    }

    @NonNull
    @Override
    public FamilyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.family_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteActivity){
                    Log.d("delete", "onClick: ");
                    int position = holder.getAdapterPosition();

                    //要在这里发出警告,警告的内容,然后是否同意
                    if(true){

                    }
                    Family family = list.get(position);
                    operate.deleteSQL(family.getIdentifier());
                }else{
                    Log.d("打电话", "onClick: ");
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyAdapter.ViewHolder holder, int position) {
        Family family = list.get(position);
        holder.name.setText(family.getName());
        holder.phone_number.setText((family.getPhone_number()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
