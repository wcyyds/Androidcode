package com.example.remote_help;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.remote_help.sqlite.MyDatabaseHelper;
import com.example.remote_help.sqlite.Operate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addfamily#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addfamily extends Fragment {

    private View view;

    private EditText importName;

    private EditText importPhone;

    private Button addPerson;

    private String name;

    private String phone;

    private Operate operate = Operate.getInstance();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addfamily() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addfamily.
     */
    // TODO: Rename and change types and number of parameters
    public static addfamily newInstance(String param1, String param2) {
        addfamily fragment = new addfamily();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view == null){
            view = inflater.inflate(R.layout.fragment_addfamily,container,false);
        }
        // Inflate the layout for this fragment\
        importName = (EditText) view.findViewById(R.id.add_name);
        importPhone = (EditText) view.findViewById(R.id.add_phone);

        addPerson = (Button) view.findViewById(R.id.add_person);
        Log.d("", "onCreateView: 这里是点击事件");
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里是添加的函数的声明
                name = importName.getText().toString();
                phone = importPhone.getText().toString();
                Log.d("sqladdfamily", "onClick: 名字" + name);
                Log.d("sqladdfamily", "onClick: 手机号" + phone);
                Log.d("sqladdfamily", "onClick: 这里已经把名字和手机号截取到了");
                operate.addSQL(name,phone);
            }
        });
        return view;
    }
}