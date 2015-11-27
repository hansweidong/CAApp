package htb.com.childrenapp.Framework.http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by weidong_wu on 15/11/9.
 * 邮箱:wwdhao163@163.com
 */
public class CAHttpManager {


    private static CAHttpManager CA_HTTP;

    private Context m_context;

    private AsyncHttpClient httpClient = null;


    private CAHttpManager() {
        httpClient = new AsyncHttpClient();
    }

    public synchronized static CAHttpManager instance() {
        if (CA_HTTP == null) {
            CA_HTTP = new CAHttpManager();
        }
        return CA_HTTP;
    }

    public void init(Context context) {
        this.m_context = context;

    }


    /**
     * 发送协议
     *
     * @param client
     */
    public void sendPost(Object client, AsyncHttpResponseHandler handler) {

        String send_url = ((IProtocolEnt) client).getURL();
        System.out.println("send_url=" + send_url);
        Map<String, String> ParamsMap = ((IProtocolEnt) client).getParmasMap();
        if (ParamsMap == null || send_url == null || send_url.compareTo("") == 0) {
            System.out.println("null point!");
            return;
        }
        Iterator<String> iterator = ParamsMap.keySet().iterator();
        RequestParams params = new RequestParams();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = ParamsMap.get(key);
            params.put(key, value);
        }

        httpClient.get(send_url, params, handler);
    }

}
