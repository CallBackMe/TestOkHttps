package com.ocean.okhttps;
import java.io.IOException;

public interface OnOkHttpsNetListener {
	void onSuccess(String info) throws IOException;

	void onFail(String errorInfo);
}
