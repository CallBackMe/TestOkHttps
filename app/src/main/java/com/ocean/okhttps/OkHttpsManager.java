package com.ocean.okhttps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * created by ocean.z
 * data 2019-10-16
 * desc ok https manager
 */
public class OkHttpsManager {

	private static OkHttpsManager mInstance = null;

	public static OkHttpsManager getInstance() {
		if (mInstance == null) {
			mInstance = new OkHttpsManager();
		}
		return mInstance;
	}

	private OkHttpsManager() {
		initOkHttpsConfig();
	}

	private OkHttpClient mOkHttpClient;

	private void initOkHttpsConfig() {
		if (mOkHttpClient == null) {
			mOkHttpClient = new OkHttpClient.Builder()
					.followRedirects(true)
					.followSslRedirects(true)
					.build();
		}
	}

	/**
	 * okhttps get 调用
	 * @param url  调用链接
	 * @param param  调用参数
	 * @param callback  回调函数
	 */
	public void get(String url, HashMap<String, String> param, final OnOkHttpsNetListener callback) {
		// 拼接请求参数
		if (param != null && !param.isEmpty()) {
			StringBuffer buffer = new StringBuffer(url);
			buffer.append('?');
			for (Map.Entry<String, String> entry : param.entrySet()) {
				buffer.append(entry.getKey());
				buffer.append('=');
				buffer.append(entry.getValue());
				buffer.append('&');
			}
			buffer.deleteCharAt(buffer.length() - 1);
			url = buffer.toString();
		}
		Request.Builder builder = new Request.Builder().url(url);
		builder.method("GET", null);
		Request request = builder.build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				callback.onFail("失败");
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				callback.onSuccess(response.message());
			}
		});
	}

	/**
	 * okhttps post 调用
	 * @param url  调用链接
	 * @param param  调用参数
	 * @param callback  回调函数
	 */
	public void post(String url, HashMap<String, String> param, final OnOkHttpsNetListener callback) {
		FormBody.Builder formBody = new FormBody.Builder();
		if (param != null && !param.isEmpty()) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				formBody.add(entry.getKey(), entry.getValue());
			}
		}
		RequestBody form = formBody.build();
		Request.Builder builder = new Request.Builder();
		Request request = builder.post(form)
				.url(url)
				.build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				callback.onFail("失败");
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				callback.onSuccess("成功");
			}
		});
	}


}
