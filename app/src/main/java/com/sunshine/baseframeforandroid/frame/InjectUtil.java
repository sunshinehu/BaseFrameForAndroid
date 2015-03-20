package com.sunshine.baseframeforandroid.frame;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by SunshineHu on 2015/3/20.
 * 视图反射工具类
 */
public class InjectUtil {

    private static final String LAYOUT_METHOD="setContentView";
    private static final String VIEW_METHOD="findViewById";




    public static void Inject(Activity mActivity) throws Exception{

        InjectLayout(mActivity);
        InjectView(mActivity);

    }


    private static void InjectLayout(Activity mActivity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class clazz=mActivity.getClass();
        LayoutInject li= (LayoutInject) clazz.getAnnotation(LayoutInject.class);
        int layoutId=li.value();//获取layout id

        //反射获取方法
        Method method=clazz.getMethod(LAYOUT_METHOD,int.class);

        method.invoke(mActivity,layoutId);

    }


    private static void InjectView(Activity mAcitity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class clazz=mAcitity.getClass();
        Field[] fields=clazz.getDeclaredFields();//获取子类所有变量

        //遍历所有变量，寻找包含注解的
        for(Field field:fields){
            ViewInject vi=field.getAnnotation(ViewInject.class);//尝试获取
            if(vi!=null){
                int viewId=vi.value();
                Method method=clazz.getMethod(VIEW_METHOD,int.class);
                Object obj=method.invoke(mAcitity,viewId);//调用方法，获得对象

                //将变量设为可访问
                field.setAccessible(true);
                field.set(mAcitity,obj);
            }
        }

    }

}
