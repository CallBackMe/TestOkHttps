package com.ocean.okhttps;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String url = "https://www.baidu.com";
		OkHttpsManager.getInstance().get(url, null, new OnOkHttpsNetListener() {
			@Override
			public void onSuccess(String info) throws IOException {
				Log.e("ocean"," ++++++++++++  info = " + info);
			}

			@Override
			public void onFail(String errorInfo) {
				Log.e("ocean"," ++++++++++++  errorInfo = " + errorInfo);
			}
		});

	}
}
