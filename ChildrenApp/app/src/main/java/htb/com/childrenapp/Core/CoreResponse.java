package htb.com.childrenapp.Core;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by weidong_wu on 15/12/1.
 * 邮箱:wwdhao163@163.com
 */
public class CoreResponse extends AsyncHttpResponseHandler {
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }
}
