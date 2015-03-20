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
        try {
            InjectUtil.Inject(this);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        createActivity();
    }

    public abstract void createActivity();

}
