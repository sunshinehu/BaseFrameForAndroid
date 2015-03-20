package com.sunshine.baseframeforandroid.frame;
/**
 * 
 * @author 晨曦
 * Http 请求接口
 */
public interface HttpResponseInterface {

	public void onResponse(int requsetCode, boolean resultStatus, Object result);
	
	public void onFailure();
}
