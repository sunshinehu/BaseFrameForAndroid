package com.sunshine.baseframeforandroid.frame;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by SunshineHu on 2015/3/20.
 * 通用基类activity
 */
public abstract class BaseActivity extends Activity{

    protected Activity mActivity;//Activity 实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        AppManager.INSTANCE.addActivity(this);//加入activity管理
        try {
            InjectUtil.Inject(this);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        createActivity();
    }

    protected abstract void createActivity();//抽象方法,用来代替oncreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.INSTANCE.finishActivity(this);//移除activity
    }
}
