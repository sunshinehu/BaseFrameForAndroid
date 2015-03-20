package com.sunshine.baseframeforandroid.orm;

/**
 * 泛型json接收类
 * Created by SunshineHu on 2015/3/20.
 */
public class BaseResult<T> {

    private boolean flag;
    private String message;
    private T result;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
