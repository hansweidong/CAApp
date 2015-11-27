package htb.com.childrenapp.Core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weidong_wu on 15/11/7.
 * 邮箱:wwdhao163@163.com
 */
public class CoreManager {
    private Map<Class<? extends IBaseCore>,Object> CoreClients = new HashMap<Class<? extends IBaseCore>,Object>();
    private static CoreManager m_CoreEvn = null;
    private CoreManager(){}
    public synchronized static CoreManager instance(){
        if(m_CoreEvn==null){
            m_CoreEvn = new CoreManager();
        }
        return m_CoreEvn;
    }

    /**
     * 获得一个Core
     * @param client
     */
    public Object getCore(Class<? extends IBaseCore > client){
        Object object = CoreClients.get(client);
        if (object == null){
            Object newInstance;
            try {
                newInstance = client.newInstance();
                CoreClients.put(client,newInstance);
                object = newInstance;
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InstantiationException e){
                e.printStackTrace();
            }
        }
        return object;
    }
}
