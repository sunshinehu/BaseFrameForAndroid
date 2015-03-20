package com.sunshine.baseframeforandroid.activity;

import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.sunshine.baseframeforandroid.R;
import com.sunshine.baseframeforandroid.frame.BaseActivity;
import com.sunshine.baseframeforandroid.frame.HttpResponseInterface;
import com.sunshine.baseframeforandroid.frame.HttpTool;
import com.sunshine.baseframeforandroid.frame.LayoutInject;
import com.sunshine.baseframeforandroid.frame.ViewInject;
import com.sunshine.baseframeforandroid.orm.BaseResult;

@LayoutInject(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HttpResponseInterface{

    @ViewInject(R.id.tv)
    private TextView tv;

    @Override
    public void createActivity() {

        tv.setText("Hello Sunshine");

        //创建http请求
        RequestParams params=new RequestParams();
        params.add("","");//添加参数
        HttpTool.HttpPost(0,this,"",params,this ,new TypeToken<BaseResult<Object>>(){}.getType());

    }

    @Override
    public void onResponse(int requsetCode, boolean resultStatus, Object result) {

        //成功

    }

    @Override
    public void onFailure() {

        //失败

    }
}
