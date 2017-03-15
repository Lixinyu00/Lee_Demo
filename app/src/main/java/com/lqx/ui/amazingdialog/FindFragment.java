package com.lqx.ui.amazingdialog;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;


public class FindFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private MapView mapView;
    private AMap aMap;
    private ToggleButton btn_mapchange;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    public FindFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initview(savedInstanceState,view);
        initlistener();
        return view;
    }

    private void initview( Bundle savedInstanceState,View view){
        mapView= (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }
        btn_mapchange=(ToggleButton) view.findViewById(R.id.btn_mapchange);
    }

    private void initlistener(){
        btn_mapchange.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        }
        else {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}


