package com.example.constraintlayout;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewpager2_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewpager2_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Fruit> fruitList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewpager2_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewpager2_1.
     */
    // TODO: Rename and change types and number of parameters
    public static viewpager2_1 newInstance(String param1, String param2) {
        viewpager2_1 fragment = new viewpager2_1();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewpager2_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);

    }

    private void initFruits(){
        Log.d(TAG, "initFruits: ");
        for(int i = 0; i < 3; i++){
            Fruit apple = new Fruit("WANGCHENYANG",R.drawable.apple);
            fruitList.add(apple);
            Fruit beer = new Fruit("beer",R.drawable.beer);
            fruitList.add(beer);
            Fruit bread = new Fruit("bread",R.drawable.bread);
            fruitList.add(bread);
            Fruit burger = new Fruit("burger",R.drawable.burger);
            fruitList.add(burger);
            Fruit cake = new Fruit("cake",R.drawable.cake);
            fruitList.add(cake);
            Fruit cheesecake = new Fruit("cheesecake",R.drawable.cheesecake);
            fruitList.add(cheesecake);
            Fruit cherry = new Fruit("cherry",R.drawable.cherry);
            fruitList.add(cherry);
            Fruit chips = new Fruit("chips",R.drawable.chips);
            fruitList.add(chips);
            Fruit kiwi = new Fruit("kiwi",R.drawable.kiwi);
            fruitList.add(kiwi);
            Fruit lemon = new Fruit("lemon",R.drawable.lemon);
            fruitList.add(lemon);
            Fruit pear = new Fruit("pear",R.drawable.pear);
            fruitList.add(pear);
            Fruit pineapple = new Fruit("pineapple",R.drawable.pineapple);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("strawberry",R.drawable.strawberry);
            fruitList.add(strawberry);
            Fruit tea = new Fruit("tea",R.drawable.tea);
            fruitList.add(tea);
            Fruit watermelon = new Fruit("watermelon",R.drawable.watermelon);
            fruitList.add(watermelon);
        }
    }

}