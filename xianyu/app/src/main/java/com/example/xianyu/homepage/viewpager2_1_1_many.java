package com.example.xianyu.homepage;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xianyu.Fruit;
import com.example.xianyu.FruitAdapter;
import com.example.xianyu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewpager2_1_1_many#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewpager2_1_1_many extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Fruit> fruitList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewpager2_1_1_many() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewpager2_1_1_many.
     */
    // TODO: Rename and change types and number of parameters
    public static viewpager2_1_1_many newInstance(String param1, String param2) {
        viewpager2_1_1_many fragment = new viewpager2_1_1_many();
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
        return inflater.inflate(R.layout.fragment_viewpager2_1_1_many, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_viewpager_many);
        StaggeredGridLayoutManager layoutManage = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManage);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);

    }


    private void initFruits(){
        Log.d(TAG, "initFruits: ");
        for(int i = 0; i < 3; i++){
            Fruit apple = new Fruit(R.drawable.fruit_1,"自用狗屁王一代，只有一个鼠标和接收器 正常使用不建议溢价 感兴趣的话就找我私聊吧",
                    "999999999999",R.drawable.sell_1,"王昊阳");
            fruitList.add(apple);
            Fruit beer = new Fruit(R.drawable.fruit_2,"锤子坚果r2,8+256,内存绿色,边框无大磕碰,屏幕无划痕,成色新,屏幕有微软老化,(抖音加号),不明显,包装盒原装快充都在全套出",
                    "1550",R.drawable.sell_2,"77新年快乐");
            Fruit bread1 = new Fruit(R.drawable.fruit_3,"[便宜处理]雅迪同款新国标全新电动车,可上牌,店面到期了不再续租了,生意难做,现将一批全新未开封的电动车以二手的价格线上出售,还有10辆未出售.证件齐全,可上牌",
                    "99999999",R.drawable.sell_3,"王昊阳");
            fruitList.add(bread1);
            Fruit burger = new Fruit(R.drawable.fruit_4,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈呵呵哈哈哈哈哈哈哈",
                    "99999999",R.drawable.sell_4,"王昊阳");
            fruitList.add(burger);
            Fruit cake = new Fruit(R.drawable.fruit_5,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "99999999",R.drawable.sell_5,"王昊阳");
            fruitList.add(cake);
            Fruit cheesecake = new Fruit(R.drawable.fruit_6,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈呵哈哈哈哈哈哈哈",
                    "99999",R.drawable.sell_6,"王昊阳");
            fruitList.add(cheesecake);
            Fruit cherry = new Fruit(R.drawable.fruit_7,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "99999",R.drawable.sell_7,"王昊阳");
            fruitList.add(cherry);
            Fruit chips = new Fruit(R.drawable.fruit_8,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "99999999",R.drawable.sell_8,"王昊阳");
            fruitList.add(chips);
            Fruit kiwi = new Fruit(R.drawable.fruit_9,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "9999",R.drawable.sell_9,"王昊阳");
            fruitList.add(kiwi);
            Fruit lemon = new Fruit(R.drawable.fruit_10,"哈哈哈哈哈哈哈哈哈哈",
                    "999999",R.drawable.sell_10,"王昊阳");
            fruitList.add(lemon);
            Fruit pear = new Fruit(R.drawable.fruit_11,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈呵呵哈哈哈哈哈哈哈",
                    "999999999",R.drawable.sell_1,"王昊阳");
            fruitList.add(pear);
            Fruit pineapple = new Fruit(R.drawable.fruit_12,"哈哈哈哈哈",
                    "999999999",R.drawable.sell_2,"王昊阳");
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(R.drawable.fruit_13,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "99",R.drawable.sell_3,"王昊阳");
            fruitList.add(strawberry);
            Fruit tea = new Fruit(R.drawable.fruit_14,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "999",R.drawable.sell_4,"王昊阳");
            fruitList.add(tea);
            Fruit watermelon = new Fruit(R.drawable.fruit_11,"哈哈哈哈哈",
                    "999999",R.drawable.sell_5,"王昊阳");
            fruitList.add(watermelon);
        }
    }


}