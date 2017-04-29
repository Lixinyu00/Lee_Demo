package com.lqx.ui.amazingdialog.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lqx.ui.amazingdialog.Bean.WeatherBean;
import com.lqx.ui.amazingdialog.HttpTool.PureNetUtil;
import com.lqx.ui.amazingdialog.R;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {
    private EditText et_city;
    private Button btn_weather;
    private TextView t_weather;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initViews(view);
        initListeners();
        return view;
    }

    private void initViews(View view) {
        et_city = (EditText) view.findViewById(R.id.et_city);
        btn_weather = (Button) view.findViewById(R.id.btn_weather);
        t_weather = (TextView) view.findViewById(R.id.t_weather);
    }

    private void initListeners() {
        btn_weather.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_weather:
                InputMethodManager imm =(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_city.getWindowToken(), 0);
                Async async=new Async();
                async.execute(et_city.getText().toString());
                break;
        }
    }
    private class Async extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            String url="http://v.juhe.cn/weather/index?&cityname=" + params[0] + "&key=a3b12671d03faa0f2d047e0980b39dbe";
            return PureNetUtil.get(url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            t_weather.setText(getByCity(s));
        }
    }
    private String getByCity(String s){
        String weather;
        if (s!=null) {
            Gson gson = new Gson();
            WeatherBean bean=gson.fromJson(s, WeatherBean.class);
            String code=bean.getResultcode();
            if(code!=null&&code.equals("200")){
                weather=bean.getResult().getSk().toString()+bean.getResult().getToday().toString();
            }
            else {
                weather=null;
            }
        }else {
            weather="error";
        }
        return weather;
    }
}