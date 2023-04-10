package com.example.busquery;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.blankj.utilcode.util.AppUtils;

public class MainActivity extends AppCompatActivity {

    private PoiSearch poiSearch;

    private BusLineSearch busLineSearch;

    private String busLineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < AppUtils.getAppSignaturesSHA1().size(); i++){
            Log.d(TAG, "onCreate: " + AppUtils.getAppSignaturesSHA1().get(i));
        }

        poiSearch = PoiSearch.newInstance();

        poiSearch.searchInCity(new PoiCitySearchOption()
                .city("北京")
                .keyword("963")
                .scope(2));

        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    return;
                }
                //遍历所有POI，找到类型为公交线路的POI
                for (PoiInfo poi : poiResult.getAllPoi()) {
                    if (poi.getPoiDetailInfo().getTag() == PoiInfo.POITYPE.BUS_LINE.toString() ||
                            poi.getPoiDetailInfo().getTag() == PoiInfo.POITYPE.SUBWAY_LINE.toString()) {
                        //获取该条公交路线POI的UID
                        busLineId = poi.uid;
                        break;
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };

        poiSearch.setOnGetPoiSearchResultListener(listener);

        busLineSearch = BusLineSearch.newInstance();

        busLineSearch.searchBusLine(new BusLineSearchOption()
                .city("北京")
                .uid(busLineId));

        OnGetBusLineSearchResultListener busLineSearchResultListener = new OnGetBusLineSearchResultListener() {
            @Override
            public void onGetBusLineResult(BusLineResult busLineResult) {
                Log.d("公交信息", "onGetBusLineResult: " + busLineResult.toString());
            }
        };


    }

}