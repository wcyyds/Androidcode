package com.example.xianyu;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link faxianFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class faxianFragment extends Fragment {


    Context context;

    private static final String TAG = "faxianFragment";


    private Banner mBanner;
    private List<DataBean> mList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public faxianFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment faxianFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static faxianFragment newInstance(String param1, String param2) {
        Log.d(TAG, "newInstance: 这是那个发现的碎片");
        faxianFragment fragment = new faxianFragment();
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
        return inflater.inflate(R.layout.fragment_faxian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList.add(new DataBean(R.drawable.banner_1));
        mList.add(new DataBean(R.drawable.banner_2));
        mList.add(new DataBean(R.drawable.banner_3));
        mList.add(new DataBean(R.drawable.banner_4));
        mList.add(new DataBean(R.drawable.banner_5));

        mBanner = (Banner) view.findViewById(R.id.banner);
        mBanner.setAdapter(new ImageAdapter(getContext(), mList));

        // Set Banner is auto to loop.
        mBanner.isAutoLoop(true);

        // Set an indicator for Banner.
        mBanner.setIndicator(new CircleIndicator(getContext()), false);
        mBanner.start();

    }


}