package com.sunshine.baseframeforandroid.frame;

import java.lang.reflect.Type;

import org.apache.http.Header;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sunshine.baseframeforandroid.R;
import com.sunshine.baseframeforandroid.orm.BaseResult;

/**
 * 
 * @author 晨曦
 *	二次封装的Http请求，一切效率至上，减少对象的创建
 */
public class HttpTool {

	//http请求client
	private static AsyncHttpClient client=null;

	private static Gson gson=null;
	
	/**
	 * 
	 *  POST 方法
	 *  params
	 *  response
	 */
	public static void HttpPost(final int requestCode,final Context c,String url,RequestParams params,final HttpResponseInterface response,final Type type){
		
		/**
		 * lazy 加载
		 */
		if(client==null){
			
			synchronized (c) {
				if(client==null){
					client=new AsyncHttpClient();
				}
			}
			
		}
		
		if(gson==null){
			
			synchronized (c) {
				if(gson==null){
					gson=new Gson();
				}
			}
			
		}
		
		if(!isOpenNetwork(c)){
			Toast.makeText(c, c.getString(R.string.network_status_error), Toast.LENGTH_SHORT).show();
			response.onFailure();
			return;
		}
		
		TextHttpResponseHandler handler=new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
					try {
						Object result = gson.fromJson(arg2, type);
						response.onResponse(requestCode, ((BaseResult)result).isFlag(), result);
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(c, c.getString(R.string.json_parse_error), Toast.LENGTH_SHORT).show();
						response.onFailure();
					} catch (JsonIOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(c, c.getString(R.string.json_parse_error), Toast.LENGTH_SHORT).show();
						response.onFailure();
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(c, c.getString(R.string.json_parse_error), Toast.LENGTH_SHORT).show();
						response.onFailure();
					}
					
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(c, c.getString(R.string.network_request_error), Toast.LENGTH_SHORT).show();
				response.onFailure();
			}
		};
		
		client.post(url, params, handler);
		
	}
	
	
	/**
	 * 对网络连接状态进行判断
	 * @return  true, 可用； false， 不可用
	 */
	private static boolean isOpenNetwork(Context c) {
		ConnectivityManager connManager = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connManager.getActiveNetworkInfo() != null) {
			return connManager.getActiveNetworkInfo().isAvailable();
		}

		return false;
	}
    
	
	
}
