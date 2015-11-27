package htb.com.childrenapp.Framework.http;

import java.util.Map;

/**
 * Created by weidong_wu on 15/11/11.
 * 邮箱:wwdhao163@163.com
 */
public class IProtocolEnt  {

    private   String URL = "";

    private Map<String,String> ParmasMap;

    public Map<String,String> getParmasMap(){
        return ParmasMap;
    }

    public void setParamsMap(Map<String,String> paramsMap){
        ParmasMap = paramsMap;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
