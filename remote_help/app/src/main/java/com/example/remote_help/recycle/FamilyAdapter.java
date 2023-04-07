package com.example.remote_help.recycle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remote_help.R;
import com.example.remote_help.sharescreen.GetPhoneNumberActivity;
import com.example.remote_help.sharescreen.Helped_person;
import com.example.remote_help.sharescreen.Person;
import com.example.remote_help.sqlite.Operate;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {

    private List<Family> list;

    private boolean deleteActivity = false;

    private Operate operate = Operate.getInstance();

    private Context object;

    private Person person = Person.getInstance();

    public void setDeleteActivity(boolean deleteActivity, Context object) {
        this.deleteActivity = deleteActivity;
        this.object = object;
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
                    Family family = list.get(position);
                    //要在这里发出警告,警告的内容,然后是否同意
                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("这是一个警告");
                    dialog.setMessage("你确定要删除" + family.getName());
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            operate.deleteSQL(family.getIdentifier());
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }else{
                    Log.d("打电话", "onClick: 直接进入打电话的界面,然后开房间,进行屏幕通话");
                        Intent intent = new Intent(object, Helped_person.class);
                        object.startActivity(intent);
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
